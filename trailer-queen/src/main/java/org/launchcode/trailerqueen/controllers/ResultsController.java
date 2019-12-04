package org.launchcode.trailerqueen.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.launchcode.trailerqueen.models.Park;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        String parkLat;
        String parkLng;
        int parkCode;
        JSONObject hazards;
        JSONObject type;
        JSONObject level;
        Park aPark = null;

        JSONArray detailedInfo = (JSONArray) obj.getJSONArray("data");
        for (int i = 0; i < detailedInfo.length(); i++) {
            name = detailedInfo.getJSONObject(i).getString("Name");
            desc = detailedInfo.getJSONObject(i).getString("Desc");
            parkLat = detailedInfo.getJSONObject(i).getString("Lat");
            parkLng = detailedInfo.getJSONObject(i).getString("Lng");
            parkCode = detailedInfo.getJSONObject(i).getInt("Id");
            hazards = detailedInfo.getJSONObject(i).getJSONObject("terrain");
            type = detailedInfo.getJSONObject(i).getJSONObject("permittedVehicles");
            level = detailedInfo.getJSONObject(i).getJSONObject("experienceLevel");

            aPark = new Park(name, desc, parkLat, parkLng, parkCode, hazards, type, level);
    }

        model.addAttribute("park", aPark);
        return "park-view";
    }
}




