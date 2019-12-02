package com.example.myride.firebase;

public class Ridefacility {
    private boolean ac=false;
    private boolean wifi=false;
    private boolean chargingPoint=false;
    private boolean tv=false;
    private boolean headset=false;
    private boolean readingLight=false;
    private boolean banket=false;
    private boolean newsPaper=false;
    public Ridefacility(){}

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isChargingPoint() {
        return chargingPoint;
    }

    public void setChargingPoint(boolean chargingPoint) {
        this.chargingPoint = chargingPoint;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isHeadset() {
        return headset;
    }

    public void setHeadset(boolean headset) {
        this.headset = headset;
    }

    public boolean isReadingLight() {
        return readingLight;
    }

    public void setReadingLight(boolean readingLight) {
        this.readingLight = readingLight;
    }

    public boolean isBanket() {
        return banket;
    }

    public void setBanket(boolean banket) {
        this.banket = banket;
    }

    public boolean isNewsPaper() {
        return newsPaper;
    }

    public void setNewsPaper(boolean newsPaper) {
        this.newsPaper = newsPaper;
    }
}
