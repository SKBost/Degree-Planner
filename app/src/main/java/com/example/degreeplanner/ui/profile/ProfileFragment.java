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
    private int numYearsInt;
    private int yearEntered;
    private EditText numYearsText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        numYearsText = (EditText) root.findViewById(R.id.edit_text_num_years);
        numYearsInt = Integer.parseInt(String.valueOf(numYearsText.getText()));

        EditText yearEnteredText = (EditText) root.findViewById(R.id.edit_text_year_entered);
        yearEntered = Integer.parseInt(String.valueOf(yearEnteredText.getText()));

        return root;
    }

    /*
     * Getter method for numYearsInt
     */
    public int getNumYears() {
        return numYearsInt;
    }

    /*
     * Getter method for yearEntered
     */
    public int getYearEntered() {
        return yearEntered;
    }

}
