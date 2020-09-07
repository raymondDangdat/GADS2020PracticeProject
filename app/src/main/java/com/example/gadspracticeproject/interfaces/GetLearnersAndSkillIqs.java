package com.example.gadspracticeproject.interfaces;

import com.example.gadspracticeproject.models.LearnersModel;
import com.example.gadspracticeproject.models.SkillsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetLearnersAndSkillIqs {

    @GET("/api/hours")
    Call<List<LearnersModel>> getLearners();
    @GET("/api/skilliq")
    Call<List<SkillsModel>> getSkillIqs();
}