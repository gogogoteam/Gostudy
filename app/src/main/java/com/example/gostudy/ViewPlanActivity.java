package com.example.gostudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gostudy.adapters.CourseAdapter;
import com.example.gostudy.adapters.CreatePlanAdapter;
import com.example.gostudy.models.Course;

import java.util.List;

import static java.security.AccessController.getContext;

public class ViewPlanActivity extends AppCompatActivity {

    Context context;

    private Button btnEdit, btnExit;
    private RecyclerView rvPlan;
    private List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);

        btnEdit = findViewById(R.id.btnEdit);
        btnExit = findViewById(R.id.btnExit);
        rvPlan = findViewById(R.id.rvPlan);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
            }
        });

        CreatePlanAdapter courseAdapter =new CreatePlanAdapter(this, courses);
        rvPlan.setAdapter(courseAdapter);
        rvPlan.setLayoutManager(new LinearLayoutManager(this));




    }
}