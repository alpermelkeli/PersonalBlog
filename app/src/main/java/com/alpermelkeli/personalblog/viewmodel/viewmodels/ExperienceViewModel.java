package com.alpermelkeli.personalblog.viewmodel.viewmodels;

import com.alpermelkeli.personalblog.model.Experience;
import com.alpermelkeli.personalblog.repository.ExperienceRepository;
import com.alpermelkeli.personalblog.viewmodel.BaseViewModel;

public class ExperienceViewModel extends BaseViewModel<Experience, ExperienceRepository> {

    public ExperienceViewModel() {
        super(new ExperienceRepository());
    }

    @Override
    public void loadItems() {
        repository.getExperiences(experienceList -> {
            liveData.setValue(experienceList);
        });
    }
}
