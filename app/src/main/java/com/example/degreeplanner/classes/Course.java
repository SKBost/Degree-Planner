package com.example.degreeplanner.classes;

import com.example.degreeplanner.enums.GradingOption;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Course { //NOTES: test base class; TODO: need to add prereqs class and related method implemntations

    private String dept; // ex: COGS oe CSE
    private String code; // ex: 102C or 11
    private double units; // ex: 2 or 4 (note: double in case of 0.5 unit classes, easier division, etc)
    private GradingOption option; // LETTER, PNP, or UNCOUNTED
    private String notes;
    // TODO: PRE-REQS (note: do not change pre-reqs constructor signatures to add pre-reqs; if you want, add a new constructor instead)
    private ArrayList<Course> prereqs; //note: prereq class does not exist yet; using course class for simple "and" operations

    // constructors

    // minimal constructor (note: we fill in default vals for now, but null can be valid as well if you check for it)
    public Course(String myDept, String myCode) {
        dept = myDept;
        code = myCode;
        option = GradingOption.UNCOUNTED;
        units = 0;
        notes = "";
        prereqs = new ArrayList<Course>();
    }

    // almost full constructor (if needed)
    public Course(String myDept, String myCode, GradingOption myOption, double myUnits, String myNotes) {
        dept = myDept;
        code = myCode;
        option = myOption;
        units = myUnits;
        notes = myNotes;
        prereqs = new ArrayList<Course>();
    }

    // full constructor (if needed)
    public Course(String myDept, String myCode, GradingOption myOption, double myUnits, String myNotes, ArrayList<Course> myPrereqs) {
        dept = myDept;
        code = myCode;
        option = myOption;
        units = myUnits;
        notes = myNotes;
        prereqs = myPrereqs;
    }



    // getters (note: packed together because they are trivial)
    public String getDept() { return dept; }
    public String getCode() { return code; }
    public double getUnits() { return units; }
    public GradingOption getOption() { return option; }
    public String getNotes() { return notes; }
    public ArrayList<Course> getPrereqs() { return prereqs; }

    // setters (note: packed together because they are trivial)
    public void setDept(String newDept) { dept = newDept; }
    public void setCode(String newCode) { code = newCode; }
    public void setUnits(double newUnits) { units = newUnits; }  //TODO: maybe check for invalid unit input?
    public void setOption(GradingOption newOption) { option = newOption; }
    public void setNotes(String newNotes) { notes = newNotes; }
    public void setPrereqs(ArrayList<Course> newPrereqs) { prereqs = newPrereqs; }

    // other

    /* templates:
    public x getx() { return x; }
    public void setx(x newx) { x = newx; }
     */

    public void addPrereq(Course newPrereq) {
        prereqs.add(newPrereq);
    }

    public void removePrereq(Course removed) { // TODO need to test
        for(int i = 0; i < prereqs.size(); i++) {
            if(removed.sameCourseNameAs(prereqs.get(i))) {
                prereqs.remove(i);
                break;
            }
        }
    }

    // compares if two courses are the same
    public boolean compareCourses(Course myCourse) { // TODO need to test
        if(this.dept.compareTo(myCourse.dept) == 0 && this.code.compareTo(myCourse.code) == 0) {
            // compare the grading options in addition to dept and code for cases of retaking
            if(option == myCourse.option) {
                return true;
            }
        }
        return false;
    }

    // tests if dept and code are the same
    public boolean sameCourseNameAs(Course myCourse) { // do not change this. TODO needs to be tested
        if(this.dept.compareTo(myCourse.dept) == 0 && this.code.compareTo(myCourse.code) == 0) {
            return true;
        }
        return false;
    }

    // see if all prereqs have been fulfilled
    public boolean prereqsFulfilled(ArrayList<Course> compCourses) {
        for(int i = 0; i < prereqs.size(); i++) { // check if each prereq has one same completed class
            Course prereq = prereqs.get(i);
            boolean fulfilled = false;
            for(int j = 0; j < compCourses.size(); j++) {
                Course compCourse = compCourses.get(j);
                if(prereq.sameCourseNameAs(compCourse)) { // fulfilled
                    fulfilled = true;
                    break;
                }
            }
            if(!fulfilled) { // no match found for prereq
                return false;
            }
        }
        return true; // no unfulfilled prereqs
    }


}
