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
import com.example.degreeplanner.classes.RequirementCategory;
import com.example.degreeplanner.ui.home.SharedHomeViewModel;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
        mViewModel =
                new ViewModelProvider(requireActivity()).get(SharedHomeViewModel.class);

        // Set up major category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandableListView);
        checklistAdapter = new ChecklistAdapter
                (getContext(), mViewModel.getChecklist("major"));
        expandableListView.setAdapter(checklistAdapter);

        // Set up minor category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_minor);
        checklistAdapter = new ChecklistAdapter
                (getContext(), mViewModel.getChecklist("minor"));
        expandableListView.setAdapter(checklistAdapter);

        // Set up college category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_college);
        checklistAdapter = new ChecklistAdapter
                (getContext(), mViewModel.getChecklist("college"));
        expandableListView.setAdapter(checklistAdapter);

        // Set up university category expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandable_list_university);
        checklistAdapter = new ChecklistAdapter
                (getContext(), mViewModel.getChecklist("university"));
        expandableListView.setAdapter(checklistAdapter);

        return root;
    }

}