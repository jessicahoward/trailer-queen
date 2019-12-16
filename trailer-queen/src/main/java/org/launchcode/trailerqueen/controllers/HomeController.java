package org.launchcode.trailerqueen.controllers;

import com.google.gson.*;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.launchcode.trailerqueen.models.Location;
import org.launchcode.trailerqueen.models.Park;
import org.launchcode.trailerqueen.models.User;
import org.launchcode.trailerqueen.repositories.ParkRepository;
import org.launchcode.trailerqueen.repositories.UserRepository;
import org.launchcode.trailerqueen.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParkService parkService;

    @Autowired
    ParkRepository parkRepository;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String displayHome(Model model) {

        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }

        User user = userRepository.findByEmail(currentUserName);
        List<Park> favParks = parkRepository.findByUsers_Id(user.getId());

//        SELECT `park_id` FROM `user_fav_park` WHERE `auth_user_id`LIKE 5;
        model.addAttribute("favParks", favParks);
        model.addAttribute("username", user.getEmail());
//        TODO: Pass favorites to home view
        return "home";
    }

    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String processHome(Model model, @RequestParam String zip,
                              @RequestParam String dist,
                              @RequestParam String vehicle,
                              @RequestParam String experience,
                              @RequestParam String terrain) throws InterruptedException, ApiException, IOException, UnirestException, JSONException {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyC6urX1HiPnH_DH0Miwbaz3Ce2htxSS-68")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, zip).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String googLat = gson.toJson(results[0].geometry.location.lat);
        String googLng = gson.toJson(results[0].geometry.location.lng);

//        TODO : BONUS! HIDE YOUR API KEYS DUMMY!!

        String host = "https://brappdbv2.p.rapidapi.com/Parks";
        String xRapidApiHost = "brappdbv2.p.rapidapi.com";
        String xRapidApiKey = "daJQtrmBjimshXqYFh1HBrEKaQZop1ERXVXjsnp8CgR0U1Me4u";
        String authorization = "DEj1j3JKBjzFIjN2rdAdTec8c40tNjuozluyfOQOjBw";

        String alwaysOpen = "isOpen=true";
        String distance = "dist=" + dist ;
        String allowed = "allowedvehicles=" + vehicle;
        String expLevel = "ExperienceLevel=" + experience;
        String obstacles = "TerrainHas" + terrain;
        String query = allowed + "&" + expLevel + "&" + obstacles + "&" + alwaysOpen + "&" + "lat=" + googLat + "&" + "lng=" + googLng + "&" + distance;

        HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
                .header("x-rapidapi-host", xRapidApiHost)
                .header("x-rapidapi-key", xRapidApiKey)
                .header("authorization", authorization)
                .asJson();

        JSONObject obj = new JSONObject(response.getBody().toString());

        String name;
        String desc;
        String shortDesc;
        String parkLat;
        String parkLng;
        int parkCode;
        int sand = 0;
        int hardPack = 0;
        int jumps = 0;
        int largeRocks = 0;
        int mud = 0;
        int hillClimb = 0;
        boolean motorcycle = false;
        boolean atv = false;
        boolean jeep = false;
        boolean sxs = false;
        int levelBeginner = 0;
        int levelAdvanced = 0;
        int levelIntermediate = 0;
        int levelExpert = 0;


        ArrayList<Location> locationList = new ArrayList<>();
        ArrayList<Park> resultsList = new ArrayList<>();
        JSONArray stuff = (JSONArray) obj.getJSONArray("data");
        for (int i = 0; i < stuff.length(); i++) {
            name = stuff.getJSONObject(i).getString("Name");
            desc = stuff.getJSONObject(i).getString("Desc");
            shortDesc = desc.substring(0, 100) + "...";
            parkLat = stuff.getJSONObject(i).getString("Lat");
            parkLng = stuff.getJSONObject(i).getString("Lng");
            parkCode = stuff.getJSONObject(i).getInt("Id");

            if (stuff.getJSONObject(i).getJSONObject("terrain").get("Sand").equals("null") ||  stuff.getJSONObject(i).getJSONObject("terrain").get("Sand").equals(null)) {
                sand = 0;
            } else {
                sand = (int)stuff.getJSONObject(i).getJSONObject("terrain").get("Sand");
            }

            if (stuff.getJSONObject(i).getJSONObject("terrain").get("HardPack").equals("null") ||  stuff.getJSONObject(i).getJSONObject("terrain").get("HardPack").equals(null)) {
                hardPack = 0;
            } else {
                hardPack = (int)stuff.getJSONObject(i).getJSONObject("terrain").get("HardPack");
            }

            if (stuff.getJSONObject(i).getJSONObject("terrain").get("Jumps").equals("null") ||  stuff.getJSONObject(i).getJSONObject("terrain").get("Jumps").equals(null)) {
                jumps = 0;
            } else {
                jumps = (int)stuff.getJSONObject(i).getJSONObject("terrain").get("Jumps");
            }

            if (stuff.getJSONObject(i).getJSONObject("terrain").get("LargeLooseRocks").equals("null") ||  stuff.getJSONObject(i).getJSONObject("terrain").get("LargeLooseRocks").equals(null)) {
                largeRocks = 0;
            } else {
                largeRocks = (int)stuff.getJSONObject(i).getJSONObject("terrain").get("LargeLooseRocks");
            }

            if (stuff.getJSONObject(i).getJSONObject("terrain").get("Mud").equals("null") ||  stuff.getJSONObject(i).getJSONObject("terrain").get("Mud").equals(null)) {
                mud = 0;
            } else {
                mud = (int)stuff.getJSONObject(i).getJSONObject("terrain").get("Mud");
            }

            if (stuff.getJSONObject(i).getJSONObject("terrain").get("HillClimb").equals("null") ||  stuff.getJSONObject(i).getJSONObject("terrain").get("HillClimb").equals(null)) {
                hillClimb = 0;
            } else {
                hillClimb = (int)stuff.getJSONObject(i).getJSONObject("terrain").get("HillClimb");
            }

            motorcycle = stuff.getJSONObject(i).getJSONObject("permittedVehicles").getBoolean("Motorcycle");
            atv = stuff.getJSONObject(i).getJSONObject("permittedVehicles").getBoolean("ATV");
            jeep = stuff.getJSONObject(i).getJSONObject("permittedVehicles").getBoolean("Jeep");
            sxs = stuff.getJSONObject(i).getJSONObject("permittedVehicles").getBoolean("SxS");
//

            if (stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Beginner").equals(null) || stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Beginner").equals("null")) {
                levelBeginner = 0;
            } else {
                levelBeginner = (int)stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Beginner");
            }

            if (stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Advanced").equals(null) || stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Advanced").equals("null")) {
                levelAdvanced = 0;
            } else {
                levelAdvanced = (int)stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Advanced");
            }

            if (stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Intermediate").equals(null) || stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Intermediate").equals("null")) {
                levelIntermediate = 0;
            } else {
                levelIntermediate = (int)stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Intermediate");
            }

            if (stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Expert").equals(null) || stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Expert").equals("null")) {
                levelExpert = 0;
            } else {
                levelExpert = (int)stuff.getJSONObject(i).getJSONObject("experienceLevel").get("Expert");
            }

            Location alocation = new Location(parkLat, parkLng);
            locationList.add(alocation);
            Park aPark = new Park(name, shortDesc, parkLat, parkLng, parkCode, sand, hardPack, jumps, largeRocks, mud, hillClimb, motorcycle, atv, jeep, sxs, levelBeginner, levelAdvanced, levelIntermediate, levelExpert);
            resultsList.add(aPark);
        }

        model.addAttribute("baseLat", googLat);
        model.addAttribute("baseLng", googLng);
        model.addAttribute("locations", locationList);
        model.addAttribute("parks", resultsList);
        return "results";

    }
}