package com.example.degreeplanner.ui.home;


import androidx.lifecycle.ViewModel;

import com.example.degreeplanner.classes.Checklist;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.Quarter;
import com.example.degreeplanner.classes.RequirementCategory;
import com.example.degreeplanner.classes.Schedule;
import com.example.degreeplanner.ui.requirements.RequirementsViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class SharedHomeViewModel extends ViewModel {

    // Create Schedule class
    protected Schedule mySchedule = new Schedule();
    int yearEntered = 2019;
    int numYears = 4;

    // Checklists for all categories
    Checklist majorChecklist = new Checklist(new ArrayList<RequirementCategory>
            (Collections.singletonList(RequirementsViewModel.majorCourses)), new ArrayList<Course>());
    Checklist minorChecklist = new Checklist(new ArrayList<RequirementCategory>
            (Collections.singletonList(RequirementsViewModel.minorCourses)), new ArrayList<Course>());
    Checklist collegeChecklist = new Checklist(new ArrayList<RequirementCategory>
            (Collections.singletonList(RequirementsViewModel.collegeCourses)), new ArrayList<Course>());
    Checklist universityChecklist = new Checklist(new ArrayList<RequirementCategory>
            (Collections.singletonList(RequirementsViewModel.universityCourses)), new ArrayList<Course>());
    // Holds location of seekBar
    int seekBarProgress = 0;

    /*
     * Getter method for seekBarProgress
     */
    public int getSeekBarProgress() {
        return seekBarProgress;
    }

    /*
     * Getter method for checklist
     */
    public Checklist getChecklist(String checklistName) {
        switch (checklistName) {
            case "major" :
                return majorChecklist;
            case "minor" :
                return minorChecklist;
            case "college" :
                return collegeChecklist;
            case "university" :
                return universityChecklist;
        }
        return majorChecklist;
    }

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
     * Setter method for the schedule
     */
    public void setMySchedule(Schedule newSchedule) {
        mySchedule = newSchedule;
    }

    /*
     * Setter method for checklists
     */
    public void setMyChecklists(String category, Checklist newChecklist) {
        switch (category) {
            case "major" :
                majorChecklist = newChecklist;
                break;
            case "minor" :
                minorChecklist = newChecklist;
                break;
            case "college" :
                collegeChecklist = newChecklist;
                break;
            case "university" :
                universityChecklist = newChecklist;
                break;
            default:
        }
    }

    /*
     * Setter method for seekBarProgress
     */
    public void setSeekBarProgress(int myProgress) {
        seekBarProgress = myProgress;
    }

    /*
     * Setter method for yearEntered
     */
    public void setYearEntered(int myYearEntered) {
        yearEntered = myYearEntered;
    }

    public void setNumYears(int myNumYears) {
        numYears = myNumYears;
    }

    /*
     * Method to reset the requirement categories for the checklists
     */
    public void resetChecklists() {
        // Reset major checklist
        majorChecklist.setRequirementCategories(new ArrayList<RequirementCategory>
                (Collections.singletonList(RequirementsViewModel.majorCourses)));
        minorChecklist.setRequirementCategories(new ArrayList<RequirementCategory>
                (Collections.singletonList(RequirementsViewModel.minorCourses)));
        collegeChecklist.setRequirementCategories(new ArrayList<RequirementCategory>
                (Collections.singletonList(RequirementsViewModel.collegeCourses)));
        universityChecklist.setRequirementCategories(new ArrayList<RequirementCategory>
                (Collections.singletonList(RequirementsViewModel.universityCourses)));
    }

    /*
     * Method to create 4 quarters per year and add to the schedule
     */
    public void createQuarters() {
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
    public Course addCourseToQuarter(String course, String quarter, int year) {
        Course addedCourse = null;
        // Find quarter to add course to
        for (Quarter q: getMySchedule().getQuarters()) {
            if (q.getName().equals(quarter + " " + year)) {
                // Find course
                for (Course c: RequirementsViewModel.unplannedCourses.getCourses()) {
                    String courseName = c.getDept() + " " + c.getCode();
                    if (courseName.equals(course)) {
                        addedCourse = c;
                    }
                }
                q.addCourse(addedCourse);
                return addedCourse;
            }
        }
        return addedCourse;
    }

    /*
     * Removes planned course from the associated quarter
     */
    public Course removeCourseFromQuarter(Course course) {
        // Find quarter to remove course from
        for (Quarter q: getMySchedule().getQuarters()) {
            // Find and remove course
            for (Course removedCourse: q.getCourses()) {
                if (removedCourse.compareCourses(course)) {
                    q.removeCourse(removedCourse);
                }
            }
        }
        return course;
    }


}