package com.example.gostudy.adapters;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
//        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.item_study_course, parent, false);
//        RecyclerView.ViewHolder viewHolder = new CreatePlanViewHolder(view);
//        return viewHolder;
        // below created on 4/14
        View view = LayoutInflater.from(context).inflate(R.layout.item_study_course, parent, false);
        return new ViewHolder(view);
        //above created on 4/14
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //below added on 4/14
        Course course = courses.get(position);
        holder.bind(course);
        //above added on 4/14
//        CreatePlanViewHolder viewHolder = (CreatePlanViewHolder) holder;
//        configureViewHolder(viewHolder, position);
//        ImageView ivAdd = viewHolder.getivAdd();
//        ImageView ivSubtract = viewHolder.getivSubtract();
        
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    // below added on 4/14
    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCourseName;
        public TextView etGoalHour;
        public TextView tvHourPerWeek;
        public ImageView ivAdd;
        public ImageView ivSubtract;
        public EditText etHour;

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
            // Bind the post data to the view elements
            ivAdd = CreatePlanViewHolder.getivAdd();
            ivSubtract = CreatePlanViewHolder.getivSubtract();
            CreatePlanViewHolder.setetHour(0);
            etHour = CreatePlanViewHolder.getetHour();
            tvCourseName = CreatePlanViewHolder.gettvCourseName();
            etGoalHour = CreatePlanViewHolder.setetGoalHour(Integer.parseInt(course.getCredits()));
            tvHourPerWeek = CreatePlanViewHolder.gettvHourPerWeek();

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
    //above added on 4/14


//    private void configureViewHolder(CreatePlanViewHolder viewHolder, int position) {
//        //TODO: finish the adaptor
//        //viewHolder.gettvCourseName().setText(courses.get(position).getName());
//    }
}


