package com.example.degreeplanner.ui.requirements;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;

import java.util.ArrayList;

public class RequirementAdapter extends RecyclerView.Adapter<RequirementAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Course> courses;
    LayoutInflater mInflater;
    String mCategory;
    FragmentManager mFragmentManager;
    SharedHomeViewModel mViewModel;

    /*
     * Constructor for adapter
     */
    public RequirementAdapter(Context context, ArrayList<Course> courses, String category,
                              FragmentManager fragmentManager, SharedHomeViewModel viewModel) {
        this.mInflater = LayoutInflater.from(context);
        this.courses = courses;
        mContext = context;
        mCategory = category;
        mFragmentManager = fragmentManager;
        mViewModel = viewModel;
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
        setBackgroundColor(mCategory, myTextView);
        myTextView.setOnClickListener(view -> {
            // Show dialog that allows removal of a course
            removeCourseDialog(myCourse, position);
        });
    }

    public void removeCourseDialog(Course course, int mPosition) {
        String courseDept = course.getDept();
        String courseCode = course.getCode();
        String gradingOption = course.getOption().toString();
        Bundle courseBundle = new Bundle();
        courseBundle.putString("Department", courseDept);
        courseBundle.putString("Code", courseCode);
        courseBundle.putString("Grading Option", gradingOption);
        DialogFragment dialog = new RemoveCourseDialog
                (mContext, courseBundle, this, mPosition, courses.size(), mViewModel);
        dialog.show(mFragmentManager, "Remove Course");
        RemoveCourseDialog updateDialog = (RemoveCourseDialog) dialog;
        if (updateDialog.getCourseRemoved()) {
            courses.remove(mPosition);
        }
    }

    /*
     * Sets the background color of the text based on category
     */
    public void setBackgroundColor(String category, TextView textView) {
        switch (mCategory) {
            case "Major":
                textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.baby_pink));
                break;
            case "Minor":
                textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.dark_salmon));
                break;
            case "College":
                textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_green));
                break;
            case "University":
                textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.midnight_green_reduced_opacity));
                break;
            default:
                textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.baby_pink));
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
