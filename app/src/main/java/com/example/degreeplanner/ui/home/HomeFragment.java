package com.example.degreeplanner.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Checklist;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;
import com.example.degreeplanner.classes.Schedule;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private SharedHomeViewModel mViewModel;
    TabAdapter tabAdapter;
    ViewPager2 viewPager;
    SharedPreferences mPrefs;
    SharedPreferences.Editor prefsEditor;
    Gson gson = new Gson();

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

        // Instantiate shared preferences variables
        mPrefs = getActivity().getPreferences(MODE_PRIVATE);
        getInfo();

        // Open popup window once button is pressed
        FloatingActionButton editPlanBtn = root.findViewById(R.id.edit_quarter_plan_button);
        editPlanBtn.setOnClickListener(v -> openEditQuarterPlan(container, v));
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
     * Get info again if it has changed
     */
    @Override
    public void onResume() {
        super.onResume();
        getInfo();
    }


    /*
     * Function to open the edit quarter plan popup on click
     */
    public void openEditQuarterPlan(ViewGroup container, View view) {
        // inflate the layout of the popup window
        View popupView = getLayoutInflater().inflate(R.layout.popup_window, container, false);

        // create the popup window
        int width = FrameLayout.LayoutParams.MATCH_PARENT;
        int height = FrameLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 600);

        // Set up drop down spinner for quarter
        Spinner quarterSpinner = popupView.findViewById(R.id.spinner_quarter);
        // Create an ArrayAdapter using the string array and a spinner layout
        ArrayAdapter<CharSequence> quarterAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.quarter_spinner_options, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        quarterAdapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        quarterSpinner.setAdapter(quarterAdapter);

        // Set up drop down spinner for courses
        Spinner courseSpinner = popupView.findViewById(R.id.spinner_courses);
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
        Button planButton = view.findViewById(R.id.plan_button);
        planButton.setOnClickListener(v -> {
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
            // Remove class from list of plannable courses and show warning if prereqs not met
            RequirementsViewModel.unplannedCourses.removeCourse(c);
            checkPrereqs(c);
            // Add new info to shared preferences
            mViewModel.resetChecklists();
            setInfo();
            // Close popup
            popupWindow.dismiss();
        });
    }

    public void checkPrereqs(Course mCourse) {
        String category = null;
        if (RequirementsViewModel.majorCourses.containsCourse(mCourse)) {
            category = "major";
        }
        if (RequirementsViewModel.minorCourses.containsCourse(mCourse)) {
            category = "minor";
        }
        if (RequirementsViewModel.collegeCourses.containsCourse(mCourse)) {
            category = "college";
        }
        if (RequirementsViewModel.universityCourses.containsCourse(mCourse)) {
            category = "university";
        }
        boolean prereqsFulfilled =
                mCourse.prereqsFulfilled(mViewModel.getChecklist(category).getCompletedCourses());
        if (prereqsFulfilled) {
            String successText = "Course has been successfully planned";
            Toast toast = Toast.makeText(getContext(), successText, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            String warningText = "Warning: Prerequisites for this class may not have been fulfilled";
            Toast toast = Toast.makeText(getContext(), warningText, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /*
     * Get any locally stored information from shared preferences
     */
    public void getInfo() {
        // Get info from the schedule
        String json = mPrefs.getString("My Schedule", "");
        if (!json.equals("")) {
            Schedule mNewSchedule = gson.fromJson(json, Schedule.class);
            if (mNewSchedule.getQuarters().size() != 0) {
                mViewModel.setMySchedule(mNewSchedule);
            }
        }
        // Get info from major checklist
        json = mPrefs.getString("My Major Checklist", "");
        if (!json.equals("")) {
            Checklist mNewChecklist = gson.fromJson(json, Checklist.class);
            if (mNewChecklist.getRequirementCategories().size() != 0) {
                mViewModel.setMyChecklists("major", mNewChecklist);
            }
        }
        // Get info from minor checklist
        json = mPrefs.getString("My Minor Checklist", "");
        if (!json.equals("")) {
            Checklist mNewChecklist = gson.fromJson(json, Checklist.class);
            if (mNewChecklist.getRequirementCategories().size() != 0) {
                mViewModel.setMyChecklists("minor", mNewChecklist);
            }
        }
        // Get info from college checklist
        json = mPrefs.getString("My College Checklist", "");
        if (!json.equals("")) {
            Checklist mNewChecklist = gson.fromJson(json, Checklist.class);
            if (mNewChecklist.getRequirementCategories().size() != 0) {
                mViewModel.setMyChecklists("college", mNewChecklist);
            }
        }
        // Get info from university checklist
        json = mPrefs.getString("My University Checklist", "");
        if (!json.equals("")) {
            Checklist mNewChecklist = gson.fromJson(json, Checklist.class);
            if (mNewChecklist.getRequirementCategories().size() != 0) {
                mViewModel.setMyChecklists("university", mNewChecklist);
            }
        }
        // Get info from unplanned courses
        json = mPrefs.getString("My Unplanned Courses", "");
        if (!json.equals("")) {
            RequirementCategory mNewRequirementCategory = gson.fromJson(json, RequirementCategory.class);
            if (mNewRequirementCategory.getCourses().size() != 0) {
                RequirementsViewModel.unplannedCourses = mNewRequirementCategory;
            }
        }
    }

    /*
     * Store any new information to shared preferences
     */
    public void setInfo() {
        String jsonSchedule = gson.toJson(mViewModel.getMySchedule());
        String jsonMajor = gson.toJson(mViewModel.getChecklist("major"));
        String jsonMinor = gson.toJson(mViewModel.getChecklist("minor"));
        String jsonCollege = gson.toJson(mViewModel.getChecklist("college"));
        String jsonUniversity = gson.toJson(mViewModel.getChecklist("university"));
        String jsonUnplanned = gson.toJson(RequirementsViewModel.unplannedCourses);
        prefsEditor = mPrefs.edit();
        prefsEditor.putString("My Schedule", jsonSchedule);
        prefsEditor.putString("My Major Checklist", jsonMajor);
        prefsEditor.putString("My Minor Checklist", jsonMinor);
        prefsEditor.putString("My College Checklist", jsonCollege);
        prefsEditor.putString("My University Checklist", jsonUniversity);
        prefsEditor.putString("My Unplanned Courses", jsonUnplanned);
        prefsEditor.commit();
    }
}



