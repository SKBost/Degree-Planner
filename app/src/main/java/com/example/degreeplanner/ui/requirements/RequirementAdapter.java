package com.example.degreeplanner.ui.requirements;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;

import java.util.ArrayList;

public class RequirementAdapter extends RecyclerView.Adapter<RequirementAdapter.ViewHolder> {
    ArrayList<Course> courses;
    LayoutInflater mInflater;

    /*
     * Constructor for adapter
     */
    public RequirementAdapter(Context context, ArrayList<Course> courses) {
        this.mInflater = LayoutInflater.from(context);
        this.courses = courses;
    }

    /*
     * Specifies ViewHolder which gives access to necessary views
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        /*
         * Constructor for ViewHolder
         */
        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    /*
     * inflates the layout from xml when needed
     */
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.view_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView myTextView = holder.textView;
        Course myCourse = courses.get(position);
        myTextView.setText(myCourse.getDept() + " " + myCourse.getCode());
        Log.e("RequirementsFragment", "AAAAAAH" + " " + myCourse.getDept() + " " + myCourse.getCode());
    }

    /*
     * Total number of rows
     */
    @Override
    public int getItemCount() {
        Log.e("adapter", "This is size: " + courses.size());
        return this.courses.size();
    }
}
