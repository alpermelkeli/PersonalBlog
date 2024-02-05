package com.alpermelkeli.personalblog.uı.projects.view.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alpermelkeli.personalblog.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.imageview.ShapeableImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemFragment extends Fragment {

    private final String bionlukUrl = "https://bionluk.com/alpermelkeli";
    private final String githubUrl = "https://github.com/alpermelkeli";
    private final String linkedinUrl = "https://www.linkedin.com/in/alpermelkeli/";


    // to reachable bundle I defined here.
    Bundle bundle;

    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.descriptionText)
    TextView descriptionText;
    @BindView(R.id.layoutWithBackground)
    LinearLayout layoutWithBackground;
    @BindView(R.id.backButton)
    ImageView backButton;
    @BindView(R.id.githubLink)
    ShapeableImageView githubLink;
    @BindView(R.id.bionlukLink)
    ShapeableImageView bionlukLink;
    @BindView(R.id.linkedinLink)
    ShapeableImageView linkedinLink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ButterKnife.bind(this, view);

        // get arguments from previous fragment
        bundle = getArguments();

        // change text and photo by this arguments
        changeTextAndImage();

        // if user tap on back button return previous fragment
        returnBack();

        setOnClickForLinks();


        return view;
    }

    public void changeTextAndImage(){
        uploadImageToBackgroundWithGlide(bundle.getString("imageUrl"));
        titleText.setText(bundle.getString("title"));
        descriptionText.setText(bundle.getString("description"));
    }
    // To see backButton on photo I changed my method and I use linearlayout background instead of imageView
    public void uploadImageToBackgroundWithGlide(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        layoutWithBackground.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Placeholder handling, if needed.
                    }
                });
    }
    public void returnBack(){

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnPreviousFragment();

            }
        });

    }
    private void returnPreviousFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }
    private void setOnClickForLinks(){
        githubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToLink(githubUrl);
            }
        });
        bionlukLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToLink(bionlukUrl);
            }
        });
        linkedinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToLink(linkedinUrl);
            }
        });



    }
    private void redirectToLink(String link){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        //When user tapped on redirect link and come back again do this.
        System.out.println("Here again");
    }
}