package com.alpermelkeli.personalblog.uı.projects.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.model.Project;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private List<Project> projects;
    private OnItemClickListener listener;

    public ProjectAdapter(List<Project> projects, OnItemClickListener listener) {
        this.projects = projects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.bind(project, listener);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public interface OnItemClickListener {
        void onItemClick(Project project);
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.titleTextView)
        TextView titleTextView;
        @BindView(R.id.projectItemImage)
        ShapeableImageView projectItemImage;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            // Normally we use just this but here our view is itemView so we write: this,itemView
            ButterKnife.bind(this,itemView); // I changed this with dataBinding
        }

        public void bind(Project project, OnItemClickListener listener) {

            titleTextView.setText(project.getTitle());

            Glide.with(itemView).load(project.getImageUrl()).centerCrop().into(projectItemImage); // I added this to load photo of item

            itemView.setOnClickListener(v -> listener.onItemClick(project));
        }


    }
}
