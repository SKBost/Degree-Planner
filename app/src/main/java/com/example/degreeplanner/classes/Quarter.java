package com.example.degreeplanner.classes;

import com.google.common.base.CharMatcher;

import java.util.ArrayList;

public class Quarter { // NOTE: important rule: assume input is always correct (contract) so you fix invalid input instead of checking it
    private int index; // ex: first term (transfer units) = 0
    private String name; // ex: Spring 2020
    private ArrayList<Course> courses;

    // constructors (use whichever suits your purposes; you can set any part of this class later)
    // Todo: check if the minimum units allowed for enrollment is reached
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

    // helper function for reorder()
    private int extractDigits(String courseCode) {
        CharMatcher matcher = CharMatcher.javaLetter();
        String digitOnly = matcher.removeFrom(courseCode);
        int digits = Integer.parseInt(digitOnly);
        return digits;
    }

    //helper function for reorder()
    private String extractLetters(String courseCode) {
        CharMatcher matcher = CharMatcher.javaDigit();
        String StringOnly = matcher.removeFrom(courseCode);
        return StringOnly;
    }
    // helper function for reorder()
    private int partition(int low, int high) {
        // choose the pivot point
        int pivot = extractDigits(courses.get(high).getCode());
        int i = (low - 1);
        // partition the larger elements to the right of the pivot
        // and smaller elements to the left of the pivot
        for (int j = low; j < high; j++) {
            int coursePtr = extractDigits(courses.get(j).getCode());
            if (coursePtr <= pivot) {
                i++;
                Course temp = courses.get(i);
                courses.set(i, courses.get(j));
                courses.set(j, temp);
            }
        }
        Course temp = courses.get(i+1);
        courses.set(i+1, (courses.get(high)));
        courses.set(high, temp);
        return (i + 1);
    }

    /**
     * Reorder the course list using quick sort by choosing the last element
     * as the pivot point.
     * After sorting, the course list will be in the increasing order
     * according to the course codes.
     * @param low
     * @param high
     */
    public void reorder(int low, int high) {
        if (low < high) {
            // Select pivot position and put all the elements smaller
            // than pivot on left and greater than pivot on right
            int pi = partition(low, high);

            // Sort the elements on the left of pivot
            reorder(low, pi - 1);

            // Sort the elements on the right of pivot
            reorder(pi + 1, high);
        }
    }
    // TODO: check underdone units nums? varies for summer session ond transfer units


}