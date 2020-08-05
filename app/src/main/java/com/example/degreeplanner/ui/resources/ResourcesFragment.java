package com.example.degreeplanner.ui.resources;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.degreeplanner.R;

public class ResourcesFragment extends Fragment {

    private ResourcesViewModel resourcesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        resourcesViewModel =
                ViewModelProviders.of(this).get(ResourcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resources, container, false);

        /* Original code when file was created
        final TextView textView = root.findViewById(R.id.text_resources);
        resourcesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */

        // Move to college advising screen once button is pressed
        Button collegeAdvBtn = root.findViewById(R.id.college_advising_button);
        collegeAdvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openCollegeAdv();
            }
        });

        // Move to department advising screen once button is pressed
        Button deptAdvBtn = root.findViewById(R.id.department_advising_button);
        deptAdvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openDeptAdv();
            }
        });

        // Move to UCSD catalog screen once button is pressed
        Button catalogBtn = root.findViewById(R.id.ucsd_catalog_button);
        catalogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openCatalog();
            }
        });

        Button webregBtn = root.findViewById(R.id.webreg_button);
        webregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openWebreg();
            }
        });

        return root;
    }

    /*
     * Function to open the college advising page on click
     */
    public void openCollegeAdv() {
        Intent intent = new Intent(getActivity(), CollegeAdvising.class);
        startActivity(intent);
    }

    /*
     * Function to open the department advising page on click
     */
    public void openDeptAdv() {
        Intent intent = new Intent(getActivity(), DepartmentAdvising.class);
        startActivity(intent);
    }

    /*
     * Function to open the UCSD catalog page on click
     */
    public void openCatalog() {
        Intent intent = new Intent(getActivity(), UCSDCatalog.class);
        startActivity(intent);
    }

    /*
     * Function to open the webreg page on click
     */
    public void openWebreg() {
        Intent intent = new Intent(getActivity(), Webreg.class);
        startActivity(intent);
    }
}