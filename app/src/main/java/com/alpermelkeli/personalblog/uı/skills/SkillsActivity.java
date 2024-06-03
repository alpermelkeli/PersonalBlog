package com.alpermelkeli.personalblog.uı.skills;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.uı.skills.view.Corner;
import com.alpermelkeli.personalblog.uı.skills.view.CustomPolygonView;
import com.alpermelkeli.personalblog.viewmodel.viewmodels.SkillsViewModel;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
public class SkillsActivity extends AppCompatActivity {
    @BindView(R.id.customPolygonView)
    CustomPolygonView customPolygonView;

    private SkillsViewModel skillsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        skillsViewModel = new ViewModelProvider(this).get(SkillsViewModel.class);


        skillsViewModel.getLiveData().observe(this, skills -> {
            List<Corner> cornerList = new ArrayList<>();
            for (int i = 0; i < skills.size(); i++) {
                cornerList.add(new Corner(i+1,skills.get(i).getProgress(), skills.get(i).getName()));
            }
            customPolygonView.addAllCorners(cornerList);
        });



        skillsViewModel.loadItems();
    }



}

