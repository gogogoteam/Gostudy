package com.example.gostudy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gostudy.R;
import com.example.gostudy.ViewPlanActivity;
import com.example.gostudy.adapters.CourseAdapter;
import com.example.gostudy.models.Course;
import com.example.gostudy.models.LocalCourse;
import com.example.gostudy.models.Plan;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {

    public static final String TAG = "CreateFragment";

    private EditText etPlanName, etCourseName, etCredits;
    private Button btnSave;
    private ImageView ivAddCourse;
    private RecyclerView rvCourses;
    private List<LocalCourse> courses;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etPlanName = view.findViewById(R.id.etPlanName);
        etCourseName = view.findViewById(R.id.etCourseName);
        etCredits = view.findViewById(R.id.etCredits);
        btnSave = view.findViewById(R.id.btnSave);
        ivAddCourse = view.findViewById(R.id.ivAddCourse);
        rvCourses = view.findViewById(R.id.rvCourses);

        courses = new ArrayList<>();
        CourseAdapter courseAdapter =new CourseAdapter(getContext(), courses);
        rvCourses.setAdapter(courseAdapter);
        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));

        // Add and display new course (locally)
        ivAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etCourseName.getText().toString();
                String credits = etCredits.getText().toString();
                if (name.isEmpty() || credits.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter the course name and credits!", Toast.LENGTH_SHORT).show();
                }
                else {
                    LocalCourse localCourse = new LocalCourse(name, Float.valueOf(credits));
                    courses.add(localCourse);
                    courseAdapter.notifyDataSetChanged();
                    etCourseName.setText("");
                    etCredits.setText("");
                }
            }
        });


        // Save courses to Parse with a pointer to the plan
        // Save the plan to Parse with a pointer to User
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a plan to save
                String planName = etPlanName.getText().toString();
                if (planName.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter the plan name!", Toast.LENGTH_SHORT).show();
                } else {
                    Plan plan = new Plan();
                    plan.setPlanName(planName);
                    plan.setUser(ParseUser.getCurrentUser());
                    plan.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e!=null) {
                                Log.e(TAG, "Error while saving plan",e);
                                return;
                            }
                            Log.i(TAG,"Plan save was successful!");
                            for(int i=0;i<courses.size();i++) {
                                LocalCourse localCourse = courses.get(i);
                                Course course = new Course();
                                course.setCredits(localCourse.getCredits());
                                course.setCourseName(localCourse.getName());
                                course.setPlan(plan);
                                course.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e!=null) {
                                            Log.e(TAG, "Error while saving course",e);
                                            return;
                                        }
                                        Log.i(TAG,"Course save was successful!");
                                    }
                                });
                            }
//                            Toast.makeText(getContext(), "Succeed!",Toast.LENGTH_SHORT).show();
//
//                            // set back to blank
//                            etPlanName.setText("");
//                            etCourseName.setText("");
//                            etCredits.setText("");
//                            courses.clear();
//                            courseAdapter.notifyDataSetChanged();
                        }
                    });


                    Intent i = new Intent(getContext(), ViewPlanActivity.class);
                    getContext().startActivity(i);

                }
                
            }
        });
    }
}