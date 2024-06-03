package com.alpermelkeli.personalblog.repository;

import androidx.annotation.NonNull;

import com.alpermelkeli.personalblog.model.Project;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository extends Repository{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Project> projects = new ArrayList<>();
    public void getProjects(ProjectCallback callback) {
        db.collection("Projects").orderBy("id", Query.Direction.DESCENDING) // Projects ordered by id descending
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){

                                Project newProject = new Project();
                                newProject.setProjectId(document.getId().toString());
                                newProject.setDescription(document.get("description").toString());
                                newProject.setTitle(document.get("title").toString());
                                newProject.setImageUrl(document.get("imageUrl").toString());
                                newProject.setCategory(document.get("category").toString()); // added category feature
                                projects.add(newProject);

                            }
                            callback.onProjectsLoaded(projects);

                        }
                    }
                });


    }

    public interface ProjectCallback {
        void onProjectsLoaded(List<Project> projects);
    }
}
