package com.example.weatherviewapi.Domains;

public class Current {
    private String hour;
    private int temp;
    private int picPath;

    public Current(String hour, int picPath, int temp) {
        this.hour = hour;
        this.picPath = picPath;
        this.temp = temp;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getPicPath() {
        return picPath;
    }

    public void setPicPath(int picPath) {
        this.picPath = picPath;
    }
}
