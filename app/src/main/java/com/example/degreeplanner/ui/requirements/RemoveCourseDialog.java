package com.example.degreeplanner.ui.requirements;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.enums.GradingOption;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class RemoveCourseDialog extends DialogFragment {

    Course courseToRemove;
    boolean courseRemoved = false;
    Context myContext;
    Bundle mBundle;
    RequirementAdapter mAdapter;
    int mPosition;
    int mCourseSize;
    SharedHomeViewModel mViewModel;
    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson = new Gson();

    public RemoveCourseDialog(Context context, Bundle bundle, RequirementAdapter adapter,
                              int position, int courseSize, SharedHomeViewModel viewModel, Activity activity) {
        myContext = context;
        mBundle = bundle;
        mAdapter = adapter;
        mPosition = position;
        mCourseSize = courseSize;
        mViewModel = viewModel;
        mPrefs = activity.getPreferences(MODE_PRIVATE);
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
        this.storeInfo();
    }

    /*
     * Stores Requirement Category information locally through shared preferences
     */
    public void storeInfo() {
        String jsonSchedule = gson.toJson(mViewModel.getMySchedule());
        String jsonMajor = gson.toJson(RequirementsViewModel.majorCourses);
        String jsonMinor = gson.toJson(RequirementsViewModel.minorCourses);
        String jsonCollege = gson.toJson(RequirementsViewModel.collegeCourses);
        String jsonUniversity = gson.toJson(RequirementsViewModel.universityCourses);
        String jsonUnplanned = gson.toJson(RequirementsViewModel.unplannedCourses);
        prefsEditor = mPrefs.edit();
        prefsEditor.putString("My Schedule", jsonSchedule);
        prefsEditor.putString("My Major Courses", jsonMajor);
        prefsEditor.putString("My Minor Courses", jsonMinor);
        prefsEditor.putString("My College Courses", jsonCollege);
        prefsEditor.putString("My University Courses", jsonUniversity);
        prefsEditor.putString("My Unplanned Courses", jsonUnplanned);
        prefsEditor.commit();
    }
}
