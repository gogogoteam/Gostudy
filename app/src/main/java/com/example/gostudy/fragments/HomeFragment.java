package com.example.gostudy.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gostudy.MainActivity;
import com.example.gostudy.R;
import com.example.gostudy.ViewPlanActivity;
import com.example.gostudy.adapters.PlanAdapter;
import com.example.gostudy.models.Plan;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TextView tvHomePlanName;
    private RecyclerView rvPlan;
    ParseUser user;
    List<Plan> plans;

    public HomeFragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHomePlanName = view.findViewById(R.id.tvHomePlanName);
        rvPlan = view.findViewById(R.id.rvPlan);
        user = ParseUser.getCurrentUser();
        plans = new ArrayList<>();

        PlanAdapter planAdapter = new PlanAdapter(getContext(), plans);
        rvPlan.setAdapter(planAdapter);


    }


}