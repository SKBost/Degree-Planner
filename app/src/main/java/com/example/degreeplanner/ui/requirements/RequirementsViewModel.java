package com.example.degreeplanner.ui.requirements;

import androidx.lifecycle.ViewModel;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;

import java.util.ArrayList;
import java.util.Arrays;


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



    public ArrayList<Course> getAllCourses() {
        // todo: do a null check
        return unplannedCourses.getCourses();
    }

    public ArrayList<Course> getMajorCourses() {
        // todo: do a null check
        return majorCourses.getCourses();
    }

    public ArrayList<Course> getMinorCourses() {
        // todo: do a null check
        return minorCourses.getCourses();
    }

    public ArrayList<Course> getCollegeCourses() {
        // todo: do a null check
        return collegeCourses.getCourses();
    }

    public ArrayList<Course> getUniversityCourses() {
        // todo: do a null check
        return universityCourses.getCourses();
    }
}