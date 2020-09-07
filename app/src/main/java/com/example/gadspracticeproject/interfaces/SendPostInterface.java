package com.example.gadspracticeproject.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SendPostInterface {
       @POST("" +
               "")
        @FormUrlEncoded
        Call<Void> sendPost(@Field("entry.1877115667") String firstname, @Field("entry.2006916086") String lastname, @Field("entry.1824927963") String email, @Field("entry.284483984") String githublink);
    }

