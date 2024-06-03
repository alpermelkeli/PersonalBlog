package com.alpermelkeli.personalblog.viewmodel.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alpermelkeli.personalblog.model.Skill;
import com.alpermelkeli.personalblog.repository.SkillsRepository;
import com.alpermelkeli.personalblog.viewmodel.BaseViewModel;

import java.util.List;

public class SkillsViewModel extends BaseViewModel<Skill, SkillsRepository> {

    public SkillsViewModel(){
        super(new SkillsRepository());
    }
    @Override
    public void loadItems() {
        repository.getSkills(skills -> {
            liveData.setValue(skills);
        });
    }
}
