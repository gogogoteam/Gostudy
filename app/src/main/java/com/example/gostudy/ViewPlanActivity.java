package com.example.gostudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.gostudy.adapters.CourseAdapter;
import com.example.gostudy.adapters.CreatePlanAdapter;
import com.example.gostudy.models.Course;
import com.example.gostudy.models.Plan;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ViewPlanActivity extends AppCompatActivity {

    Context context;

    private Button btnEdit, btnExit;
    private RecyclerView rvPlan;
    private List<Course> courses;
    private EditText etGoalHour;
    public TextView tvCourseName;
    public TextView tvHourPerWeek;

    public static final String TAG = "ViewPlanActivity";

    CreatePlanAdapter adapter;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);

        btnEdit = findViewById(R.id.btnEdit);
        btnExit = findViewById(R.id.btnExit);
        rvPlan = findViewById(R.id.rvPlan);
        tvCourseName = findViewById(R.id.tvCourseName);
        etGoalHour = findViewById(R.id.etGoalHour);
        tvHourPerWeek = findViewById(R.id.tvHourPerWeek);

        context = ViewPlanActivity.this;

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
                Toast.makeText(context, "this button is still unfinished", Toast.LENGTH_SHORT).show();
            }
        });

        courses = new ArrayList<>();
        adapter = new CreatePlanAdapter(this, courses);

        rvPlan.setAdapter(adapter);
        // 4. set the layout manger on the recycler view
        rvPlan.setLayoutManager(new LinearLayoutManager(this));
        queryCourses();
        //swipeContainer.setRefreshing(true);

        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                courses = new ArrayList<>();
                adapter = new CreatePlanAdapter(context, courses);
                rvPlan.setAdapter(adapter);
                rvPlan.setLayoutManager(new LinearLayoutManager(context));
                queryCourses();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    protected void queryCourses() {
        ParseQuery<Course> query = ParseQuery.getQuery(Course.class);
        query.include(Course.KEY_PLAN);
        Log.i(TAG, "we've got here first!");
        Plan plan = Parcels.unwrap(getIntent().getParcelableExtra("KEY_PLAN"));
        query.whereEqualTo(Course.KEY_PLAN, plan);
        Log.i(TAG, "we've got here!");
        query.findInBackground(new FindCallback<Course>() {
            @Override
            public void done(List<Course> allCourses, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting courses", e);
                    return;
                }

                courses.addAll(allCourses);
                adapter.notifyDataSetChanged();

                for (Course course : courses){
                    Log.i(TAG, "Course: " + course.getCourseName() + ", credit: " + course.getCredits());
                }

                swipeContainer.setRefreshing(false);
            }
        });
    }
}