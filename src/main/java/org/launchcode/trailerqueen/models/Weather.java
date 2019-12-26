package org.launchcode.trailerqueen.models;

public class Weather {

    private int highTemp;

    private int lowTemp;

    private String displayImage;

    private int precipPercentage;

    public Weather() {
    }

    public Weather(int highTemp, int lowTemp, String displayImage, int precipPercentage) {
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.displayImage = displayImage;
        this.precipPercentage = precipPercentage;
    }

    public int getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    public int getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(int lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    public int getPrecipPercentage() {
        return precipPercentage;
    }

    public void setPrecipPercentage(int precipPercentage) {
        this.precipPercentage = precipPercentage;
    }
}
