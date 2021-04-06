package com.example.gostudy.view_holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gostudy.R;

public class CourseViewHolder extends RecyclerView.ViewHolder {

    TextView tvCourseName, tvCredits;
    ImageView ivCancel;
    RelativeLayout container;

    public CourseViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCourseName = itemView.findViewById(R.id.tvCourseName);
        tvCredits = itemView.findViewById(R.id.tvCredits);
        container = itemView.findViewById(R.id.container);
        ivCancel = itemView.findViewById(R.id.ivCancel);
    }

    public RelativeLayout getContainer() {
        return container;
    }

    public TextView getTvCourseName() {
        return tvCourseName;
    }

    public TextView getTvCredits() {
        return tvCredits;
    }

    public ImageView getIvCancel() {
        return ivCancel;
    }
}
