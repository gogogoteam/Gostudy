package com.example.gostudy.view_holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gostudy.R;

public class CreatePlanViewHolder extends RecyclerView.ViewHolder{

    TextView tvCourseName, tvGoalHour, tvHour;
    RelativeLayout container;
    ImageView ivAdd, ivSubtract;

    public CreatePlanViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCourseName = itemView.findViewById(R.id.tvCourseName);
        tvGoalHour = itemView.findViewById(R.id.tvGoalHour);
        tvHour = itemView.findViewById(R.id.tvHour);
        container = itemView.findViewById(R.id.container);
        ivAdd = itemView.findViewById(R.id.ivAdd);
        ivSubtract = itemView.findViewById(R.id.ivSubtract);
    }

    public RelativeLayout getContainer() {
        return container;
    }

    public TextView gettvCourseName() {
        return tvCourseName;
    }

    public TextView gettvGoalHour() {
        return tvGoalHour;
    }

    public TextView gettvHour() {
        return tvHour;
    }

    public void settvHour(String addOrSub) {
        if (addOrSub ==  "add"){
            tvHour.setText(Integer.parseInt(tvHour.getText().toString()) + 1);
        }
        else if(addOrSub ==  "sub"){
            if (Integer.parseInt(tvHour.getText().toString()) == 0){
                return;
            }
            else{
                tvHour.setText(Integer.parseInt(tvHour.getText().toString()) - 1);
            }
        }

    }

    public ImageView getivAdd() {
        return ivAdd;
    }

    public ImageView getivSubtract() {
        return ivSubtract;
    }

}
