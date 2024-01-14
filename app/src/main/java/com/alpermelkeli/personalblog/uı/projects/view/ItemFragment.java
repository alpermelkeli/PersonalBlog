package com.alpermelkeli.personalblog.uı.projects.view;

import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alpermelkeli.personalblog.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemFragment extends Fragment {

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

}