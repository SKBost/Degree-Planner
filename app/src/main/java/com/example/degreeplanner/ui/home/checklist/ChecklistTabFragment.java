package com.example.degreeplanner.ui.home.checklist;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;

public class ChecklistTabFragment extends Fragment {

    private ChecklistTabViewModel mViewModel;
    ExpandableListView expandableListView;
    ChecklistAdapter checklistAdapter;

    public static ChecklistTabFragment newInstance() {
        return new ChecklistTabFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.checklist_tab_fragment, container, false);

        // Set up expandable list in checklist
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandableListView);
        // todo: using all courses, replace with specific categories (major,minor,etc.)
        // todo: array of all completed courses needs to be made, using all courses just as filler
        checklistAdapter = new ChecklistAdapter(getContext(), RequirementsViewModel.allReqCats, RequirementsViewModel.allCourses.getCourses());
        expandableListView.setAdapter(checklistAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChecklistTabViewModel.class);
        // TODO: Use the ViewModel
    }

}