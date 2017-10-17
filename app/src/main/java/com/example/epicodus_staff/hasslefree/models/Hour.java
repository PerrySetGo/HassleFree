package com.example.epicodus_staff.hasslefree.models;

/**
 * Created by epicodus_staff on 10/17/17.
 */


        import com.fasterxml.jackson.annotation.JsonGetter;

        import java.io.Serializable;
        import java.util.ArrayList;

/**
 * Created by Ranga on 2/24/2017.
 */

public class Hour implements Serializable {
    @JsonGetter("is_open_now")
    public boolean getIsOpenNow() {
        return this.isOpenNow;
    }
    public void setIsOpenNow(boolean isOpenNow) {
        this.isOpenNow = isOpenNow;
    }
    boolean isOpenNow;

    @JsonGetter("hours_type")
    public String getHoursType() {
        return this.hoursType;
    }
    public void setHoursType(String hoursType) {
        this.hoursType = hoursType;
    }
    String hoursType;

    @JsonGetter("open")
    public ArrayList<Open> getOpen() {
        return this.open;
    }
    public void setOpen(ArrayList<Open> open) {
        this.open = open;
    }
    ArrayList<Open> open;

}