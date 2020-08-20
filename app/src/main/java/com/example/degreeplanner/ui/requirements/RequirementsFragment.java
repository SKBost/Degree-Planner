package com.example.degreeplanner.ui.requirements;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RequirementsFragment extends Fragment {

    public RequirementsViewModel requirementsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_requirements, container, false);

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
     * Event callback to when fragment activity is resumed
     */
    @Override
    public void onResume() {
        super.onResume();
        // Create recycler view layouts for all categories
        RecyclerView majorView = (RecyclerView) getView().findViewById(R.id.req_recycler_view);
        RecyclerView minorView = (RecyclerView) getView().findViewById(R.id.req_recyclerView_minor);
        RecyclerView collegeView = (RecyclerView) getView().findViewById(R.id.req_recycler_view_college);
        RecyclerView universityView = (RecyclerView) getView().findViewById(R.id.req_recycler_view_university);
        this.displayCourses(majorView, "Major");
        this.displayCourses(minorView, "Minor");
        this.displayCourses(collegeView, "College");
        this.displayCourses(universityView, "University");
    }

    /*
     * Displays all user input courses through a recycler view layout
     */
    public void displayCourses(RecyclerView recyclerView, String category) {
        // Create RequirementsViewModel object
        requirementsViewModel =
                new ViewModelProvider(this).get(RequirementsViewModel.class);
        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        // Add space between rows
        recyclerView.addItemDecoration(new VerticalSpaceItemDecorator(20));
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        // Adapter initialization
        RequirementAdapter adapter = new RequirementAdapter(getActivity(), this.displayCoursesHelper(category));
        recyclerView.setAdapter(adapter);
    }

    /*
     * displayCourses helper method to get correct courses based on category
     */
    public ArrayList<Course> displayCoursesHelper(String category) {
        switch (category) {
            case "Major" :
                return requirementsViewModel.getMajorCourses();
            case "Minor" :
                return requirementsViewModel.getMinorCourses();
            case "College" :
                return requirementsViewModel.getCollegeCourses();
            case "University" :
                return requirementsViewModel.getUniversityCourses();
        }
        return requirementsViewModel.getAllCourses();
    }


    /*
     * Function to open the add requirements page on click
     */
    public void openAddReq() {
        Intent intent = new Intent(getActivity(), AddRequirement.class);
        startActivity(intent);
    }

}