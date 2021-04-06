package com.example.gostudy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gostudy.R;
import com.example.gostudy.models.Course;
import com.example.gostudy.models.LocalCourse;
import com.example.gostudy.view_holders.CourseViewHolder;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    List<LocalCourse> courses;

    public CourseAdapter(Context context, List<LocalCourse> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_course, parent, false);
        RecyclerView.ViewHolder viewHolder = new CourseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CourseViewHolder viewHolder = (CourseViewHolder) holder;
        configureViewHolder(viewHolder, position);
        ImageView ivCancel = viewHolder.getIvCancel();
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courses.remove(position);
                notifyDataSetChanged();
            }
        });
    }



    private void configureViewHolder(CourseViewHolder viewHolder, int position) {
        viewHolder.getTvCourseName().setText(courses.get(position).getName());
        viewHolder.getTvCredits().setText(String.valueOf(courses.get(position).getCredits()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
