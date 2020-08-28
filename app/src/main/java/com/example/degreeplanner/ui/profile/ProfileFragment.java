package com.example.degreeplanner.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.degreeplanner.R;

public class ProfileFragment extends Fragment{

    private ProfileViewModel profileViewModel;
    // Information from welcome screen
    private String majorString;
    private String minorString;
    private String collegeString;
    private String yearEnteredString;
    private String graduatingYearString;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        // Set text
        EditText majorFragmentText = root.findViewById(R.id.edit_text_major);
        majorFragmentText.setText(profileViewModel.major);
        EditText minorFragmentText = root.findViewById(R.id.edit_text_minor);
        minorFragmentText.setText(profileViewModel.minor);
        EditText collegeFragmentText = root.findViewById(R.id.edit_text_college);
        collegeFragmentText.setText(profileViewModel.college);
        EditText yearEnteredFragmentText = root.findViewById(R.id.edit_text_year_entered);
        yearEnteredFragmentText.setText(profileViewModel.yearEntered);
        EditText numYearsFragmentText = root.findViewById(R.id.edit_text_num_years);
        numYearsFragmentText.setText(Integer.toString(profileViewModel.numYears));

        return root;
    }

}
