package com.example.epicodus_staff.hasslefree.service;

import com.example.epicodus_staff.hasslefree.Constants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient

{

    private static final String YELP_API_BASE_URL = "https://api.yelp.com";

    private OkHttpClient httpClient;

    public ApiInterface getYelpFusionApi() {

        // prefix each request with the token.
        httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", Constants.YELP_TOKEN)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
    //// TODO: 10/17/17  switch jackson to gson

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YELP_API_BASE_URL)
                .addConverterFactory(getJacksonFactory())
                .client(this.httpClient)
                .build();
        return retrofit.create(ApiInterface.class);
    }

    private static JacksonConverterFactory getJacksonFactory(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return JacksonConverterFactory.create(mapper);
    }

}