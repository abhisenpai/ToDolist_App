package com.example.android.to_do_list;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomView> {

    ArrayList<Task> tasks;


    public CustomAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public CustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {

        holder.Bind(position);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class CustomView extends RecyclerView.ViewHolder {

        public TextView name, time;

        public CustomView(View itemView) {

            super(itemView);

            name = itemView.findViewById(R.id.taskName);
            time = itemView.findViewById(R.id.taskTime);


        }

        public void Bind(int pos) {
            name.setText(tasks.get(pos).getName());
            time.setText(tasks.get(pos).getTime());


        }
    }

}
