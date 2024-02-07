package com.alpermelkeli.personalblog.uı.skills;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.uı.skills.view.CustomPolygonView;
import com.alpermelkeli.personalblog.viewmodel.SkillsViewModel;


import butterknife.BindView;
import butterknife.ButterKnife;

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


        // TODO Fix issue here
        skillsViewModel.getSkillLiveData().observe(this, skills -> {
            for (int i = 0; i < skills.size(); i++) {
                float result = (float) skills.get(i).getProgress() / 100;
                System.out.println(result);
                //customPolygonView.addCorner(i+1, result,skills.get(i).getName());
            }

        });
        customPolygonView.addCorner(1,0.6f,"Flask");
        customPolygonView.addCorner(2,0.75f,"Python");
        customPolygonView.addCorner(3,0.6f,"Java");
        customPolygonView.addCorner(4,0.65f,"Android");
        customPolygonView.addCorner(5,0.6f,"XML");
        customPolygonView.addCorner(6,0.4f,"Data");
        customPolygonView.addCorner(7,0.75f, "JSON");
        customPolygonView.addCorner(8,0.55f,"WebServices");


        skillsViewModel.loadSkills();
    }



}

