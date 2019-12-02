package org.launchcode.trailerqueen.models;

import org.json.JSONObject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private String latitude;

    private String longitude;

    private JSONObject listedTerrain;

    private JSONObject allowedVehicles;

    private JSONObject experienceLevel;

    public Park() {
    }

    public Park(String name, String description, String latitude, String longitude, JSONObject listedTerrain, JSONObject allowedVehicles, JSONObject experienceLevel) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.listedTerrain = listedTerrain;
        this.allowedVehicles = allowedVehicles;
        this.experienceLevel = experienceLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public JSONObject getListedTerrain() {
        return listedTerrain;
    }

    public void setListedTerrain(JSONObject listedTerrain) {
        this.listedTerrain = listedTerrain;
    }

    public JSONObject getAllowedVehicles() {
        return allowedVehicles;
    }

    public void setAllowedVehicles(JSONObject allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }

    public JSONObject getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(JSONObject experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
}
