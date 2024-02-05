package com.alpermelkeli.personalblog.uı.navigate.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.uı.education.view.EducationActivity;
import com.alpermelkeli.personalblog.uı.experience.view.ExperienceActivity;
import com.alpermelkeli.personalblog.uı.projects.view.ProjectsActivity;
import com.alpermelkeli.personalblog.uı.skills.view.SkillsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.myProjectButton)
    AppCompatButton myProjectButton;
    @BindView(R.id.skillsButton)
    AppCompatButton skillsButton;
    @BindView(R.id.experienceButton)
    AppCompatButton experienceButton;
    @BindView(R.id.educationButton)
    AppCompatButton educationButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        clickListeners();




    }
    private void clickListeners(){
        myProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(new ProjectsActivity());
            }
        });
        skillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(new SkillsActivity());
            }
        });
        experienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(new ExperienceActivity());

            }
        });
        educationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(new EducationActivity());
            }
        });
    }

    private void redirectActivity(AppCompatActivity activity){
        Intent intent = new Intent(this, activity.getClass());
        startActivity(intent);
    }


}