package com.example.degreeplanner.ui.requirements;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RequirementsFragment extends Fragment {
    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson = new Gson();


    public RequirementsViewModel requirementsViewModel;
    public SharedHomeViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_requirements, container, false);
        // Create RequirementsViewModel object
        requirementsViewModel =
                new ViewModelProvider(this).get(RequirementsViewModel.class);

        // Instantiate shared preferences variables
        mPrefs = getActivity().getPreferences(MODE_PRIVATE);
        getInfo();

        // Move to add requirements screen once button is pressed
        FloatingActionButton addReqBtn = root.findViewById(R.id.add_requirement_button);
        addReqBtn.setOnClickListener(v -> openAddReq());

        return root;
    }


    /*
     * Event callback to when fragment activity is resumed
     */
    @Override
    public void onResume() {
        super.onResume();
        // Create recycler view layouts for all categories
        RecyclerView majorView = getView().findViewById(R.id.req_recycler_view);
        RecyclerView minorView = getView().findViewById(R.id.req_recyclerView_minor);
        RecyclerView collegeView = getView().findViewById(R.id.req_recycler_view_college);
        RecyclerView universityView = getView().findViewById(R.id.req_recycler_view_university);
        this.displayCourses(majorView, "Major");
        this.displayCourses(minorView, "Minor");
        this.displayCourses(collegeView, "College");
        this.displayCourses(universityView, "University");
        storeInfo();
    }

    /*
     * Store any new info before moving to another fragment
     */
    @Override
    public void onStop() {
        super.onStop();
        storeInfo();
    }

    /*
     * Displays all user input courses through a recycler view layout
     */
    public void displayCourses(RecyclerView recyclerView, String category) {
        sharedViewModel =
                new ViewModelProvider(requireActivity()).get(SharedHomeViewModel.class);
        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        // Add space between rows
        recyclerView.addItemDecoration(new VerticalSpaceItemDecorator(20));
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        // Adapter initialization
        RequirementAdapter adapter =
                new RequirementAdapter(getActivity(), this.displayCoursesHelper(category), category, getChildFragmentManager(), sharedViewModel);
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

    /*
     * Stores Requirement Category information locally through shared preferences
     */
    public void storeInfo() {
        String jsonMajor = gson.toJson(requirementsViewModel.getMajorCoursesObject());
        String jsonMinor = gson.toJson(requirementsViewModel.getMinorCoursesObject());
        String jsonCollege = gson.toJson(requirementsViewModel.getCollegeCourseObject());
        String jsonUniversity = gson.toJson(requirementsViewModel.getUniversityCoursesObject());
        String jsonUnplanned = gson.toJson(requirementsViewModel.getUnplannedCoursesObject());
        prefsEditor = mPrefs.edit();
        prefsEditor.putString("My Major Courses", jsonMajor);
        prefsEditor.putString("My Minor Courses", jsonMinor);
        prefsEditor.putString("My College Courses", jsonCollege);
        prefsEditor.putString("My University Courses", jsonUniversity);
        prefsEditor.putString("My Unplanned Courses", jsonUnplanned);
        prefsEditor.commit();
    }

    /*
     * Get Requirement Category information stored locally
     */
    public void getInfo() {
        // Get info from the major category
        String json = mPrefs.getString("My Major Courses", "");
        if (!json.equals("")) {
            RequirementCategory mNewRequirementCategory = gson.fromJson(json, RequirementCategory.class);
            if (mNewRequirementCategory.getCourses().size() != 0) {
                requirementsViewModel.setMajorCoursesObject(mNewRequirementCategory);
            }
        }
        // Get info from the minor category
        json = mPrefs.getString("My Minor Courses", "");
        if (!json.equals("")) {
            RequirementCategory mNewRequirementCategory = gson.fromJson(json, RequirementCategory.class);
            if (mNewRequirementCategory.getCourses().size() != 0) {
                requirementsViewModel.setMinorCoursesObject(mNewRequirementCategory);
            }
        }
        // Get info from the college category
        json = mPrefs.getString("My College Courses", "");
        if (!json.equals("")) {
            RequirementCategory mNewRequirementCategory = gson.fromJson(json, RequirementCategory.class);
            if (mNewRequirementCategory.getCourses().size() != 0) {
                requirementsViewModel.setCollegeCoursesObject(mNewRequirementCategory);
            }
        }
        // Get info from the University category
        json = mPrefs.getString("My University Courses", "");
        if (!json.equals("")) {
            RequirementCategory mNewRequirementCategory = gson.fromJson(json, RequirementCategory.class);
            if (mNewRequirementCategory.getCourses().size() != 0) {
                requirementsViewModel.setUniversityCoursesObject(mNewRequirementCategory);
            }
        }
        // Get info from the Unplanned category
        json = mPrefs.getString("My Unplanned Courses", "");
        if (!json.equals("")) {
            RequirementCategory mNewRequirementCategory = gson.fromJson(json, RequirementCategory.class);
            if (mNewRequirementCategory.getCourses().size() != 0) {
                requirementsViewModel.setUnplannedCoursesObject(mNewRequirementCategory);
            }
        }
    }

}