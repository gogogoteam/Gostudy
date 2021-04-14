package com.example.gostudy.view_holders;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gostudy.R;

public class CreatePlanViewHolder extends RecyclerView.ViewHolder{

    static EditText etHour, etGoalHour;
    static TextView tvCourseName;
    static RelativeLayout container;
    static ImageView ivAdd, ivSubtract;
    static TextView tvHourPerWeek;

    public CreatePlanViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCourseName = itemView.findViewById(R.id.tvCourseName);
        etGoalHour = itemView.findViewById(R.id.etGoalHour);
        etHour = itemView.findViewById(R.id.etHour);
        container = itemView.findViewById(R.id.container);
        ivAdd = itemView.findViewById(R.id.ivAdd);
        ivSubtract = itemView.findViewById(R.id.ivSubtract);
        tvHourPerWeek = itemView.findViewById(R.id.tvHourPerWeek);
    }

    public RelativeLayout getContainer() {
        return container;
    }

    public static TextView gettvCourseName() {
        return tvCourseName;
    }

    public static TextView getetGoalHour() {
        return etGoalHour;
    }

    public static EditText setetGoalHour(int credit){
        etGoalHour.setText(credit * 3);
        return (EditText) getetGoalHour();
    }

    public static TextView gettvHourPerWeek(){
        return tvHourPerWeek;
    }

    public static EditText getetHour() {
        return etHour;
    }

    public static void setetHour(int hour) {
        etHour.setText(hour);
    }

    public static ImageView getivAdd() {
        return ivAdd;
    }

    public static ImageView getivSubtract() {
        return ivSubtract;
    }

}
