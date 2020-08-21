package com.example.degreeplanner.ui.home;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.degreeplanner.R;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.Quarter;
import com.example.degreeplanner.classes.Schedule;
import com.example.degreeplanner.ui.profile.ProfileFragment;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;

import java.util.ArrayList;

public class SharedHomeViewModel extends ViewModel {

    // Create Schedule class
    protected Schedule mySchedule = new Schedule();
    // todo: change to user input value
    int yearEntered = 2019;

    /*
     * Getter method for mySchedule
     */
    public Schedule getMySchedule() {
        return mySchedule;
    }

    /*
     * Getter method for year entered
     */
    public int getMyYearEntered() {
        return yearEntered;
    }

    /*
     * Method to create 4 quarters per year and add to the schedule
     */
    public void createQuarters() {
        // todo: replace with user input
        int numYears = 4;
        int year = yearEntered;
        for (int i = 0, j = 0; i < numYears; i+=1, year+=1, j+=4) {
            createQuartersHelper(j, year);
        }
    }

    /*
     * createQuarters helper method to name each quarter
     */
    public void createQuartersHelper(int index, int year) {
        Quarter fallQuarter = new Quarter(index, "Fall" + " " + year);
        Quarter winterQuarter = new Quarter(index + 1, "Winter" + " " + (year + 1));
        Quarter springQuarter = new Quarter(index + 2, "Spring" + " " + (year + 1));
        Quarter summerQuarter = new Quarter(index + 3, "Summer" + " " + (year + 1));
        getMySchedule().addQuarter(fallQuarter);
        getMySchedule().addQuarter(winterQuarter);
        getMySchedule().addQuarter(springQuarter);
        getMySchedule().addQuarter(summerQuarter);
    }

    /*
     * Adds planned course from popup to correct quarter
     */
    public void addCourseToQuarter(String course, String quarter, int year) {
        // Find quarter to add course to
        for (Quarter q: getMySchedule().getQuarters()) {
            if (q.getName().equals(quarter + " " + year)) {
                Course addedCourse = null;
                // Find course
                for (Course c: RequirementsViewModel.allCourses.getCourses()) {
                    String courseName = c.getDept() + " " + c.getCode();
                    if (courseName.equals(course)) {
                        addedCourse = c;
                    }
                }
                q.addCourse(addedCourse);
            }
        }
    }



}