package com.alpermelkeli.personalblog.viewmodel.viewmodels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.alpermelkeli.personalblog.model.Project;
import com.alpermelkeli.personalblog.repository.ProjectRepository;
import com.alpermelkeli.personalblog.viewmodel.BaseViewModel;

public class ProjectViewModel extends BaseViewModel<Project, ProjectRepository> {
    boolean isLoaded = false;

    public ProjectViewModel() {
        super(new ProjectRepository());

    }
    @Override
    public void loadItems() {
        if (!isLoaded){
            repository.getProjects(projects -> liveData.setValue(projects)
            );
            isLoaded = true;
        }
    }
}
