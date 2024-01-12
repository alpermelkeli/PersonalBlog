package com.alpermelkeli.personalblog.uı.projects.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alpermelkeli.personalblog.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeScreen();

    }

    public void changeScreen(){

        Intent intent = new Intent(MainActivity.this, HomeScreen.class);
        startActivity(intent);
        finish();


    }




}