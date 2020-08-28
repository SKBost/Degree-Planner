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




//public class ChecklistAdapter extends BaseExpandableListAdapter {
//    private Context context;
//    // Header information (completed/uncompleted)
//    private List<String> expandableListTitle;
//    // List body information with all completed courses
//    private Checklist expandableListDetail;
//    private ArrayList<Course> myCompCourses = new ArrayList<>();
//    private ArrayList<Course> myUncompCourses = new ArrayList<>();
//
//    /*
//     * Adapter constructor
//     */
//    public ChecklistAdapter(Context context, Checklist checklist, ArrayList<Course> myCoursesToQuarter) {
//        super();
//        this.context = context;
//        expandableListTitle = new ArrayList<>();
//        this.expandableListTitle.add("Completed");
//        this.expandableListTitle.add("Uncompleted");
//        expandableListDetail = checklist;
//        separateCompCourses(myCoursesToQuarter);
//    }
//
//    /*
//     * Separates completed and completed courses given a list of all courses
//     */
//    public void separateCompCourses(ArrayList<Course> allCoursesToDisplay) {
//        for (Course c: allCoursesToDisplay) {
//            if (expandableListDetail.getRequirementCategories().get(0).containsCourse(c)) {
//                if (expandableListDetail.getCompletedCourses().contains(c)) {
//                    myCompCourses.add(c);
//                } else {
//                    myUncompCourses.add(c);
//                }
//            }
//        }
//    }
//
//    /*
//     * Gets the data associated with the given child within the given group
//     */
//    @Override
//    public Course getChild(int listPosition, int expandedListPosition) {
//        switch (listPosition) {
//            case 0:
//                return myCompCourses.get(expandedListPosition);
//            case 1:
//                return myUncompCourses.get(expandedListPosition);
//        }
//        return myCompCourses.get(expandedListPosition);
//    }
//
//
//    /*
//     * Gets the ID for the given child within the given group
//     */
//    @Override
//    public long getChildId(int listPosition, int expandedListPosition) {
//        return expandedListPosition;
//    }
//
//    /*
//     * Gets a View that displays the data for the given child within the given group
//     */
//    @Override
//    public View getChildView(int listPosition, final int expandedListPosition,
//                             boolean isLastChild, View convertView, ViewGroup parent) {
//        // Turn each Course object into a string with name
//        final String myDept = getChild(listPosition, expandedListPosition).getDept();
//        final String myCourseCode = getChild(listPosition, expandedListPosition).getCode();
//        final String expandedListText = myDept + myCourseCode;
//        // Inflate child item view
//        if (convertView == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) this.context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.checklist_child_item, parent, false);
//        }
//        // Set text of child item to course name
//        TextView expandedListTextView = (TextView) convertView
//                .findViewById(R.id.expandedListItem);
//        expandedListTextView.setText(expandedListText);
//        return convertView;
//    }
//
//    @Override
//    public int getChildrenCount(int listPosition) {
//        switch (listPosition) {
//            case 0:
//                return myCompCourses.size();
//            case 1:
//                return myUncompCourses.size();
//        }
//        return expandableListDetail.getCompletedCourses().size();
//    }
//
//    @Override
//    public String getGroup(int listPosition) {
//        int childSize = 0;
//        switch (listPosition) {
//            case 0:
//                childSize = myCompCourses.size();
//                return new String(childSize + " " + this.expandableListTitle.get(listPosition));
//            case 1:
//                childSize =  myUncompCourses.size();
//                return new String(childSize + " " + this.expandableListTitle.get(listPosition));
//            default:
//                return new String(childSize + " " + this.expandableListTitle.get(listPosition));
//        }
//    }
//
//    @Override
//    public int getGroupCount() {
//        return this.expandableListTitle.size();
//    }
//
//    @Override
//    public long getGroupId(int listPosition) {
//        return listPosition;
//    }
//
//    /*
//     * Gets a View that displays the given group
//     */
//    @Override
//    public View getGroupView(int listPosition, boolean isExpanded,
//                             View convertView, ViewGroup parent) {
//        // Get either the completed or uncompleted category
//        String listTitle = (String) getGroup(listPosition);
//        // Inflate view of group text
//        if (convertView == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) this.context.
//                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.checklist_group_header, parent, false);
//        }
//        // Set text to name of header
//        TextView listTitleTextView = (TextView) convertView
//                .findViewById(R.id.listTitle);
//        listTitleTextView.setText(listTitle);
//        return convertView;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
//        return true;
//    }
//}
