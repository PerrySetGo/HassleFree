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

@SuppressWarnings("InstanceVariableMayNotBeInitialized")
public class MainActivity extends AppCompatActivity {
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
        params.put("term", "indian food");
        params.put("latitude", "40.581140");
        params.put("longitude", "-111.914184");


        ApiInterface client = apiClient.getYelpFusionApi();
        Call<SearchResponse> call = client.getBusinessSearch(params);

        try {
            Response<SearchResponse> response = call.execute();
            ArrayList<Business> allBusinesses = response.body().getBusinesses();

            for (Business business : allBusinesses){
                Log.d("TESTING", business.getName());
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}