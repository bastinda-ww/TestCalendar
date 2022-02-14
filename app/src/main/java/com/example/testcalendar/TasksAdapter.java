package com.example.testcalendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.ZoneId;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder>{
    private int numberItems;
    private String [] title;
    private ViewGroup parent;
    Context con;
    public TasksAdapter(String [] title){
        numberItems = 31;
        this.title = title;
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.event_list_item;
        this.parent = parent;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        //taskViewHolder.listItemTimeView.setText("some time will be  here");
        //taskViewHolder.listItemTitleView.setText("title" + viewHolderCount);
        //viewHolderCount++;
        return(taskViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //holder.bind("Time " + String.valueOf(viewHolderCount),"Title " + position);
        holder.bind(position + ":00", title[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                System.out.println("HELLO");
                MainActivity.listOfTask.forEach(task -> {
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(task.getStartDate().toInstant(), ZoneId.systemDefault());
                    if(localDateTime.getHour() == position) {
                        DescriptionActivity.task = task;
                    }
                });

                parent.getContext().startActivity(new Intent(parent.getContext(), DescriptionActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView listItemTimeView;
        TextView listItemTitleView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            listItemTimeView = itemView.findViewById(R.id.itemTime);
            listItemTitleView = itemView.findViewById(R.id.itemTitle);

        }

        void bind (String itemTime, String itemTitle){
            listItemTimeView.setText(itemTime);
            listItemTitleView.setText(itemTitle);
        }

    }
}
