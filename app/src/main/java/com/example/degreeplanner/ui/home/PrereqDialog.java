package com.example.degreeplanner.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;

import java.util.ArrayList;

public class PrereqDialog extends DialogFragment {
    private SharedHomeViewModel mViewModel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(requireActivity()).get(SharedHomeViewModel.class);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
        builder.setTitle(R.string.suggested_courses)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dismiss dialog
                        PrereqDialog.this.getDialog().cancel();
                    }
                });
        ArrayList<Course> suggestedCourses = mViewModel.getMySchedule().getSuggestedCourses
                (RequirementsViewModel.unplannedCourses.getCourses(), mViewModel.getSeekBarProgress() + 1);
        ArrayList<String> suggestedCoursesString = new ArrayList<>();
        for (Course c: suggestedCourses) {
            String newCourseString = c.getDept() + " " + c.getCode();
            suggestedCoursesString.add(newCourseString);
        }
        final CharSequence[] suggestedCoursesChar;
        if (suggestedCoursesString.size() != 0) {
            suggestedCoursesChar =
                    suggestedCoursesString.toArray(new CharSequence[suggestedCoursesString.size()]);
        }
        else {
            suggestedCoursesChar = new CharSequence[1];
            suggestedCoursesChar[0] = "No courses currently suggested";
        }

        builder.setItems(suggestedCoursesChar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
