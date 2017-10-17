package com.example.epicodus_staff.hasslefree.models;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Created by epicodus_staff on 10/17/17.
 */

public class Region
{
    @JsonGetter("center")
    public Center getCenter() {
        return this.center;
    }
    public void setCenter(Center center) {
        this.center = center;
    }
    Center center;
}