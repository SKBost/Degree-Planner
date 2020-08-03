package com.example.degreeplanner.classes;

import java.util.ArrayList;

public class Schedule { // Notes: refer to the structure of quarter-by-quarter worksheet when considering the fields.
    // quarter list for the entire schedule
    private ArrayList<Quarter> quarters;
    // total units for the entire schedule
    private double totalUnits;

    // minimal constructor
    public Schedule() {
        quarters = new ArrayList<>();
    }

    // maximal constructor
    public Schedule(ArrayList<Quarter> myQuarters, double myTotalUnits) {
        quarters = new ArrayList<>(myQuarters);
        totalUnits = myTotalUnits;
    }

    // getters
    public ArrayList<Quarter> getQuarters() { return quarters;}
    public double getTotalUnits() { return totalUnits;}

    // setters
    public void setQuarters(ArrayList<Quarter> newQuarters) { quarters = newQuarters;}
    public void setTotalUnits(double newTotalUnits) { totalUnits = newTotalUnits;}

    // possible TODO: checklist of AHI, DEI, etc,.
}