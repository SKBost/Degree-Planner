package com.example.degreeplanner.ui.requirements;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.degreeplanner.R;
import com.example.degreeplanner.activities.AddRequirement;
import com.example.degreeplanner.activities.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RequirementsFragment extends Fragment {

    private RequirementsViewModel requirementsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        requirementsViewModel =
                ViewModelProviders.of(this).get(RequirementsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_requirements, container, false);
        final TextView textView = root.findViewById(R.id.text_requirements);
        requirementsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Move to add requirements screen once button is pressed
        FloatingActionButton addReqBtn = root.findViewById(R.id.add_requirement_button);
        addReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openAddReq();
            }
        });

        return root;
    }

    /*
            * Function to open the add requirements page on click
     */
    public void openAddReq() {
        Intent intent = new Intent(getActivity(), AddRequirement.class);
        startActivity(intent);
    }
}