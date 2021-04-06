package com.example.gostudy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gostudy.LoginActivity;
import com.example.gostudy.MainActivity;
import com.example.gostudy.R;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView tvUsername, tvUserId, tvUserDescription;
    private ImageView ivAvatar, ivBackground;
    private Button btnLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUsername=view.findViewById(R.id.tvUsername);
        tvUserId=view.findViewById(R.id.tvUserId);
        tvUserDescription =view.findViewById(R.id.tvUserDescription);
        ivAvatar=view.findViewById(R.id.ivAvatar);
        ivBackground=view.findViewById(R.id.ivBackground);
        btnLogout=view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        // request and set personal info
        ParseUser parseUser = ParseUser.getCurrentUser();
        tvUsername.setText(parseUser.getUsername());
        tvUserId.setText(parseUser.getObjectId());
        tvUserDescription.setText("");

    }
}