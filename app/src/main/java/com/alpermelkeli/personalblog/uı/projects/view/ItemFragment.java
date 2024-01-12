package com.alpermelkeli.personalblog.uı.projects.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpermelkeli.personalblog.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemFragment extends Fragment {

    // to reachable bundle I defined here.
    Bundle bundle;

    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.descriptionText)
    TextView descriptionText;
    @BindView(R.id.projectImage)
    ImageView projectImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ButterKnife.bind(this, view);

        bundle = getArguments();
        changeText();



        return view;
    }

    public void changeText(){
        changeImageWithGlide(bundle.getString("imageUrl"));
        titleText.setText(bundle.getString("title"));
        descriptionText.setText(bundle.getString("description"));
    }

    public void changeImageWithGlide(String imageUrl){
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(projectImage);
    }


}