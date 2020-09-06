package com.example.degreeplanner.ui.requirements;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;


public class RequirementsViewModel extends ViewModel {

    public static RequirementCategory unplannedCourses = new RequirementCategory("All Course Info");
    // Containers for all categories
    public static RequirementCategory majorCourses = new RequirementCategory("Major Courses");
    public static RequirementCategory minorCourses = new RequirementCategory("Minor Courses");
    public static RequirementCategory collegeCourses = new RequirementCategory("College Courses");
    public static RequirementCategory universityCourses = new RequirementCategory("University Courses");
    // List of all categories
    public static ArrayList<RequirementCategory> allReqCats =
            new ArrayList<>(Arrays.asList(unplannedCourses, majorCourses, minorCourses, collegeCourses, universityCourses));

    // Getters for Requirement Category objects
    public RequirementCategory getMajorCoursesObject() { return majorCourses; }
    public RequirementCategory getMinorCoursesObject() { return minorCourses; }
    public RequirementCategory getCollegeCourseObject() { return collegeCourses; }
    public RequirementCategory getUniversityCoursesObject() { return universityCourses; }
    public RequirementCategory getUnplannedCoursesObject() { return unplannedCourses; }


    // Setters for Requirement Category objects
    public void setMajorCoursesObject(RequirementCategory newMajorCourses) {
        majorCourses = newMajorCourses;
    }

    public void setMinorCoursesObject(RequirementCategory newMinorCourses) {
        minorCourses = newMinorCourses;
    }

    public void setCollegeCoursesObject(RequirementCategory newCollegeCourses) {
        collegeCourses = newCollegeCourses;
    }

    public void setUniversityCoursesObject(RequirementCategory newUniversityCourses) {
        universityCourses = newUniversityCourses;
    }

    public void setUnplannedCoursesObject(RequirementCategory newUnplannedCourses) {
        unplannedCourses = newUnplannedCourses;
    }

    // Getters for course lists of requirement category objects
    public ArrayList<Course> getAllCourses() {
        return unplannedCourses.getCourses();
    }

    public ArrayList<Course> getMajorCourses() {
        return majorCourses.getCourses();
    }

    public ArrayList<Course> getMinorCourses() {
        return minorCourses.getCourses();
    }

    public ArrayList<Course> getCollegeCourses() {
        return collegeCourses.getCourses();
    }

    public ArrayList<Course> getUniversityCourses() {
        return universityCourses.getCourses();
    }
}