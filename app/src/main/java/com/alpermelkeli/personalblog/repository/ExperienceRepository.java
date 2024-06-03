package com.alpermelkeli.personalblog.repository;

import androidx.annotation.NonNull;

import com.alpermelkeli.personalblog.model.Experience;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
public class ExperienceRepository extends Repository{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Experience> experienceList = new ArrayList<>();

    public void getExperiences(ExperienceCallBack callBack){
        db.collection("Experiences").orderBy("id")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){

                            for(QueryDocumentSnapshot document : task.getResult()){

                                Experience experience = new Experience();
                                experience.setCompany(document.getString("company"));
                                experience.setTitle(document.getString("title"));
                                experience.setDate(document.getString("date"));
                                experience.setDescription(document.getString("description"));
                                experience.setImageUrl(document.getString("ImageUrl"));
                                experienceList.add(experience);
                            }

                            callBack.onExperiencesLoaded(experienceList);


                        }
                    }
                });


    }


    public interface ExperienceCallBack{

        void onExperiencesLoaded(List<Experience> experienceList);

    }




}
