package com.example.degreeplanner.ui.home.checklist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;

public class ChecklistTabFragment extends Fragment {

    private SharedHomeViewModel mViewModel;
    ExpandableListView expandableListView;
    ChecklistAdapter checklistAdapter;

    public static ChecklistTabFragment newInstance() {
        return new ChecklistTabFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.checklist_tab_fragment, container, false);

        // todo: array of all completed and uncompleted courses needs to be made, using all courses just as filler
        // Create adapter for checklist
        checklistAdapter = new ChecklistAdapter(getContext(), RequirementsViewModel.allReqCats, RequirementsViewModel.allCourses.getCourses());

        // Set up major category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandableListView);
        expandableListView.setAdapter(checklistAdapter);
        // Set up minor category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_minor);
        expandableListView.setAdapter(checklistAdapter);
        // Set up college category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_college);
        expandableListView.setAdapter(checklistAdapter);
        // Set up university category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_university);
        expandableListView.setAdapter(checklistAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel =
                new ViewModelProvider(requireActivity()).get(SharedHomeViewModel.class);
    }

}