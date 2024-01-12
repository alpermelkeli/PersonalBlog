package com.alpermelkeli.personalblog.uı.projects.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.alpermelkeli.personalblog.R;

public class HomeScreen extends AppCompatActivity {
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Fragment'i yükle
        loadProjectFragment();
    }

    private void loadProjectFragment() {
        Fragment projectFragment = new ProjectFragment(); // Projeleri gösteren Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homescreenFragmentLayout, projectFragment);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        loadProjectFragment();
        Toast.makeText(HomeScreen.this,"Press double to exit app.",Toast.LENGTH_SHORT).show();
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 100);
    }
}
