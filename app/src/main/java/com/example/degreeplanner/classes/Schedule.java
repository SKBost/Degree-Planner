package com.example.degreeplanner.classes;

import java.util.ArrayList;

// untested
public class Schedule { // Notes: refer to the structure of quarter-by-quarter worksheet when considering the fields.
    // quarter list for the entire schedule
    private ArrayList<Quarter> quarters;

    // minimal constructor
    public Schedule() {
        quarters = new ArrayList<Quarter>();
    }

    // maximal constructor
    public Schedule(ArrayList<Quarter> myQuarters) {
        quarters = new ArrayList<Quarter>(myQuarters);
    }

    // getters
    public ArrayList<Quarter> getQuarters() { return quarters; }

    // setters
    public void setQuarters(ArrayList<Quarter> newQuarters) { quarters = newQuarters; }

    // other modifiers
    public void addQuarter(Quarter newQuarter) { quarters.add(newQuarter); }

    public void removeQuarter(Quarter removedQuarter) {
        for(int i = 0; i < quarters.size(); i++) {
            Quarter quarter = quarters.get(i);
            if(quarter.equals(removedQuarter)) {
                quarters.remove(i);
            }
        }
    }

    // TODO: add quarter sorting mechanism by index

    // get total units for every quarter
    public double getTotalUnits() {
        double totalUnits = 0;
        for (int i = 0; i < quarters.size(); i++) {
            totalUnits += quarters.get(i).getTotalUnits();
        }
        return totalUnits;
    }

    // possible TODO: checklist of AHI, DEI, etc,.
}