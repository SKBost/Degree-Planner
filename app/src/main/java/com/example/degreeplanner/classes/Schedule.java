package com.example.degreeplanner.classes;

import java.util.ArrayList;

public class Schedule { // Notes: refer to the structure of quarter-by-quarter worksheet when considering the fields.
    // quarter list for the entire schedule
    private ArrayList<Quarter> quarters;

    // minimal constructor
    public Schedule() {
        quarters = new ArrayList<>();
    }

    // maximal constructor
    public Schedule(ArrayList<Quarter> myQuarters) {
        quarters = new ArrayList<>(myQuarters);
    }

    // getters
    public ArrayList<Quarter> getQuarters() { return quarters;}

    // setters
    public void setQuarters(ArrayList<Quarter> newQuarters) { quarters = newQuarters;}

    public double getTotalUnits() {
        double totalUnits = 0;
        for (int i = 0; i < quarters.size(); i++) {
            totalUnits += quarters.get(i).getTotalUnits();
        }
        return totalUnits;
    }

    // possible TODO: checklist of AHI, DEI, etc,.
}