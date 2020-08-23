package com.example.degreeplanner.ui.home.checklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Checklist;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;

import java.util.ArrayList;
import java.util.List;

public class ChecklistAdapter extends BaseExpandableListAdapter {
    private Context context;
    // Header information (completed/uncompleted)
    private List<String> expandableListTitle;
    // List body information (courses)
    private Checklist expandableListDetail;

    /*
     * Adapter constructor
     */
    public ChecklistAdapter(Context context, Checklist checklist) {
        super();
        this.context = context;
        expandableListTitle = new ArrayList<>();
        this.expandableListTitle.add("Completed");
        this.expandableListTitle.add("Uncompleted");
        expandableListDetail = checklist;
    }

    /*
     * Gets the data associated with the given child within the given group
     */
    @Override
    public Course getChild(int listPosition, int expandedListPosition) {
        switch (listPosition) {
            case 0:
                return expandableListDetail.getCompletedCourses().get(expandedListPosition);
            case 1:
                return expandableListDetail.getRequirementCategories().get(0).getUncompletedCourses
                        (expandableListDetail.getCompletedCourses()).get(expandedListPosition);
        }
        return expandableListDetail.getCompletedCourses().get(expandedListPosition);
    }

    /*
     * Gets the ID for the given child within the given group
     */
    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    /*
     * Gets a View that displays the data for the given child within the given group
     */
    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // Turn each Course object into a string with name
        final String myDept = getChild(listPosition, expandedListPosition).getDept();
        final String myCourseCode = getChild(listPosition, expandedListPosition).getCode();
        final String expandedListText = myDept + myCourseCode;
        // Inflate child item view
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.checklist_child_item, parent, false);
        }
        // Set text of child item to course name
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        switch (listPosition) {
            case 0:
                return expandableListDetail.getCompletedCourses().size();
            case 1:
                return expandableListDetail.getRequirementCategories().get(0).getUncompletedCourses
                        (expandableListDetail.getCompletedCourses()).size();
        }
        return expandableListDetail.getCompletedCourses().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    /*
     * Gets a View that displays the given group
     */
    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // Get either the completed or uncompleted category
        String listTitle = (String) getGroup(listPosition);
        // Inflate view of group text
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.checklist_group_header, parent, false);
        }
        // Set text to name of header
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
