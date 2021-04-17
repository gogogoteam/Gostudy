package com.example.gostudy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gostudy.R;
import com.example.gostudy.ViewPlanActivity;
import com.example.gostudy.fragments.HomeFragment;
import com.example.gostudy.models.Plan;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    Context context;
    List<Plan> plans;

    public PlanAdapter(Context context, List<Plan> plans){
        this.context = context;
        this.plans = plans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View planView = LayoutInflater.from(context).inflate(R.layout.item_home_plan, parent, false);
        return new ViewHolder(planView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plan plan = plans.get(position);
        holder.bind(plan);

    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout container;
        TextView tvHomePlanName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHomePlanName = itemView.findViewById(R.id.tvHomePlanName);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Plan plan) {
            tvHomePlanName.setText(plan.getPlanName());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ViewPlanActivity.class);
                    context.startActivity(i);
                }
            });
        }
    }
}
