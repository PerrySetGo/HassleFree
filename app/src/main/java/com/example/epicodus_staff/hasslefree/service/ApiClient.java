package com.example.epicodus_staff.hasslefree.service;

import com.example.epicodus_staff.hasslefree.Constants;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class ApiClient

{

    OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            Request.Builder builder = originalRequest.newBuilder().header("Authorization",Constants.YELP_TOKEN);

            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }
    }).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.yelp.com/v3/businesses/")
            .client(okHttpClient)
            .build();



}