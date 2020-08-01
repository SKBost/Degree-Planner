example.degreeplanner.classes;

import java.util.ArrayList;

public class Schedule { // Notes: refer to the structure of quarter-by-quarter worksheet when considering the fields.
    // quarter list for the entire schedule
    private ArrayList<com.example.degreeplanner.classes.Quarter> quarters;
    // total units for the entire schedule
    private double totalUnits;

    // minimal constructor
    public Schedule() {
        quarters = new ArrayList<com.example.degreeplanner.classes.Quarter>();
    }

    // maximal constructor
    public Schedule(ArrayList<com.example.degreeplanner.classes.Quarter> myQuarters, double myTotalUnits) {
        quarters = new ArrayList<com.example.degreeplanner.classes.Quarter>(myQuarters);
        totalUnits = myTotalUnits;
    }

    // getters
    public ArrayList<com.example.degreeplanner.classes.Quarter> getQuarters() { return quarters;}
    public double getTotalUnits() { return totalUnits;}

    // setters
    public void setQuarters(ArrayList<com.example.degreeplanner.classes.Quarter> newQuarters) { quarters = newQuarters;}
    public void setTotalUnits(double newTotalUnits) { totalUnits = newTotalUnits;}

    // possible TODO: checklist of AHI, DEI, etc,.
}