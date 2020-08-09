package com.example.degreeplanner.classes;

import java.util.ArrayList;

public class Checklist {

    private ArrayList<RequirementCategory> reqCats; // categories of required courses
    private ArrayList<Course> compCourses; // completed courses

    // constructors

    public Checklist() {
        reqCats = new ArrayList<RequirementCategory>();
        compCourses = new ArrayList<Course>();
    }

    public Checklist(ArrayList<RequirementCategory> myReqCats, ArrayList<Course> myCompCourses) {
        reqCats = myReqCats;
        compCourses = myCompCourses;
    }

    // getters

    public ArrayList<RequirementCategory> getRequirementCategories() {
        return reqCats;
    }

    public ArrayList<Course> getCompletedCourses() {
        return compCourses;
    }

    // setters

    public void setCompletedCourses(ArrayList<Course> newCompCourses) {
        compCourses = newCompCourses;
    }

    public void setRequirementCategories(ArrayList<RequirementCategory> newReqCats) {
        reqCats = newReqCats;
    }

    // adders

    public void addRequirementCategory(RequirementCategory newReqCat) {
        reqCats.add(newReqCat);
    }

    public void addCompletedCourse(Course newCompCourse) {
        compCourses.add(newCompCourse);
    }

    // removers

    public void removeRequirementCategory(RequirementCategory deleted) {
        reqCats.remove(deleted);
    }

    public void removeCompletedCourse(Course deleted) {
        compCourses.remove(deleted);
    }

    // other methods

    public ArrayList<RequirementCategory> getUncompletedRequirementCategories() {

        ArrayList<RequirementCategory> uncompleted = new ArrayList<RequirementCategory>();

        for(int i = 0; i < reqCats.size(); i++) {
            ArrayList<Course> uncompCourses = reqCats.get(i).getUncompletedCourses(compCourses);
            if(uncompCourses.size() > 0) {
                // TODO: if you want to do anything with uncompCourses, likely to display what's
                //  missing for the category, this would be the place to do it. Also add else block.
                uncompleted.add(reqCats.get(i));
            }
        }

        return uncompleted;
    }

    // TODO: should I make a completed version of the above?
}
