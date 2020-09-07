package com.example.gadspracticeproject.models;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGetHandler {
    private static Retrofit retrofit;
    private static  final String BASE_URL = "https://gadsapi.herokuapp.com/";

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
