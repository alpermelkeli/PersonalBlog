package com.alpermelkeli.personalblog.repository;

import androidx.annotation.NonNull;

import com.alpermelkeli.personalblog.model.Education;
import com.alpermelkeli.personalblog.model.Experience;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EducationRepository extends Repository{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Education> educationList = new ArrayList<>();

    public void getEducations(EducationCallBack callBack){

        db.collection("Education")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                            String title = documentSnapshot.getString("title");
                            String imageUrl = documentSnapshot.getString("imageUrl");
                            String from = documentSnapshot.getString("from");
                            String category = documentSnapshot.getString("category");
                            Education education = new Education(title,imageUrl,from,category);
                            educationList.add(education);
                        }

                        callBack.onEducationLoaded(educationList);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("There is problem on repo");
                    }
                });


    }

    public interface EducationCallBack{

        void onEducationLoaded(List<Education> educationList);

    }

}
