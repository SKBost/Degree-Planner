package com.example.degreeplanner.ui.requirements;

import android.app.Application;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;

import java.util.ArrayList;
import java.util.Arrays;


public class RequirementsViewModel extends ViewModel {

    public static RequirementCategory allCourses = new RequirementCategory("All Course Info");
    // Containers for all categories
    public static RequirementCategory majorCourses = new RequirementCategory("Major Courses");
    public static RequirementCategory minorCourses = new RequirementCategory("Minor Courses");
    public static RequirementCategory collegeCourses = new RequirementCategory("College Courses");
    public static RequirementCategory universityCourses = new RequirementCategory("University Courses");
    // List of all categories
    public static ArrayList<RequirementCategory> allReqCats =
            new ArrayList<>(Arrays.asList(allCourses, majorCourses, minorCourses, collegeCourses, universityCourses));



    public ArrayList<Course> getAllCourses() {
        // todo: do a null check
        return allCourses.getCourses();
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