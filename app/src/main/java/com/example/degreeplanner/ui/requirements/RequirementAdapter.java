package com.example.degreeplanner.ui.requirements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;

import java.util.ArrayList;

public class RequirementAdapter extends RecyclerView.Adapter<RequirementAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Course> courses;
    LayoutInflater mInflater;
    String mCategory;

    /*
     * Constructor for adapter
     */
    public RequirementAdapter(Context context, ArrayList<Course> courses, String category) {
        this.mInflater = LayoutInflater.from(context);
        this.courses = courses;
        mContext = context;
        mCategory = category;
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
            textView = itemView.findViewById(R.id.textView);
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
        String courseName = myCourse.getDept() + " " + myCourse.getCode();
        myTextView.setText(courseName);
        // Set color based on category
        if (mCategory.equals("Major")){
            //myTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.baby_pink));
        }
        if (mCategory.equals("Minor")){
            //myTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.baby_pink));
        }
        if (mCategory.equals("College")){
            //myTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.baby_pink));
        }
        if (mCategory.equals("University")){
            //myTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.baby_pink));
        }
    }

    /*
     * Total number of rows
     */
    @Override
    public int getItemCount() {
        return this.courses.size();
    }
}
