package com.alpermelkeli.personalblog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alpermelkeli.personalblog.model.Project;
import com.alpermelkeli.personalblog.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjectViewModel extends ViewModel {
    private final ProjectRepository projectRepository;
    private MutableLiveData<List<Project>> projectsLiveData;

    private boolean isLoaded = false;


    public ProjectViewModel() {
        projectRepository = new ProjectRepository();
        projectsLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Project>> getProjectsLiveData() {
        return projectsLiveData;
    }

    public void loadProjects() {
        // Get projects from firebase and set projectsLiveData value if haven't get data before.
        if (!isLoaded){
            projectRepository.getProjects(projects -> projectsLiveData.setValue(projects)
            );
            isLoaded = true;
        }
    }
}