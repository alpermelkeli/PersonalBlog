package com.alpermelkeli.personalblog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alpermelkeli.personalblog.model.Skill;
import com.alpermelkeli.personalblog.repository.SkillsRepository;

import java.util.List;

public class SkillsViewModel extends ViewModel {
    private final SkillsRepository skillsRepository;
    private MutableLiveData<List<Skill>> skillLiveData;


    public SkillsViewModel() {
        this.skillsRepository = new SkillsRepository();
        this.skillLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Skill>> getSkillLiveData(){

        return skillLiveData;

    }
    public void loadSkills(){
        skillsRepository.getSkills(skills -> {
            skillLiveData.setValue(skills);
        });
    }

}
