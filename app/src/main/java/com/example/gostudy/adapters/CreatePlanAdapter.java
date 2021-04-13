package com.example.gostudy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gostudy.R;
import com.example.gostudy.models.Course;
import com.example.gostudy.models.LocalCourse;
import com.example.gostudy.view_holders.CourseViewHolder;
import com.example.gostudy.view_holders.CreatePlanViewHolder;

import java.util.List;

public class CreatePlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Course> courses;

    public CreatePlanAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_study_course, parent, false);
        RecyclerView.ViewHolder viewHolder = new CreatePlanViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CreatePlanViewHolder viewHolder = (CreatePlanViewHolder) holder;
        configureViewHolder(viewHolder, position);
        ImageView ivAdd = viewHolder.getivAdd();
        ImageView ivSubtract = viewHolder.getivSubtract();
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.settvHour("add");
                notifyDataSetChanged();
            }
        });

        ivSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.settvHour("sub");
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    private void configureViewHolder(CreatePlanViewHolder viewHolder, int position) {
        //TODO: finish the adaptor
        //viewHolder.gettvCourseName().setText(courses.get(position).getName());
    }
}


