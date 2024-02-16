package com.alpermelkeli.personalblog.uı.experience;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.model.Experience;
import com.alpermelkeli.personalblog.uı.experience.adapter.ExperiencePagerAdapter;
import com.alpermelkeli.personalblog.viewmodel.ExperienceViewModel;
import com.google.firebase.annotations.concurrent.Background;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExperienceActivity extends AppCompatActivity {
    ExperienceViewModel experienceViewModel;
    @BindView(R.id.viewPager2)
    ViewPager2 viewPager2;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        experienceViewModel = new  ViewModelProvider(this).get(ExperienceViewModel.class);

        experienceViewModel.getExperienceLiveData().observe(this, experienceList -> {
            ExperiencePagerAdapter adapter = new ExperiencePagerAdapter(experienceList);
            viewPager2.setAdapter(adapter);
            int pageMarginPx = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
            int offsetPx = getResources().getDimensionPixelOffset(R.dimen.pageMarginAndoffset);
            setShowSideItems(viewPager2, pageMarginPx, offsetPx);
            progressBar.setVisibility(View.GONE);

        });


        experienceViewModel.loadExperiences();
    }

    public static void setShowSideItems(ViewPager2 viewPager2, int pageMarginPx, int offsetPx) {
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);

        viewPager2.setPageTransformer((page, position) -> {
            float offset = position * -(2 * offsetPx + pageMarginPx);
            if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager2) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.setTranslationX(-offset);
                } else {
                    page.setTranslationX(offset);
                }
            } else {
                page.setTranslationY(offset);
            }
        });
    }

}