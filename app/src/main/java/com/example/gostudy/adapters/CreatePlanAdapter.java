package com.example.gostudy.adapters;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gostudy.R;
import com.example.gostudy.models.Course;
import com.example.gostudy.models.LocalCourse;
import com.example.gostudy.view_holders.CourseViewHolder;
import com.example.gostudy.view_holders.CreatePlanViewHolder;
import com.parse.ParseFile;

import java.util.List;

public class CreatePlanAdapter extends RecyclerView.Adapter<CreatePlanAdapter.ViewHolder> {

    Context context;
    List<Course> courses;

    public CreatePlanAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_study_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCourseName;
        TextView etGoalHour;
        TextView tvHourPerWeek;
        ImageView ivAdd;
        ImageView ivSubtract;
        EditText etHour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAdd = itemView.findViewById(R.id.ivAdd);
            ivSubtract = itemView.findViewById(R.id.ivSubtract);
            etHour = itemView.findViewById(R.id.etHour);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            etGoalHour = itemView.findViewById(R.id.etGoalHour);
            tvHourPerWeek = itemView.findViewById(R.id.tvHourPerWeek);
        }

        public void bind(Course course) {

            etHour.setText(course.getStudiedHours());
            tvCourseName.setText(course.getCourseName());
//            etGoalHour.setText(Integer.parseInt(course.getCredits()));
            etGoalHour.setText(String.valueOf(course.getCredits()));
            tvHourPerWeek.setText("Hours/week");

            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CreatePlanViewHolder.setetHour(Integer.parseInt(CreatePlanViewHolder.getetHour().getText().toString()) + 1);
                    notifyDataSetChanged();
                }
            });

            ivSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt(etHour.getText().toString()) != 0){
                        CreatePlanViewHolder.setetHour(Integer.parseInt(CreatePlanViewHolder.getetHour().getText().toString()) - 1);
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}


