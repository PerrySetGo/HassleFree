package com.example.epicodus_staff.hasslefree;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//import com.example.epicodus_staff.hasslefree.service.ApiClient;
//import com.example.epicodus_staff.hasslefree.service.ApiInterface;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//
//        //create the retrofit object
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.yelp.com/v3/businesses/")
//                .build();
//
//        //connect the interface
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//
//
//        Call<List<Location>> call = apiService.getLocation("97217");
//        call.enqueue(new Callback<List<Location>>() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                int statusCode = response.code();
//                Location location = response.body();
//                Log.d(TAG, response.toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<Location>> call, Throwable t) {
//                // Log error here since request failed
//            }
//        });




        try {
            YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
            YelpFusionApi yelpFusionApi;
            yelpFusionApi = apiFactory.createAPI("P7ZJ25iFtLsg6MvYUkEAVg", "5rtjmwKpsUQtzcBsRnTRtYcKYLzJbZkC2jZOSDtXTFEzUcpBtBkPTawedCcFH4es");

            Map<String, String> params = new HashMap<>();

            // general params
            params.put("attributes", "gender_neutral_bathrooms");
            params.put("location", "Portland");

            Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);


            Callback<SearchResponse> callback = new Callback<SearchResponse>() {
                @Override
                public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                    SearchResponse searchResponse = response.body();

                    Log.d("TAG",searchResponse.toString());
                    // Update UI text with the searchResponse.
                }
                @Override
                public void onFailure(Call<SearchResponse> call, Throwable t) {
                    // HTTP error happened, do something to handle it.
                }
            };

           call.enqueue(callback);
         //   Response<SearchResponse> response = call.execute();

        } catch (IOException e){
            Log.d(TAG,e.toString());
        }

        Map<String, String> params = new HashMap<>();

    }
}