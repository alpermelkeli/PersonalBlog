package com.alpermelkeli.personalblog.uı.experience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alpermelkeli.personalblog.R;

import butterknife.ButterKnife;

public class ExperienceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        ButterKnife.bind(this);




    }
}