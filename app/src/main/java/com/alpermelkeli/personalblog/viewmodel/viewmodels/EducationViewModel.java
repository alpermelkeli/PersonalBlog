package com.alpermelkeli.personalblog.viewmodel.viewmodels;
import com.alpermelkeli.personalblog.model.Education;
import com.alpermelkeli.personalblog.repository.EducationRepository;
import com.alpermelkeli.personalblog.viewmodel.BaseViewModel;

public class EducationViewModel extends BaseViewModel<Education, EducationRepository> {
    public EducationViewModel(){
        super(new EducationRepository());
    }

    @Override
    public void loadItems(){
        repository.getEducations(educationList -> {
            liveData.setValue(educationList);
        });
    }

}
