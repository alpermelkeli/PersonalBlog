package com.alpermelkeli.personalblog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import com.alpermelkeli.personalblog.model.Education;
import com.alpermelkeli.personalblog.repository.EducationRepository;

public class EducationViewModel extends ViewModel {
    EducationRepository educationRepository;
    MutableLiveData<List<Education>> educationLiveData;

    public EducationViewModel(){
        this.educationRepository = new EducationRepository();
        this.educationLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Education>> getEducationList(){
        return educationLiveData;
    }

    public void loadEducations(){
        educationRepository.getEducations(educationList -> {
            educationLiveData.setValue(educationList);
        });


    }







}
