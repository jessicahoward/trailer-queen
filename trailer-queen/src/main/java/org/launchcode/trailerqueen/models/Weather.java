package org.launchcode.trailerqueen.models;

public class Weather {

    private Double highTemp;

    private Double lowTemp;

    private String forecastSummary;

    private Double precipChance;

    private String displayImage;

    public Weather() {
    }

    public Weather(Double highTemp, Double lowTemp, String forecastSummary, Double precipChance, String displayImage) {
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.forecastSummary = forecastSummary;
        this.precipChance = precipChance;
        this.displayImage = displayImage;
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
