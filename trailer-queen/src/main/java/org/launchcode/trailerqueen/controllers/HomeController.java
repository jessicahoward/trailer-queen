package org.launchcode.trailerqueen.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class HomeController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String displayHome(Model model) {
        return "home";
    }

    @RequestMapping(value = "home", method = RequestMethod.POST)
    @ResponseBody
    public String processHome(Model model, @RequestParam String zip) throws InterruptedException, ApiException, IOException, UnirestException {

        String locationSearchParam = zip;

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyC6urX1HiPnH_DH0Miwbaz3Ce2htxSS-68")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, zip).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String latDegree = gson.toJson(results[0].geometry.location.lat);
        String longDegree = gson.toJson(results[0].geometry.location.lng);
//        TODO 1: use lat/long and any additional RequestParams to access dbraap API
//        TODO 2: return dbraap results - GOT IT!
//        TODO 3: nail down query params

        String host = "https://brappdbv2.p.rapidapi.com/Parks";
        String charset = "UTF-8";
        String xRapidApiHost = "brappdbv2.p.rapidapi.com";
        String xRapidApiKey = "daJQtrmBjimshXqYFh1HBrEKaQZop1ERXVXjsnp8CgR0U1Me4u";
        String authorization = "DEj1j3JKBjzFIjN2rdAdTec8c40tNjuozluyfOQOjBw";

        String s = "Hardrock Cycle Park";
        String query = String.format("s=%s", URLEncoder.encode(s, charset));

//        ####################################################
//        ####################################################

        HttpResponse<JsonNode> response = Unirest.get(host + "?isOpen=true")
                .header("x-rapidapi-host", xRapidApiHost)
                .header("x-rapidapi-key", xRapidApiKey)
                .header("authorization", authorization)
                .asJson();

        Gson anotherGson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(response.getBody().toString());
        String prettyJsonString = anotherGson.toJson(je);


//        System.out.println(response.getStatus());
//        System.out.println(response.getHeaders().get("Content-Type"));
        return prettyJsonString;
//        return latDegree + " " + longDegree;


    }
}
