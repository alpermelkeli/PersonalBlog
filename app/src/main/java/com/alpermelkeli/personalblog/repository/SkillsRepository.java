package com.alpermelkeli.personalblog.repository;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.alpermelkeli.personalblog.model.Project;
import com.alpermelkeli.personalblog.model.Skill;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.ArrayList;

public class SkillsRepository extends Repository{

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Skill> skills = new ArrayList<>();

    public void getSkills(SkillsCallBack callBack){

        db.collection("Skills")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){

                                Skill newSkill = new Skill();
                                newSkill.setName(document.get("name").toString());
                                newSkill.setProgress(document.getDouble("progress"));
                                skills.add(newSkill);
                            }
                            callBack.onSkillsLoaded(skills);

                        }

                    }
                });




    }

    public interface SkillsCallBack{

        void onSkillsLoaded(List<Skill> skills);

    }



}
