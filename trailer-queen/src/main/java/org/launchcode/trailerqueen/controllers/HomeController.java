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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String displayHome(Model model) {

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
        JSONObject hazards;
        JSONObject type;
        JSONObject level;

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
//            TODO: What to do with these objects?
            hazards = stuff.getJSONObject(i).getJSONObject("terrain");
            type = stuff.getJSONObject(i).getJSONObject("permittedVehicles");
            level = stuff.getJSONObject(i).getJSONObject("experienceLevel");

            Location alocation = new Location(parkLat, parkLng);
            locationList.add(alocation);
            Park aPark = new Park(name, shortDesc, parkLat, parkLng, parkCode, hazards, type, level);
            System.out.println(name);
            System.out.println(shortDesc);
            System.out.println(parkCode);
            System.out.println(hazards);
            System.out.println(type);
            System.out.println(level);
            resultsList.add(aPark);
        }

        model.addAttribute("selectedVehicle", vehicle);
        model.addAttribute("selectedExperience", experience);
        model.addAttribute("selectedTerrain", terrain);
        model.addAttribute("baseLat", googLat);
        model.addAttribute("baseLng", googLng);
        model.addAttribute("locations", locationList);
        model.addAttribute("parks", resultsList);
        return "results";

    }
}