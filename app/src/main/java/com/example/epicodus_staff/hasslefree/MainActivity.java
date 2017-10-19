package com.example.epicodus_staff.hasslefree;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//import com.example.epicodus_staff.hasslefree.service.ApiClient;
//import com.example.epicodus_staff.hasslefree.service.ApiInterface;
import com.example.epicodus_staff.hasslefree.models.Business;
import com.example.epicodus_staff.hasslefree.models.SearchResponse;
import com.example.epicodus_staff.hasslefree.service.ApiClient;
import com.example.epicodus_staff.hasslefree.service.ApiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

@SuppressWarnings("InstanceVariableMayNotBeInitialized")
public class MainActivity extends AppCompatActivity  implements OnMapReadyCallback{
    ArrayList<Business> allBusinesses;

    GoogleMap mMap;
    LatLngBounds.Builder builder = new LatLngBounds.Builder();

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ApiClient apiClient = new ApiClient();

        Map<String, String> params = new HashMap<>();

// general params
        params.put("terms", "restaurants");
        params.put("attributes", "gender_neutral_restrooms");
        params.put("location", "Portland, OR");

    //does this need to be async?
        ApiInterface client = apiClient.getYelpFusionApi();
        Call<SearchResponse> call = client.getBusinessSearch(params);

        try {
            Response<SearchResponse> response = call.execute();
            allBusinesses = response.body().getBusinesses();


        }
        catch (IOException e){
            e.printStackTrace();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.setHasOptionsMenu(true);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        //how do we get businesses here?




        //map.setMyLocationEnabled(true); //checkpermission here

        //build markers out of business lat lng here



    //change for loop to shorthand for loop
        for (int i = 0; i < allBusinesses.size(); i++ ) {
            Business currentBusiness = allBusinesses.get(i);
            LatLng currentLatLng = new LatLng(currentBusiness.getCoordinates().getLatitude(), allBusinesses.get(i).getCoordinates().getLongitude()));
            mMap.addMarker(new MarkerOptions().position(currentLatLng).title(currentBusiness.getName()).snippet(currentLoc.getAddress()+ "\n\n" + currentLoc.getDescription()));
            builder.include(currentLatLng);

        }

        LatLngBounds bounds = builder.build();

        int padding = 50; // offset from edges of the map in px
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);
        //display markers
        //draw box

    }
}