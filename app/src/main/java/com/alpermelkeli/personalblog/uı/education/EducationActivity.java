package com.alpermelkeli.personalblog.uı.education;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.model.Education;
import com.alpermelkeli.personalblog.uı.education.adapter.EducationViewPagerAdapter;
import com.alpermelkeli.personalblog.viewmodel.viewmodels.EducationViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EducationActivity extends AppCompatActivity {
    @BindView(R.id.progressBarEducation)
    ProgressBar progressBarEducation;
    @BindView(R.id.androidText)
    TextView androidText;
    @BindView(R.id.viewPagerAndroid)
    ViewPager2 viewPagerAndroid;

    @BindView(R.id.viewPagerDataScience)
    ViewPager2 viewPagerDataScience;
    @BindView(R.id.dataScienceText)
    TextView dataScienceText;
    EducationViewModel educationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        educationViewModel = new ViewModelProvider(this).get(EducationViewModel.class);

        educationViewModel.getLiveData().observe(this, educationList -> {

            List<Education> androidEducationList = new ArrayList<>();
            List<Education> dataScienceEducationList = new ArrayList<>();
            for(Education education: educationList){
                if(education.getCategory().equals("Android")){
                    androidEducationList.add(education);
                }
                else if (education.getCategory().equals("Data Science")){
                    dataScienceEducationList.add(education);
                }
            }

            EducationViewPagerAdapter dataScienceAdapter = new EducationViewPagerAdapter(dataScienceEducationList);
            EducationViewPagerAdapter androidAdapter = new EducationViewPagerAdapter(androidEducationList);
            viewPagerAndroid.setAdapter(androidAdapter);
            viewPagerDataScience.setAdapter(dataScienceAdapter);

            progressBarEducation.setVisibility(View.GONE);
            androidText.setVisibility(View.VISIBLE);
            dataScienceText.setVisibility(View.VISIBLE);

        });

        educationViewModel.loadItems();

    }
}
