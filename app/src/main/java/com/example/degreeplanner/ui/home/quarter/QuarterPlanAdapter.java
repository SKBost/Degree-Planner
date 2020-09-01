package com.example.degreeplanner.ui.home.quarter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.enums.GradingOption;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;

import java.util.ArrayList;

public class QuarterPlanAdapter extends RecyclerView.Adapter<QuarterPlanAdapter.ViewHolder> {
    ArrayList<Course> courses;
    LayoutInflater mInflater;
    Context mContext;

    /*
     * Constructor for adapter
     */
    public QuarterPlanAdapter(Context context, ArrayList<Course> courses) {
        this.mInflater = LayoutInflater.from(context);
        this.courses = courses;
        mContext = context;
    }

    /*
     * Specifies ViewHolder which gives access to necessary views
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewCourse;
        public TextView textViewGradingOption;
        public TextView textViewUnits;
        public ImageView courseBackground;
        public Drawable unwrappedDrawable;


        /*
         * Constructor for ViewHolder
         */
        ViewHolder(View itemView) {
            super(itemView);
            textViewCourse = itemView.findViewById(R.id.course_name_text);
            textViewGradingOption = itemView.findViewById(R.id.course_grading_option_text);
            textViewUnits = itemView.findViewById(R.id.course_units_text);
            courseBackground = itemView.findViewById(R.id.course_plan_background);
            unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.course_plan_item);
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
        // Set color of background
        setBackgroundColor(myCourse, holder);
    }

    public void setBackgroundColor(Course myCourse, ViewHolder holder) {
        ImageView myCourseBackground = holder.courseBackground;
        Drawable courseDrawable = holder.unwrappedDrawable.mutate();
        switch (findCategory(myCourse)) {
            case "Major":
                courseDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(mContext, R.color.baby_pink), PorterDuff.Mode.SRC_IN));
                break;
            case "Minor":
                courseDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(mContext, R.color.dark_salmon), PorterDuff.Mode.SRC_IN));
                break;
            case "College":
                courseDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(mContext, R.color.light_green), PorterDuff.Mode.SRC_IN));
                break;
            case "University":
                courseDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(mContext, R.color.midnight_green_reduced_opacity), PorterDuff.Mode.SRC_IN));
                break;
            default:
                courseDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(mContext, R.color.orange_red), PorterDuff.Mode.SRC_IN));
        }
        myCourseBackground.setImageDrawable(courseDrawable);
    }

    /*
     * Find what category a course is in
     */
    public String findCategory(Course mCourse) {
        if (RequirementsViewModel.majorCourses.containsCourse(mCourse)) {
            return "Major";
        }
        else if (RequirementsViewModel.minorCourses.containsCourse(mCourse)) {
            return "Minor";
        }
        else if (RequirementsViewModel.collegeCourses.containsCourse(mCourse)) {
            return "College";
        }
        else {
            return "University";
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
