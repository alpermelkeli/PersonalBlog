package com.alpermelkeli.personalblog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alpermelkeli.personalblog.model.Experience;
import com.alpermelkeli.personalblog.repository.ExperienceRepository;
import java.util.List;
public class ExperienceViewModel extends ViewModel {
    ExperienceRepository experienceRepository;
    MutableLiveData<List<Experience>> experienceLiveData;

    public ExperienceViewModel(){

        experienceLiveData = new MutableLiveData<>();
        experienceRepository = new ExperienceRepository();

    }

    public LiveData<List<Experience>> getExperienceLiveData(){
        return experienceLiveData;
    }

    public void loadExperiences(){
        experienceRepository.getExperiences(experienceList -> {
            experienceLiveData.setValue(experienceList);
        });
    }




}
