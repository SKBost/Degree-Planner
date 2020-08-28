package com.example.degreeplanner.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    String major;
    String minor;
    String college;
    String yearEntered;
    int numYears;

    /*
     * Getter method for numYears
     */
    public int getNumYears() { return numYears; }

    /*
     * Getter method for yearEntered
     */
    public int getYearEntered() {
        return Integer.parseInt(yearEntered);
    }

    /*
     * Sets profile information based on info from welcome screen
     */
    public void setProfileInformation
            (String myMajor, String myMinor, String myCollege, String myYearEntered, String myYearGraduated) {
        major = myMajor;
        minor = myMinor;
        college = myCollege;
        yearEntered = myYearEntered;
        numYears = Integer.parseInt(myYearGraduated) - Integer.parseInt(myYearEntered);
    }
}
