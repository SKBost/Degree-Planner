package com.example.degreeplanner.ui.home.quarter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.enums.GradingOption;

import java.util.ArrayList;

public class QuarterPlanAdapter extends RecyclerView.Adapter<QuarterPlanAdapter.ViewHolder> {
    ArrayList<Course> courses;
    LayoutInflater mInflater;

    /*
     * Constructor for adapter
     */
    public QuarterPlanAdapter(Context context, ArrayList<Course> courses) {
        this.mInflater = LayoutInflater.from(context);
        this.courses = courses;
    }

    /*
     * Specifies ViewHolder which gives access to necessary views
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewCourse;
        public TextView textViewGradingOption;
        public TextView textViewUnits;

        /*
         * Constructor for ViewHolder
         */
        ViewHolder(View itemView) {
            super(itemView);
            textViewCourse = (TextView) itemView.findViewById(R.id.course_name_text);
            textViewGradingOption = (TextView) itemView.findViewById(R.id.course_grading_option_text);
            textViewUnits = (TextView) itemView.findViewById(R.id.course_units_text);
        }
    }

    /*
     * inflates the layout from xml when needed
     */
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.quarter_plan_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set Course
        TextView myTextView = holder.textViewCourse;
        Course myCourse = courses.get(position);
        String courseName = myCourse.getDept() + " " + myCourse.getCode();
        myTextView.setText(courseName);
        // Set grading option
        TextView myGradingOptionTextView = holder.textViewGradingOption;
        String gradingOption;
        // Adjust for spacing if needed
        if (myCourse.getOption().equals(GradingOption.LETTER)) {
            gradingOption = "L";
        }
        else if (myCourse.getOption().equals((GradingOption.PNP))) {
            gradingOption = "P/NP";
        }
        else {
            gradingOption = "N/A";
        }
        myGradingOptionTextView.setText(gradingOption);
        // Set units
        TextView myUnitTextView = holder.textViewUnits;
        String units = Double.toString(myCourse.getUnits());
        myUnitTextView.setText(units);
    }

    /*
     * Total number of rows
     */
    @Override
    public int getItemCount() {
        return this.courses.size();
    }
}
