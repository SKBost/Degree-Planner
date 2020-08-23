package com.example.degreeplanner.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.ui.home.checklist.ChecklistAdapter;
import com.example.degreeplanner.ui.requirements.AddRequirement;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {

    private SharedHomeViewModel mViewModel;
    TabAdapter tabAdapter;
    ViewPager2 viewPager;
    String selectedItem;

    private static final String[] tabNames = new String[] {"Checklist", "Quarter Plan"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(requireActivity()).get(SharedHomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Set up checklist and quarter plan tab
        tabAdapter = new TabAdapter(getActivity());
        viewPager = root.findViewById(R.id.home_view_pager);
        viewPager.setAdapter(tabAdapter);


        // Open popup window once button is pressed
        FloatingActionButton editPlanBtn = root.findViewById(R.id.edit_quarter_plan_button);
        editPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openEditQuarterPlan(container, v);
            }
        });


        return root;
    }

    /*
     * Allow tab layout to properly display
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabNames[position])
        ).attach();
    }

    /*
     * Function to open the edit quarter plan popup on click
     */
    public void openEditQuarterPlan(ViewGroup container, View view) {
        // inflate the layout of the popup window
        View popupView = getLayoutInflater().inflate(R.layout.popup_window, container, false);

        // create the popup window
        int width = RelativeLayout.LayoutParams.MATCH_PARENT;
        int height = RelativeLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, 550, true);

        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 600);

        // Set up drop down spinner for quarter
        Spinner quarterSpinner = (Spinner) popupView.findViewById(R.id.spinner_quarter);
        // Create an ArrayAdapter using the string array and a spinner layout
        ArrayAdapter<CharSequence> quarterAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.quarter_spinner_options, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        quarterAdapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        quarterSpinner.setAdapter(quarterAdapter);

        // Set up drop down spinner for courses
        Spinner courseSpinner = (Spinner) popupView.findViewById(R.id.spinner_courses);
        // Create an array with all course names
        RequirementsViewModel req_model = new RequirementsViewModel();
        ArrayList<String> myCourses = new ArrayList<>();
        for (Course course: req_model.getAllCourses()) {
            String name = course.getDept() + " " + course.getCode();
            myCourses.add(name);
        }
        ArrayAdapter<String> courseAdapter =
                new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, myCourses);
        courseAdapter.setDropDownViewResource(R.layout.spinner_item);
        courseSpinner.setAdapter(courseAdapter);

        EditText yearPlannedEditText = popupView.findViewById(R.id.planned_year_text);
        CheckBox completion = popupView.findViewById(R.id.completion_checkBox);

        // Set button for popup
        this.whenPlanned(popupView, popupWindow, courseSpinner, quarterSpinner, yearPlannedEditText, completion);
    }

    /*
     * Sets on click listener for plan button and adds planned course to schedule once pressed
     */
    public void whenPlanned(View view, PopupWindow popupWindow, Spinner myCourse,
                            Spinner myQuarter, EditText myYear, CheckBox myCompletion) {
        Button planButton = (Button) view.findViewById(R.id.plan_button);
        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String myCourseString = myCourse.getSelectedItem().toString();
                String myQuarterString = myQuarter.getSelectedItem().toString();
                // Save course to correct quarter
                Course c = mViewModel.addCourseToQuarter(myCourseString, myQuarterString,
                        Integer.parseInt(myYear.getText().toString()));
                // Add as a completed course if checked
                if (myCompletion.isChecked()) {
                    // Find which category course should be added to
                    if (RequirementsViewModel.majorCourses.containsCourse(c)) {
                        mViewModel.getChecklist("major").addCompletedCourse(c);
                    }
                    if (RequirementsViewModel.minorCourses.containsCourse(c)) {
                        mViewModel.getChecklist("minor").addCompletedCourse(c);
                    }
                    if (RequirementsViewModel.collegeCourses.containsCourse(c)) {
                        mViewModel.getChecklist("college").addCompletedCourse(c);
                    }
                    if (RequirementsViewModel.universityCourses.containsCourse(c)) {
                        mViewModel.getChecklist("university").addCompletedCourse(c);
                    }
                }
                // Remove class from list of plannable courses
                RequirementsViewModel.unplannedCourses.removeCourse(c);
                // Close popup
                popupWindow.dismiss();
            }
        });
    }
}



