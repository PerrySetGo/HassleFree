package com.example.epicodus_staff.hasslefree.models;

/**
 * Created by epicodus_staff on 10/17/17.
 */


        import com.fasterxml.jackson.annotation.JsonGetter;

public class Category
{
    @JsonGetter("title")
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    String title;

    @JsonGetter("alias")
    public String getAlias() {
        return this.alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    String alias;

}