package com.alpermelkeli.personalblog.uı.projects.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.model.Project;
import com.alpermelkeli.personalblog.uı.projects.adapter.ProjectAdapter;
import com.alpermelkeli.personalblog.viewmodel.ProjectViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectFragment extends Fragment implements ProjectAdapter.OnItemClickListener {

    private ProjectViewModel projectViewModel;
    private ProjectAdapter projectAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.recentProjectCategory)
    TextView recentProjectCategory;

    @BindView(R.id.recentProjectTitleText)
    TextView recentProjectTitleText;

    @BindView(R.id.recentProjectImage)
    ShapeableImageView recentProjectImage;

    @BindView(R.id.projectProgressBar)
    ProgressBar projectProgressBar;
    @BindView(R.id.linear1)
    LinearLayout linear1;
    @BindView(R.id.linear2)
    LinearLayout linear2;
    @BindView(R.id.linear3)
    LinearLayout linear3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this,rootView);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        projectAdapter = new ProjectAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(projectAdapter);

        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        projectViewModel.getProjectsLiveData().observe(getViewLifecycleOwner(), projects -> {
            loadRecentProject(projects.get(0)); // Load top of the UI with recent project
            projectAdapter.setProjects(projects);
            projectAdapter.notifyDataSetChanged();
            projectProgressBar.setVisibility(View.GONE);
            linear1.setVisibility(View.VISIBLE);
            linear2.setVisibility(View.VISIBLE);
            linear3.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        });

        projectViewModel.loadProjects();

        return rootView;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Override
    public void onItemClick(Project project) {
        pass_to_selected_project(project);
    }
    public void pass_to_selected_project(Project project){

        Bundle bundle = new Bundle();

        bundle.putString("title",project.getTitle());

        bundle.putString("description",project.getDescription());

        bundle.putString("id", project.getProjectId());

        bundle.putString("imageUrl",project.getImageUrl());

        bundle.putString("category",project.getCategory());

        // Fragment that I will show.
        ItemFragment itemFragment = new ItemFragment();

        //Put data to this fragment
        itemFragment.setArguments(bundle);

        replaceFragmentToItem(itemFragment);



    }
    private void replaceFragmentToItem(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in,
                        R.anim.fade_out,                //I added animations.
                        R.anim.fade_in,
                        R.anim.slide_out)
        .replace(R.id.homescreenFragmentLayout, fragment)
        .addToBackStack(null);
        transaction.commit();
    }

    // I add this for load recent project to UI and it is work in viewModel get data method
    public void loadRecentProject(Project recentProject){
        recentProjectCategory.setText(recentProject.getCategory().toString());
        recentProjectTitleText.setText(recentProject.getTitle().toString());
        Glide.with(this).
                load(recentProject.getImageUrl()).
                centerCrop().
                into(recentProjectImage);

    }



}
