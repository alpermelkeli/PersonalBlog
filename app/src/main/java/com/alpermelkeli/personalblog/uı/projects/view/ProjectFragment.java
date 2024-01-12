package com.alpermelkeli.personalblog.uı.projects.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpermelkeli.personalblog.R;
import com.alpermelkeli.personalblog.model.Project;
import com.alpermelkeli.personalblog.uı.projects.view.adapter.ProjectAdapter;
import com.alpermelkeli.personalblog.uı.projects.viewmodel.ProjectViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectFragment extends Fragment implements ProjectAdapter.OnItemClickListener {
    //new comment line

    private ProjectViewModel projectViewModel;
    private ProjectAdapter projectAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this,rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        projectAdapter = new ProjectAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(projectAdapter);

        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        projectViewModel.getProjectsLiveData().observe(getViewLifecycleOwner(), projects -> {
            projectAdapter.setProjects(projects);
            projectAdapter.notifyDataSetChanged();
        });

        projectViewModel.loadProjects();

        return rootView;
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

        // Fragment that I will show.
        ItemFragment itemFragment = new ItemFragment();

        //Put data to this fragment
        itemFragment.setArguments(bundle);

        replaceFragment(itemFragment);



    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.homescreenFragmentLayout, fragment);
        transaction.commit();
    }




}
