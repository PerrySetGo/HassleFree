package com.example.epicodus_staff.hasslefree.models;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Created by epicodus_staff on 10/17/17.
 */

public class Center
{
    @JsonGetter("latitude")
    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    double latitude;

    @JsonGetter("longitude")
    public double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    double longitude;
}
