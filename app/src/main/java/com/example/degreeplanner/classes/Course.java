package com.example.degreeplanner.classes;

import com.example.degreeplanner.enums.GradingOption;

public class Course { //NOTES: test base class; TODO: need to add prereqs class and related method implemntations

    private String dept; // ex: COGS oe CSE
    private String code; // ex: 102C or 11
    private double units; // ex: 2 or 4 (note: double in case of 0.5 unit classes, easier division, etc)
    private GradingOption option; // LETTER, PNP, or UNCOUNTED
    private String notes;
    // TODO: PRE-REQS (note: do not change pre-reqs constructor signatures to add pre-reqs; if you want, add a new constructor instead)
    //private ArrayList<Prerequisite> prereqs; //note: prereq class does not exist yet

    // constructors

    // minimal constructor (note: we fill in default vals for now, but null can be valid as well if you check for it)
    public Course(String myDept, String myCode) {
        dept = myDept;
        code = myCode;
        option = GradingOption.UNCOUNTED;
        units = 0;
        notes = "";
    }

    // full constructor (if needed)
    public Course(String myDept, String myCode, GradingOption myOption, double myUnits, String myNotes) {
        dept = myDept;
        code = myCode;
        option = myOption;
        units = myUnits;
        notes = myNotes;
    }

    // getters (note: packed together because they are trivial)
    public String getDept() { return dept; }
    public String getCode() { return code; }
    public double getUnits() { return units; }
    public GradingOption getOption() { return option; }
    public String getNotes() { return notes; }
    //public ArrayList<Prerequisite> getPrereqs() { return prereqs; }

    // setters (note: packed together because they are trivial)
    public void setDept(String newDept) { dept = newDept; }
    public void setCode(String newCode) { code = newCode; }
    public void setUnits(double newUnits) { units = newUnits; }  //TODO: maybe check for invalid unit input?
    public void setOption(GradingOption newOption) { option = newOption; }
    public void setNotes(String newNotes) { notes = newNotes; }
    //public void setPrereqs(ArrayList<Prerequisite> newPrereqs) { prereqs = newPrereqs; }

    /* templates:
    public x getx() { return x; }
    public void setx(x newx) { x = newx; }
     */

    // likely todo: method for adding/removing prereqs

    // compares if two courses are the same
    public boolean compareCourses(Course myCourse) {
        if(this.dept.compareTo(myCourse.dept) == 0 && this.code.compareTo(myCourse.code) == 0) {
            // compare the grading options in addition to dept and code for cases of retaking
            if(option == myCourse.option) {
                return true;
            }
        }
        return false;
    }
}
