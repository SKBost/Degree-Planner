package com.example.degreeplanner.ui.home.checklist;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Checklist;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.Quarter;
import com.example.degreeplanner.ui.home.PrereqDialog;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;
import com.example.degreeplanner.ui.requirements.VerticalSpaceItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class ChecklistTabFragment extends Fragment {

    private SharedHomeViewModel mViewModel;
    RecyclerView expandableMajor;
    ChecklistAdapter checklistAdapter;
    RecyclerView expandableMinor;
    RecyclerView expandableCollege;
    RecyclerView expandableUniversity;
    SeekBar seekBar;

    public static ChecklistTabFragment newInstance() {
        return new ChecklistTabFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.checklist_tab_fragment, container, false);
        mViewModel =
                new ViewModelProvider(requireActivity()).get(SharedHomeViewModel.class);

        setChecklistLayout(root);
        seekBar = root.findViewById(R.id.checklist_seek_bar);
        this.setChecklistSeekBar(seekBar, getContext());

        // Open suggested courses dialog once button is pressed
        ImageButton dialogBtn = root.findViewById(R.id.suggested_courses_button);
        dialogBtn.setOnClickListener(v -> openSuggestedCourses());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        setChecklistLayout(getView());
        setChecklistSeekBar(seekBar,getContext());
    }

    /*
     * Function to open the suggested coursed dialog on click
     */
    public void openSuggestedCourses() {
        DialogFragment dialogFragment = new PrereqDialog();
        dialogFragment.show(getParentFragmentManager(), "Suggested Courses");
    }

    /*
     * Sets up the checklist expandable list layout with completed and uncompleted courses
     */
    public void setChecklistLayout(View root) {
        // Get list of classes before a certain quarter
        ArrayList<Course> coursesToIndex =
                mViewModel.getMySchedule().getCoursesCompletedBeforeQuarter(mViewModel.getSeekBarProgress() + 1);

        // Set up major category expandable list in checklist
        expandableMajor = root.findViewById(R.id.expandableListView);
        LinearLayoutManager layoutMajor = new LinearLayoutManager(getContext());
        expandableMajor.setLayoutManager(layoutMajor);
        //expandableListView.addItemDecoration(new VerticalSpaceItemDecorator(5));
        checklistAdapter = new ChecklistAdapter
                (getContext(), generateCourses(coursesToIndex, mViewModel.getChecklist("major")));
        checklistAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        checklistAdapter.setParentClickableViewAnimationDefaultDuration();
        checklistAdapter.setParentAndIconExpandOnClick(true);
        expandableMajor.setAdapter(checklistAdapter);


        // Set up minor category expandable list in checklist
        expandableMinor = root.findViewById(R.id.expandable_list_minor);
        LinearLayoutManager layoutMinor = new LinearLayoutManager(getContext());
        expandableMinor.setLayoutManager(layoutMinor);
        checklistAdapter = new ChecklistAdapter
                (getContext(), generateCourses(coursesToIndex, mViewModel.getChecklist("minor")));
        checklistAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        checklistAdapter.setParentClickableViewAnimationDefaultDuration();
        checklistAdapter.setParentAndIconExpandOnClick(true);
        expandableMinor.setAdapter(checklistAdapter);

        // Set up college category expandable list in checklist
        expandableCollege = root.findViewById(R.id.expandable_list_college);
        LinearLayoutManager layoutCollege = new LinearLayoutManager(getContext());
        expandableCollege.setLayoutManager(layoutCollege);
        checklistAdapter = new ChecklistAdapter
                (getContext(), generateCourses(coursesToIndex, mViewModel.getChecklist("college")));
        checklistAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        checklistAdapter.setParentClickableViewAnimationDefaultDuration();
        checklistAdapter.setParentAndIconExpandOnClick(true);
        expandableCollege.setAdapter(checklistAdapter);

        // Set up university category expandable list in checklist
        expandableUniversity =  root.findViewById(R.id.expandable_list_university);
        LinearLayoutManager layoutUniversity = new LinearLayoutManager(getContext());
        expandableUniversity.setLayoutManager(layoutUniversity);
        checklistAdapter = new ChecklistAdapter
                (getContext(), generateCourses(coursesToIndex, mViewModel.getChecklist("university")));
        checklistAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        checklistAdapter.setParentClickableViewAnimationDefaultDuration();
        checklistAdapter.setParentAndIconExpandOnClick(true);
        expandableUniversity.setAdapter(checklistAdapter);


    }

    /*
     * Method to populate expanding list headers and courses
     */
    private ArrayList<ParentObject> generateCourses(ArrayList<Course> mCoursesToIndex, Checklist mChecklist) {
        List<ChecklistParent> completionTitles = new ArrayList<>();
        completionTitles.add(new ChecklistParent());
        completionTitles.add(new ChecklistParent());
        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        // Set completed courses
        ArrayList<Object> childListComp = new ArrayList<>();
        childListComp = separateCompCourses(mCoursesToIndex, mChecklist, true);
        completionTitles.get(0).setChildObjectList(childListComp);
        parentObjects.add(completionTitles.get(0));
        // Set uncompleted courses
        ArrayList<Object> childListUncomp = new ArrayList<>();
        childListUncomp = separateCompCourses(mCoursesToIndex, mChecklist, false);
        completionTitles.get(1).setChildObjectList(childListUncomp);
        parentObjects.add(completionTitles.get(1));
        return parentObjects;
    }

    /*
     * Separates completed and completed courses given a list of all courses
     */
    public ArrayList<Object> separateCompCourses(ArrayList<Course> allCoursesToDisplay, Checklist checklist, boolean returnArray) {
        ArrayList<Object> myCompCourses = new ArrayList<>();
        ArrayList<Object> myUncompCourses = new ArrayList<>();
        for (Course c: allCoursesToDisplay) {
            if (checklist.getRequirementCategories().get(0).containsCourse(c)) {
                if (separateCompCourseHelper(checklist.getCompletedCourses(), c)) {
                    myCompCourses.add(c);
                } else {
                    myUncompCourses.add(c);
                }
            }
        }
        if (returnArray) {
            return myCompCourses;
        }
        return myUncompCourses;
    }

    public boolean separateCompCourseHelper(ArrayList<Course> mArrayCourses, Course mCourse) {
        for (Course c: mArrayCourses) {
            if (c.getDept().equals(mCourse.getDept()) && c.getCode().equals(mCourse.getCode())) {
                return true;
            }
        }
        return false;
    }

    /*
     * Method to set the checklist slider
     */
    public void setChecklistSeekBar(SeekBar mySeekBar, Context myContext) {
        mySeekBar.setProgress(mViewModel.getSeekBarProgress());
        mySeekBar.setMax(mViewModel.getMySchedule().getQuarters().size() - 1);
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Reset checklist layout once seekBar is changed
                setChecklistLayout(getView());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Code to perform some action when touch is started.
            }

            /*
             * When user releases slider, the correct completed and uncompleted courses are
             * displayed within each category
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mViewModel.setSeekBarProgress(seekBar.getProgress());
                Quarter currQuarter = mViewModel.getMySchedule().getQuarters().get(mySeekBar.getProgress());
                Toast.makeText(myContext, currQuarter.getName(), Toast.LENGTH_SHORT).show();
                setChecklistLayout(getView());
            }
        });
    }

}