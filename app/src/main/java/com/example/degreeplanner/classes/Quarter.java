package com.example.degreeplanner.classes;

import java.util.ArrayList;

public class Quarter { // NOTE: important rule: assume input is always correct (contract) so you fix invalid input instead of checking it
    private int index; // ex: first term (transfer units) = 0
    private String name; // ex: Spring 2020
    private ArrayList<Course> courses;

    // constructors (use whichever suits your purposes; you can set any part of this class later)

    // minimal
    public Quarter() { // note: null fields exist for this option
        courses = new ArrayList<Course>();
    }

    public Quarter(int myIndex, String myName) {
        index = myIndex;
        name = myName;
        courses = new ArrayList<Course>();
    }

    // maximal
    public Quarter(int myIndex, String myName, ArrayList<Course> myCourses) {
        index = myIndex;
        name = myName;
        courses = myCourses;
    }

    // getters
    public int getIndex() { return index; }
    public String getName() { return name; }
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // setters
    public void setIndex(int newIndex) { index = newIndex; }
    public void setName(String newName) { name = newName; }
    public void setCourses(ArrayList<Course> newCourses) {
        courses = newCourses;
    }


    // other modifiers

    public void addCourse(Course newCourse) {
        courses.add(newCourse);
    }

    public void removeCourse(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).equals(course)) {
                courses.remove(i);
            }
        }
    }

    // get combined units of all classes in the quarter
    public double getTotalUnits() {
        int total = 0;
        for(int i = 0; i < courses.size(); i++) {
            // don't count if units are null? idk how defensive I need to be (I choose not to check, units should never be null)
            // TODO: test with System.out
            Course course = courses.get(i);
            //if(!course.getUnits().equals(null))
            total += course.getUnits();
        }
        return total;
    }

    public boolean hasValidUnitNum() { // we only check for overdone units
        return getTotalUnits() <= 22;
    }

    //possible TODO: make function for reordering list if display can be reordered
    // TODO: check underdone units nums? varies for summer session ond transfer units
}