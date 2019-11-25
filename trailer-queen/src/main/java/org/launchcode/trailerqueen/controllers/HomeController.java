package org.launchcode.trailerqueen.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String displayHome(Model model) {
        return "home";
    }

    @RequestMapping(value = "home", method = RequestMethod.POST)
    @ResponseBody
    public String processHome(Model model, @RequestParam String zip) throws InterruptedException, ApiException, IOException {

        String locationSearchParam = zip;

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyC6urX1HiPnH_DH0Miwbaz3Ce2htxSS-68")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, zip).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(results[0].geometry.location);

    }
}
