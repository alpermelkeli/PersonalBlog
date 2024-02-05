package com.alpermelkeli.personalblog.uı.projects.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.alpermelkeli.personalblog.R;

public class ProjectsActivity extends AppCompatActivity {
    Fragment projectFragment;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // remove app action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_home_screen);
        projectFragment = new ProjectFragment();

        // load Fragment
        loadProjectFragment();
    }

    private void loadProjectFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homescreenFragmentLayout, projectFragment);
        transaction.commit();
    }


}
