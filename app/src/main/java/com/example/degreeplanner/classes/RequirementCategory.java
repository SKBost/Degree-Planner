package com.example.degreeplanner.classes;

import java.util.ArrayList;

public class RequirementCategory { // TODO: potentially add support for boolean combinations: and/or
    private String name;
    private ArrayList<Course> courses;

    // constructors

    public RequirementCategory() {
        courses = new ArrayList<Course>();
    } // null name field

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
        // note: possibly add prevention of adding multiple of the same? todo
    }

    public void removeCourse(Course course) { // checking dept and code only
        for(int i = 0; i < courses.size(); i++) {
            // removes first match
            if(courses.get(i).getDept().equals(course.getDept())
                    && courses.get(i).getCode().equals(course.getCode())) {
                courses.remove(i);
                break;
            }
        }
    }

    public boolean containsCourse(Course course) { // checking dept and code only
        //System.out.println("checking if this contains the course: " + course.getDept() + course.getCode());
        for(int i = 0; i < courses.size(); i++) {
            //System.out.println("testing the course: " + courses.get(i).getDept() + courses.get(i).getCode());
            if(courses.get(i).getDept().equals(course.getDept())
                    && courses.get(i).getCode().equals(course.getCode())) {
                //System.out.println("breaking with true");
                return true;
            }
        }
        //System.out.println("didn't find it");
        return false;
    }

    // get uncompleted courses in the requirement category, given a general list of completed courses
    public ArrayList<Course> getUncompletedCourses(ArrayList<Course> compList) {
        ArrayList<Course> uncompList = new ArrayList<Course>();

        // loop through required courses
        for(int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            boolean matchFound = false;

            // loop through completed courses
            for(int j = 0; j < compList.size(); j++) {
                Course compCourse = compList.get(j);
               //System.out.println("comparing " + course.getDept() + course.getCode() + " against " + compCourse.getDept() + compCourse.getCode());

                if (course.getDept().equals(compCourse.getDept())
                        && course.getCode().equals(compCourse.getCode())) {
                    matchFound = true;
                  //  System.out.println("match found!");
                    break;
                }
            }

            if(!matchFound) {
                uncompList.add(course);
                //System.out.println("added to uncompList: " + course.getDept() + course.getCode());
            }
        }

        return uncompList;
    }


}
