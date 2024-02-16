package com.alpermelkeli.personalblog.uı.education.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.model.Education;
import com.alpermelkeli.personalblog.uı.experience.adapter.ExperiencePagerAdapter;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class EducationViewPagerAdapter extends RecyclerView.Adapter<EducationViewPagerAdapter.EducationViewHolder> {
    private List<Education> educationList;

    public EducationViewPagerAdapter(List<Education> educationList){
        this.educationList = educationList;
    }



    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_education, parent, false);
        return new EducationViewPagerAdapter.EducationViewHolder(view);   }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        Education education = educationList.get(position);
        holder.bind(education);
    }



    @Override
    public int getItemCount() {
        return educationList.size();
    }

    static class EducationViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView fromText;
        private ShapeableImageView shapeableImageView;
        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleTextEducation);
            fromText = itemView.findViewById(R.id.fromTextEducation);
            shapeableImageView = itemView.findViewById(R.id.educationShapeableImageView);
        }

        public void bind(Education education){
            titleText.setText(education.getTitle());
            fromText.setText(education.getFrom());
            Glide.with(itemView).load(education.getImageUrl()).centerCrop().into(shapeableImageView);

        }


    }

}
