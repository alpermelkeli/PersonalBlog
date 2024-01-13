package com.alpermelkeli.personalblog.uı.projects.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alpermelkeli.personalblog.model.Project;
import com.alpermelkeli.personalblog.repository.ProjectRepository;

import java.util.List;

public class ProjectViewModel extends ViewModel {
    private final ProjectRepository projectRepository;
    private MutableLiveData<List<Project>> projectsLiveData;

    public ProjectViewModel() {
        projectRepository = new ProjectRepository();
        projectsLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Project>> getProjectsLiveData() {
        return projectsLiveData;
    }

    public void loadProjects() {
        // Get projects from firebase and set projectsLiveData value
        projectRepository.getProjects(projects -> projectsLiveData.setValue(projects));
    }
}
