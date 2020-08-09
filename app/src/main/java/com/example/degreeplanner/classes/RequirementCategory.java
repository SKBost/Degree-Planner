package com.example.degreeplanner.classes;

import java.util.ArrayList;

public class RequirementCategory { // TODO: potentially add support for boolean combinations: and/or
    private String name;
    private ArrayList<Course> courses;

    // constructors

    public RequirementCategory() {} // null fields

    public RequirementCategory(String myName) {
        name = myName;
        courses = new ArrayList<Course>();
    }

    public RequirementCategory(String myName, ArrayList<Course> myCourses) {
        name = myName;
        courses = myCourses;
    }

    // setters

    public void setName(String newName) {
        name = newName;
    }

    public void setCourses(ArrayList<Course> newCourses) {
        courses = newCourses;
    }


    // getters

    public String getName() {
        return name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }


    // other methods

    public void addCourse(Course newCourse) {
        courses.add(newCourse);
    }

    public void removeCourse(Course deleted) {
        courses.remove(deleted);
    }

    // get uncompleted courses in the requirement category, given a general list of completed courses
    public ArrayList<Course> getUncompletedCourses(ArrayList<Course> completed) {
        ArrayList<Course> uncompleted = new ArrayList<Course>();

        for(int i = 0; i < courses.size(); i++) {
            if(!completed.contains(courses.get(i))) {
                // add each course in this category to uncompleted course
                // list if completed course list does not contain it
                uncompleted.add(courses.get(i));
            }
        }

        return uncompleted;
    }


}
