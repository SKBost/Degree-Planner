package com.example.degreeplanner.ui.home.checklist;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.Quarter;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;

import java.util.ArrayList;

public class ChecklistTabFragment extends Fragment {

    private SharedHomeViewModel mViewModel;
    ExpandableListView expandableListView;
    ChecklistAdapter checklistAdapter;
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

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        setChecklistLayout(getView());
        setChecklistSeekBar(seekBar,getContext());
    }

    /*
     * Sets up the checklist expandable list layout with completed and uncompleted courses
     */
    public void setChecklistLayout(View root) {
        // Get list of classes before a certain quarter
        ArrayList<Course> coursesToIndex =
                mViewModel.getMySchedule().getCoursesCompletedBeforeQuarter(mViewModel.getSeekBarProgress() + 1);
        // Set up major category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandableListView);
        checklistAdapter = new ChecklistAdapter
                (getContext(), mViewModel.getChecklist("major"), coursesToIndex);
        expandableListView.setAdapter(checklistAdapter);

        // Set up minor category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_minor);
        checklistAdapter = new ChecklistAdapter
                (getContext(), mViewModel.getChecklist("minor"), coursesToIndex);
        expandableListView.setAdapter(checklistAdapter);

        // Set up college category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_college);
        checklistAdapter = new ChecklistAdapter
                (getContext(), mViewModel.getChecklist("college"), coursesToIndex);
        expandableListView.setAdapter(checklistAdapter);

        // Set up university category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_university);
        checklistAdapter = new ChecklistAdapter
                (getContext(), mViewModel.getChecklist("university"), coursesToIndex);
        expandableListView.setAdapter(checklistAdapter);
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