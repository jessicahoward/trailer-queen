package org.launchcode.trailerqueen.models;

public class Weather {

    private int weatherId;
    private static int nextId = 1;

    private Double highTemp;

    private Double lowTemp;

    private String forecastSummary;

    private Double precipChance;

    private String displayImage;

    public Weather() {
        weatherId = nextId;
        nextId++;
    }

    public Weather(Double highTemp, Double lowTemp, String forecastSummary, Double precipChance, String displayImage) {
        this();
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.forecastSummary = forecastSummary;
        this.precipChance = precipChance;
        this.displayImage = displayImage;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public Double getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(Double highTemp) {
        this.highTemp = highTemp;
    }

    public Double getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(Double lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getForecastSummary() {
        return forecastSummary;
    }

    public void setForecastSummary(String forecastSummary) {
        this.forecastSummary = forecastSummary;
    }

    public Double getPrecipChance() {
        return precipChance;
    }

    public void setPrecipChance(Double precipChance) {
        this.precipChance = precipChance;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }
}
