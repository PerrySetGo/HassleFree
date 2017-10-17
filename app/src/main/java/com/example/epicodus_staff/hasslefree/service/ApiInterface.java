package com.example.epicodus_staff.hasslefree.service;


import com.example.epicodus_staff.hasslefree.models.Location;
import com.example.epicodus_staff.hasslefree.models.SearchResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("/v3/businesses/search")
    Call<SearchResponse> getBusinessSearch(@QueryMap Map<String, String> params);
}
