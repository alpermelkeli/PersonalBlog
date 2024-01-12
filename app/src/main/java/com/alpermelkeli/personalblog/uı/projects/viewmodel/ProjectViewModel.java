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
        // Firestore'dan projeleri al ve LiveData'ya set et
        projectRepository.getProjects(projects -> projectsLiveData.setValue(projects));
    }
}
