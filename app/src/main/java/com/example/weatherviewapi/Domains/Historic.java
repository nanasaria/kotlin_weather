package com.example.weatherviewapi.Domains;

public class Historic {
    private String day;
    private String picPath;
    private int humidity;
    private int temperature;

    public Historic(String day, String picPath, int humidity, int temperature) {
        this.day = day;
        this.picPath = picPath;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
