package com.vit.app.smartshuttle.modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cabs {

    @SerializedName("cabID")
    @Expose
    private String cabID;

    @SerializedName("gpsLatitude")
    @Expose
    private String gpsLatitude;

    @SerializedName("gpsLongitude")
    @Expose
    private String gpsLongitude;

    public Cabs(String cabID, String lat, String lon) {
        this.cabID = cabID;
        this.gpsLatitude = lat;
        this.gpsLongitude = lon;
    }

    public String getCabID() {
        return cabID;
    }

    public String getLat() {
        return gpsLatitude;
    }

    public String getLon() {
        return gpsLongitude;
    }
}
