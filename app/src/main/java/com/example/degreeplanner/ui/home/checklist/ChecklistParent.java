package com.example.degreeplanner.ui.home.checklist;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.degreeplanner.classes.Course;

import java.util.List;

public class ChecklistParent implements ParentObject {

    private List<Object> mChildrenList;

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }
}
