package com.example.degreeplanner.ui.home.checklist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Checklist;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;
import java.util.ArrayList;
import java.util.List;

public class ChecklistAdapter extends ExpandableRecyclerAdapter<ChecklistParentViewHolder, ChecklistChildViewHolder> {

    private Context context;
    private LayoutInflater mInflater;

    public ChecklistAdapter(Context context, List<ParentObject> parentList) {
        super(context, parentList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ChecklistParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.checklist_group_header, viewGroup, false);
        return new ChecklistParentViewHolder(view);    }

    @Override
    public ChecklistChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.checklist_child_item, viewGroup, false);
        return new ChecklistChildViewHolder(view);    }

    @Override
    public void onBindParentViewHolder(ChecklistParentViewHolder checklistParentViewHolder, int i, Object parentObject) {
        ChecklistParent listParent = (ChecklistParent) parentObject;
        switch (i) {
            case 0:
                String numCompleted = listParent.getChildObjectList().size() + " Completed";
                checklistParentViewHolder.mListTitleTextView.setText(numCompleted);
                break;

            case 1:
                String numUncompleted = listParent.getChildObjectList().size() + " Uncompleted";
                checklistParentViewHolder.mListTitleTextView.setText(numUncompleted);
                break;

            default:
                checklistParentViewHolder.mListTitleTextView.setText(Integer.toString(i));
        }
    }

    @Override
    public void onBindChildViewHolder(ChecklistChildViewHolder checklistChildViewHolder, int i, Object childObject) {
        Course listChild = (Course) childObject;
        String courseName = listChild.getDept() + " " +
                listChild.getCode();
        checklistChildViewHolder.mCourseNameText.setText(courseName);
    }
}
