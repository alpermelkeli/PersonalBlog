package com.alpermelkeli.personalblog.uı.experience.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.model.Experience;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ExperiencePagerAdapter extends RecyclerView.Adapter<ExperiencePagerAdapter.ExperienceViewHolder> {
    private List<Experience> experiences;

    public ExperiencePagerAdapter(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience, parent, false);
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Experience experience = experiences.get(position);
        holder.bind(experience);
    }

    @Override
    public int getItemCount() {
        return experiences.size();
    }

    static class ExperienceViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView companyTextView;
        private TextView dateTextView;
        private TextView descriptionTextView;
        private ShapeableImageView shapeableImageView;

        public ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            companyTextView = itemView.findViewById(R.id.companyTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            shapeableImageView = itemView.findViewById(R.id.experienceShapeableImageView);
        }

        public void bind(Experience experience) {
            titleTextView.setText(experience.getTitle());
            companyTextView.setText(experience.getCompany());
            dateTextView.setText(experience.getDate());
            descriptionTextView.setText(experience.getDescription());
            Glide.with(itemView).load(experience.getImageUrl()).centerCrop().into(shapeableImageView); // I added this to load photo of item

        }
    }
}
