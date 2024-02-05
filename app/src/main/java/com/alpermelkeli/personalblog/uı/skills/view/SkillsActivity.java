package com.alpermelkeli.personalblog.uı.skills.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.model.Skill;
import com.alpermelkeli.personalblog.viewmodel.SkillsViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillsActivity extends AppCompatActivity {
    @BindView(R.id.skillsLinearLayout)
    LinearLayout skillsLinearLayout;
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

        skillsLinearLayout = findViewById(R.id.skillsLinearLayout);

        skillsViewModel = new ViewModelProvider(this).get(SkillsViewModel.class);

        // ViewModel'den beceri verilerini dinle
        skillsViewModel.getSkillLiveData().observe(this, skills -> {
            showSkills(skills);
        });

        skillsViewModel.loadSkills();
    }

    private void showSkills(List<Skill> skills) {
        // Önceki görünümleri temizle
        skillsLinearLayout.removeAllViews();

        // Her bir beceriyi LinearLayout'a ekleyin
        for (Skill skill : skills) {
            // item_skill.xml dosyasını kullanarak bir öğe oluşturun
            View skillView = getLayoutInflater().inflate(R.layout.item_skill, null);

            // Oluşturulan öğenin içindeki TextView, ProgressBar ve ImageView'a erişin
            TextView nameTextView = skillView.findViewById(R.id.nameTextView);
            ProgressBar skillProgressBar = skillView.findViewById(R.id.skillProgressBar);
            ImageView skillItemImage = skillView.findViewById(R.id.skillItemImage);

            // TextView, ProgressBar ve ImageView'u güncelleyin
            nameTextView.setText(skill.getName());
            skillProgressBar.setProgress(skill.getProgress());
            // Eğer varsa resmi yükle, benim için placeholder kullanıldı
            if (skill.getImageUrl() != null && !skill.getImageUrl().isEmpty()) {
                Glide.with(this).load(skill.getImageUrl()).centerCrop().into(skillItemImage);
            }

            // LinearLayout'a öğeyi ekleyin
            skillsLinearLayout.addView(skillView);
        }
    }
}

