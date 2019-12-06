package org.launchcode.trailerqueen.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.launchcode.trailerqueen.models.Park;
import org.launchcode.trailerqueen.models.Weather;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ResultsController {

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String displayResults() {

        return "results";
    }

    @RequestMapping(value = "park-view", method = RequestMethod.POST)
    public String targetSpecificResult(Model model, @RequestParam int code) throws JSONException, UnirestException {

        String host = "https://brappdbv2.p.rapidapi.com/Park/" + code;
        String xRapidApiHost = "brappdbv2.p.rapidapi.com";
        String xRapidApiKey = "daJQtrmBjimshXqYFh1HBrEKaQZop1ERXVXjsnp8CgR0U1Me4u";
        String authorization = "DEj1j3JKBjzFIjN2rdAdTec8c40tNjuozluyfOQOjBw";

        HttpResponse<JsonNode> response = Unirest.get(host)
                .header("x-rapidapi-host", xRapidApiHost)
                .header("x-rapidapi-key", xRapidApiKey)
                .header("authorization", authorization)
                .asJson();

        JSONObject obj = new JSONObject(response.getBody().toString());
        String name;
        String desc;
        String parkLat = null;
        String parkLng = null;
        int parkCode;
        int sand = 0;
        int hardPack = 0;
        int gravle = 0;
        int roots = 0;
        int jumps = 0;
        int rhythmSection = 0;
        int largeRocks =0;
        int mud = 0;
        int hillClimb = 0;
        boolean motorcycle;
        boolean atv;
        boolean jeep;
        boolean sxs;
        int levelBeginner = 0;
        int levelAdvanced = 0;
        int levelIntermediate = 0;
        int levelExpert = 0;

        Park aPark = null;

        JSONArray detailedInfo = (JSONArray) obj.getJSONArray("data");
        for (int i = 0; i < detailedInfo.length(); i++) {
            name = detailedInfo.getJSONObject(i).getString("Name");
            desc = detailedInfo.getJSONObject(i).getString("Desc");
            parkLat = detailedInfo.getJSONObject(i).getString("Lat");
            parkLng = detailedInfo.getJSONObject(i).getString("Lng");
            parkCode = detailedInfo.getJSONObject(i).getInt("Id");
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Sand")) != null) {
//                sand = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Sand");
//            }
//
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("HardPack")) != null) {
//                hardPack = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("HardPack");
//            }
//
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Gravle")) != null) {
//                gravle = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Gravle");
//            }
//
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Roots")) != null) {
//                roots = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Roots");
//            }
//
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Jumps")) != null) {
//                jumps = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Jumps");
//            }
//
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("RhythmSection")) != null) {
//                rhythmSection = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("RhythmSection");
//            }
//
//            largeRocks = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("LargeLooseRocks");
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("LargeLooseRocks")) != null) {
//
//            }
//
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Mud")) != null) {
//                mud = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("Mud");
//            }
//
//            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("HillClimb")) != null) {
//                hillClimb = detailedInfo.getJSONObject(i).getJSONObject("terrain").getInt("HillClimb");
//            }
            motorcycle = detailedInfo.getJSONObject(i).getJSONObject("permittedVehicles").getBoolean("Motorcycle");
            atv = detailedInfo.getJSONObject(i).getJSONObject("permittedVehicles").getBoolean("ATV");
            jeep = detailedInfo.getJSONObject(i).getJSONObject("permittedVehicles").getBoolean("Jeep");
            sxs = detailedInfo.getJSONObject(i).getJSONObject("permittedVehicles").getBoolean("SxS");

            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("experienceLevel").getInt("Beginner")) != null) {
                levelBeginner = detailedInfo.getJSONObject(i).getJSONObject("experienceLevel").getInt("Beginner");
            }

            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("experienceLevel").getInt("Advanced")) != null) {
                levelAdvanced = detailedInfo.getJSONObject(i).getJSONObject("experienceLevel").getInt("Advanced");
            }

            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("experienceLevel").getInt("Intermediate")) != null) {
                levelIntermediate = detailedInfo.getJSONObject(i).getJSONObject("experienceLevel").getInt("Intermediate");
            }

            if (String.valueOf(detailedInfo.getJSONObject(i).getJSONObject("experienceLevel").getInt("Expert")) != null) {
                levelExpert = detailedInfo.getJSONObject(i).getJSONObject("experienceLevel").getInt("Expert");
            }


            aPark = new Park(name, desc, parkLat, parkLng, parkCode, sand, hardPack, gravle, roots, jumps, rhythmSection, largeRocks, mud, hillClimb, motorcycle, atv, jeep, sxs, levelBeginner, levelAdvanced, levelIntermediate, levelExpert);
    }

        String weatherURL = "https://dark-sky.p.rapidapi.com/" + parkLat + "," + parkLng + "?lang=en&units=auto&exclude=minutely%252Chourly%252Calerts%252Cflags";
        HttpResponse<JsonNode> weatherResponse = Unirest.get(weatherURL)
                .header("x-rapidapi-host", "dark-sky.p.rapidapi.com")
                .header("x-rapidapi-key", "daJQtrmBjimshXqYFh1HBrEKaQZop1ERXVXjsnp8CgR0U1Me4u")
                .asJson();

        JSONObject weatherObj = new JSONObject(weatherResponse.getBody().toString());
        JSONArray weatherDetails = (JSONArray) weatherObj.getJSONObject("daily").getJSONArray("data");

        double highTempDouble;
        int highTemp;
        double lowTempDouble;
        int lowTemp;
        double rainChance;
        int percentage;
        String image;
        Weather someWeather = null;

        ArrayList<Weather> weatherList = new ArrayList<>();
        for (int i = 0; i < weatherDetails.length(); i++) {
            highTempDouble = weatherDetails.getJSONObject(i).getDouble("temperatureMax");
            highTemp = (int)highTempDouble;
            lowTempDouble = weatherDetails.getJSONObject(i).getDouble("temperatureLow");
            lowTemp = (int)lowTempDouble;
            rainChance = weatherDetails.getJSONObject(i).getDouble("precipProbability") * 100 ;
            image = weatherDetails.getJSONObject(i).getString("icon").replace("-", "_").toUpperCase();
            percentage = (int)rainChance;

            someWeather = new Weather(highTemp, lowTemp, image, percentage);
            weatherList.add(someWeather);
        }

        model.addAttribute("park", aPark);
        model.addAttribute("forecast", weatherList);
        return "park-view";
    }
}




