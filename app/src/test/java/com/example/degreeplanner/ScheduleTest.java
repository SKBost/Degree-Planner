package com.example.degreeplanner;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.Quarter;
import com.example.degreeplanner.classes.Schedule;
import com.example.degreeplanner.enums.GradingOption;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class ScheduleTest {
    /**
     * Notes:
     * When comparing, the order of adding different courses and quarters must be the same as those
     * in the setup. Could be a potential issue. May be solved by quarter sorting mechanism
     */
    // set up the first three quarters
    Quarter firstFall;
    Quarter firstWinter;
    Quarter firstSpring;

    ArrayList<Course> prereqs;
    ArrayList<Course> untaken;
    ArrayList<Quarter> threeQuarters;
    ArrayList<Quarter> oneQuarter;

    // schedules of different number of quarters
    Schedule fullSchedule;
    Schedule emptySchedule;
    Schedule oneThirdFullSchedule;

    @Before
    public void setUp() {

        // set up first 3 quarters
        Course COGS1 = new Course("COGS", "1", GradingOption.LETTER, 4, "");
        Course CSE11 = new Course("CSE", "11", GradingOption.LETTER, 4, "");
        Course JAP1A = new Course("JAP", "1A", GradingOption.LETTER, 5, "");
        ArrayList<Course> testCourses1 = new ArrayList<Course>();
        testCourses1.add(COGS1);
        testCourses1.add(
                CSE11);
        testCourses1.add(JAP1A);
        firstFall = new Quarter(13, "firstFall", testCourses1);
        Course CSE12 = new Course("CSE", "12", GradingOption.LETTER, 4, "");
        Course DSC10 = new Course("DSC", "10", GradingOption.LETTER, 4, "");
        Course JAP1B = new Course("JAP", "1B", GradingOption.LETTER, 5, "");
        ArrayList<Course> testCourses2 = new ArrayList<Course>();
        testCourses2.add(CSE12);
        testCourses2.add(DSC10);
        testCourses2.add(JAP1B);
        firstWinter = new Quarter(13, "firstWinter", testCourses2);
        Course CSE20 = new Course("CSE", "20", GradingOption.LETTER, 4, "");
        Course DSC20 = new Course("DSC", "20", GradingOption.LETTER, 4, "");
        Course JAP1C = new Course("JAP", "1C", GradingOption.LETTER, 5, "");
        ArrayList<Course> testCourses3 = new ArrayList<Course>();
        testCourses3.add(CSE20);
        testCourses3.add(DSC20);
        testCourses3.add(JAP1C);
        firstSpring = new Quarter(13, "firstSpring", testCourses3);

        // set up a ArrayList of Quarters for first 3 quarters
        threeQuarters = new ArrayList<>();
        threeQuarters.add(firstFall);
        threeQuarters.add(firstWinter);
        threeQuarters.add(firstSpring);

        // set up a ArrayList of Quarters for first 1 quarters
        oneQuarter = new ArrayList<>();
        oneQuarter.add(firstFall);

        // a full schedule with all three Quarters
        fullSchedule = new Schedule(threeQuarters);

        // a empty Schedule using minimal constructor
        emptySchedule = new Schedule();

        // a schedule with only one quarter
        oneThirdFullSchedule = new Schedule(oneQuarter);

        // a list of prereqs for CSE20
        prereqs = new ArrayList<Course>();
        prereqs.add(CSE11);
        // untaken courses
        untaken = new ArrayList<>();
        untaken.add(CSE20);
    }

    @Test
    public void testDefaultSchedule() {
        Schedule schedule = new Schedule();
        ArrayList<Quarter> quarters = schedule.getQuarters();
        assertEquals(0, quarters.size());
        assertEquals(0.0, schedule.getTotalUnits());
    }

    @Test
    public void testGetQuarters() {
        // test schedule with only one quarter
        assertEquals(oneQuarter, oneThirdFullSchedule.getQuarters());

        // test full schedule
        assertEquals(threeQuarters, fullSchedule.getQuarters());
    }

    @Test
    public void testSetQuarters() {
        emptySchedule.setQuarters(oneQuarter);
        assertEquals(oneQuarter, emptySchedule.getQuarters());

        emptySchedule.setQuarters(threeQuarters);
        assertEquals(threeQuarters, emptySchedule.getQuarters());
    }

    @Test
    public void testAddQuarter() {
        emptySchedule.addQuarter(firstFall);
        assertEquals(oneQuarter, emptySchedule.getQuarters());

        emptySchedule.addQuarter(firstWinter);
        emptySchedule.addQuarter(firstSpring);
        assertEquals(threeQuarters, emptySchedule.getQuarters());
        // when adding quarters, the order must be the same for the test to pass.
    }

    @Test
    public void testGetTotalUnits() {
        assertEquals(0.0, emptySchedule.getTotalUnits());
        assertEquals(39.0, fullSchedule.getTotalUnits());
        assertEquals(13.0, oneThirdFullSchedule.getTotalUnits());
    }

    /**
     * Notes: for the method getCoursesCompletedBeforeQuarter(qtrIdx)
     * qtrIdx = 1 refer to before the second quarter, and that may cause some confusion for the users
     */
    @Test
    public void testGetCoursesCompletedBeforeQuarter() {
        ArrayList<Course> cpltBefore = fullSchedule.getCoursesCompletedBeforeQuarter(1);
        assertEquals(cpltBefore, firstFall.getCourses());
    }

    @Test
    public void testGetSuggestedCourses() {
        Course CSE20 = new Course("CSE", "20", GradingOption.LETTER, 4, "");
        CSE20.setPrereqs(prereqs);
        ArrayList<Course> cpltBefore = fullSchedule.getCoursesCompletedBeforeQuarter(1);
        ArrayList<Course> expectedSuggestion = new ArrayList<>();
        expectedSuggestion.add(CSE20);
        ArrayList<Course> actualSuggestion = fullSchedule.getSuggestedCourses(untaken, 1);
        assertEquals(actualSuggestion, expectedSuggestion);

        /**
         * Notes for getSuggestedCourses:
         * Test will only pass if the exact same course is used in the parameter untakenCourses,
         * and the list of course returned by getCoursesCompletedBeforeQuarter()
         * Test will not pass in other cases.
         */

    }


}