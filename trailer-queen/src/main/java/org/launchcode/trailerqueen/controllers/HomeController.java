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
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@Controller
public class HomeController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String displayHome(Model model) {
        return "home";
    }

    @RequestMapping(value = "home", method = RequestMethod.POST)
    @ResponseBody
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

        String latDegree = gson.toJson(results[0].geometry.location.lat);
        String longDegree = gson.toJson(results[0].geometry.location.lng);
//        TODO 4: HIDE YOUR API KEYS DUMMY

        String host = "https://brappdbv2.p.rapidapi.com/Parks";
        String xRapidApiHost = "brappdbv2.p.rapidapi.com";
        String xRapidApiKey = "daJQtrmBjimshXqYFh1HBrEKaQZop1ERXVXjsnp8CgR0U1Me4u";
        String authorization = "DEj1j3JKBjzFIjN2rdAdTec8c40tNjuozluyfOQOjBw";

        String alwaysOpen = "isOpen=true";
        String distance = "dist=" + dist ;
        String allowed = "allowedvehicles=" + vehicle;
        String expLevel = "ExperienceLevel=" + experience;
        String obstacles = "TerrainHas" + terrain;
        String query = allowed + "&" + expLevel + "&" + obstacles + "&" + alwaysOpen + "&" + "lat=" + latDegree + "&" + "lng=" + longDegree + "&" + distance;

        HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
                .header("x-rapidapi-host", xRapidApiHost)
                .header("x-rapidapi-key", xRapidApiKey)
                .header("authorization", authorization)
                .asJson();

        Gson anotherGson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(response.getBody().toString());
        String prettyJsonString = anotherGson.toJson(je);
//
        JSONObject obj = new JSONObject(response.getBody().toString());
//        System.out.println("************" + obj.getJSONArray("data"));

        
        JSONArray stuff = (JSONArray) obj.getJSONArray("data");
        for (int i = 0; i < stuff.length(); i++) {
            String name = stuff.getJSONObject(i).getString("Name");
            String desc = stuff.getJSONObject(i).getString("Desc");
//            String permittedVehicles = stuff.getJSONObject(i).getString("permittedVehicles");
            System.out.println("********************" + name);
            System.out.println("********************" + desc);

//            System.out.println("********************" + permittedVehicles);
        }
//        System.out.println("************" + stuff);

        return prettyJsonString;

    }
}

