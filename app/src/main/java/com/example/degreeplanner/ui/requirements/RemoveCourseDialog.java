package com.example.degreeplanner.ui.requirements;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.enums.GradingOption;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;

public class RemoveCourseDialog extends DialogFragment {

    Course courseToRemove;
    boolean courseRemoved = false;
    Context myContext;
    Bundle mBundle;
    RequirementAdapter mAdapter;
    int mPosition;
    int mCourseSize;
    SharedHomeViewModel mViewModel;

    public RemoveCourseDialog(Context context, Bundle bundle, RequirementAdapter adapter,
                              int position, int courseSize, SharedHomeViewModel viewModel) {
        myContext = context;
        mBundle = bundle;
        mAdapter = adapter;
        mPosition = position;
        mCourseSize = courseSize;
        mViewModel = viewModel;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        builder.setTitle(R.string.delete_course)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    // Remove course
                    removeCourse(mBundle);
                    courseRemoved = true;
                    mAdapter.notifyItemRemoved(mPosition);
                    mAdapter.notifyItemRangeChanged(mPosition, mCourseSize);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // Dismiss dialog
                    RemoveCourseDialog.this.getDialog().cancel();
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /*
     * Getter method for courseRemoved
     */
    public boolean getCourseRemoved() {
        return courseRemoved;
    }

    /*
     * Find which RequirementCategory the course is stored in and delete it
     */
    public void removeCourse(Bundle mSavedInstanceState) {
        // Find and remove course from RequirementCategory
        courseToRemove = new Course(mSavedInstanceState.get("Department").toString(),
                mSavedInstanceState.get("Code").toString());
        String mGradingOption = mSavedInstanceState.get("Grading Option").toString();
        courseToRemove.setOption(GradingOption.valueOf(mGradingOption));
        if (RequirementsViewModel.unplannedCourses.containsCourse(courseToRemove)) {
            RequirementsViewModel.unplannedCourses.removeCourse(courseToRemove);
        }
        if (RequirementsViewModel.majorCourses.containsCourse((courseToRemove))) {
            RequirementsViewModel.majorCourses.removeCourse(courseToRemove);
        }
        else if (RequirementsViewModel.minorCourses.containsCourse((courseToRemove))) {
            RequirementsViewModel.minorCourses.removeCourse(courseToRemove);
        }
        else if (RequirementsViewModel.collegeCourses.containsCourse((courseToRemove))) {
            RequirementsViewModel.collegeCourses.removeCourse(courseToRemove);
        }
        else {
            RequirementsViewModel.universityCourses.removeCourse(courseToRemove);
        }
        // Remove course from quarter
        mViewModel.removeCourseFromQuarter(courseToRemove);
    }
}
