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


public class RequirementsViewModel extends ViewModel {

    public static RequirementCategory allCourses = new RequirementCategory("All Course Info");


    public ArrayList<Course> getAllCourses() {
        // todo: do a null check
        return allCourses.getCourses();
    }

}