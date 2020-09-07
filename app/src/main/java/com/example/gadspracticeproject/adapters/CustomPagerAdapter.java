package com.example.gadspracticeproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.gadspracticeproject.R;
import com.example.gadspracticeproject.interfaces.GetLearnersAndSkillIqs;
import com.example.gadspracticeproject.models.LearnersModel;
import com.example.gadspracticeproject.models.ModelObject;
import com.example.gadspracticeproject.models.RetrofitGetHandler;
import com.example.gadspracticeproject.models.SkillsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater inflater;
    ViewGroup layout;
    RecyclerView recyclerView_learners;
    RecyclerView recyclerView_skills;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        ModelObject modelObject = ModelObject.values()[position];
        inflater = LayoutInflater.from(collection.getRootView().getContext());
        layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);
        collection.addView(layout);

        recyclerView_learners = collection.findViewById(R.id.recyclerview_learners);
        recyclerView_skills = collection.findViewById(R.id.recyclerview_skill);

        if (recyclerView_learners != null){
            GetLearnersAndSkillIqs getLearnersAndSkillIqs = RetrofitGetHandler.getRetrofit().create(GetLearnersAndSkillIqs.class);
            Call<List<LearnersModel>> call = getLearnersAndSkillIqs.getLearners();
            call.enqueue(new Callback<List<LearnersModel>>() {
                @Override
                public void onResponse(Call<List<LearnersModel>> call, Response<List<LearnersModel>> response) {
                    getLearnersDataList(response.body());
                }

                @Override
                public void onFailure(Call<List<LearnersModel>> call, Throwable t) {

                }
            });
        }if (recyclerView_skills != null){
            GetLearnersAndSkillIqs getLearnersAndSkillIqs = RetrofitGetHandler.getRetrofit().create(GetLearnersAndSkillIqs.class);
            Call<List<SkillsModel>> call = getLearnersAndSkillIqs.getSkillIqs();
            call.enqueue(new Callback<List<SkillsModel>>() {
                @Override
                public void onResponse(Call<List<SkillsModel>> call, Response<List<SkillsModel>> response) {
                    getSkillsDataList(response.body());
                }

                @Override
                public void onFailure(Call<List<SkillsModel>> call, Throwable t) {

                }
            });
        }
        return layout;
    }

    private void getSkillsDataList(List<SkillsModel> skillsIq) {
        SkillsIqRecyclerAdapter adapter = new SkillsIqRecyclerAdapter(skillsIq, mContext);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView_skills.setLayoutManager(layoutManager);
        recyclerView_skills.setAdapter(adapter);
    }

    private void getLearnersDataList(List<LearnersModel> learners) {
        LearnersRecyclerAdapter adapter = new LearnersRecyclerAdapter(learners, mContext);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView_learners.setLayoutManager(layoutManager);
        recyclerView_learners.setAdapter(adapter);
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ModelObject customPagerEnum = ModelObject.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }
}
