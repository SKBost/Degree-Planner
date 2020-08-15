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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        this.displayCourses();

    }

    /*
     * Displays all user input courses through a recycler view layout
     * todo: have a separate method for major/minor/etc.
     */
    public void displayCourses() {
        // Create RequirementsViewModel object
        requirementsViewModel =
                new ViewModelProvider(this).get(RequirementsViewModel.class);
        // Create recycler view layout
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.req_recycler_view);
        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        // Add space between rows
        recyclerView.addItemDecoration(new VerticalSpaceItemDecorator(20));
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        // Adapter initialization
        RequirementAdapter adapter = new RequirementAdapter(getActivity(), requirementsViewModel.getAllCourses());
        recyclerView.setAdapter(adapter);
    }

    /*
     * Function to open the add requirements page on click
     */
    public void openAddReq() {
        Intent intent = new Intent(getActivity(), AddRequirement.class);
        startActivity(intent);
    }

}