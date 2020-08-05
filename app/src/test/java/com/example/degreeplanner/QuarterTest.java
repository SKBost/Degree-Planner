package com.example.degreeplanner;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.Quarter;
import com.example.degreeplanner.enums.GradingOption;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class QuarterTest {

    /*@Before
    public void setUp() {
        //intent = new Intent(ApplicationProvider.getApplicationContext(), HeightActivity.class);
    }*/

    @Test
    public void testDefaultQuarter() {
        Quarter quarter = new Quarter();
        ArrayList<Course> courses = quarter.getCourses();

        assertEquals(0, courses.size());
        assertEquals(0.0, quarter.getTotalUnits());
        assertEquals(null, quarter.getName());
        assertEquals(0, quarter.getIndex()); // uninitialized int goes to 0 automatically
        assertEquals(true, quarter.hasValidUnitNum()); // 0 <= 22 for now 8/4/20
    }

    @Test
    public void testMinConstructor() { // test getters and default values for course
        // setup
        Quarter quarter = new Quarter();
        ArrayList<Course> courses = quarter.getCourses();

        Course course1 = new Course("CSE", "110");
        Course course2 = new Course("COGS", "102C", GradingOption.LETTER, 6.0, "Why is this class so many units?");
        quarter.addCourse(course1);
        quarter.addCourse(course2);

        // testing
        assertEquals(2, courses.size());

        // test first course
        Course course = courses.get(0);
        assertEquals("CSE", course.getDept());
        assertEquals("110", course.getCode());
        assertEquals(0.0, course.getUnits());
        assertEquals(GradingOption.UNCOUNTED, course.getOption());
        assertEquals("", course.getNotes());

        // test second course
        course = courses.get(1);
        assertEquals("COGS", course.getDept());
        assertEquals("102C", course.getCode());
        assertEquals(6.0, course.getUnits());
        assertEquals(GradingOption.LETTER, course.getOption());
        assertEquals("Why is this class so many units?", course.getNotes());
    }

    @Test
    public void testUnitsAndValidity() { // test getters and default values for course
        // setup
        Quarter quarter = new Quarter();
        Course course1 = new Course("CSE", "110");
        Course course2 = new Course("COGS", "102C", GradingOption.LETTER, 6.0, "Why is this class so many units?");
        quarter.addCourse(course1);
        quarter.addCourse(course2);

        // testing
        ArrayList<Course> courses = quarter.getCourses();

        // test unit counter
        assertEquals(6.0, quarter.getTotalUnits());
        assertEquals(true, quarter.hasValidUnitNum());

        // with change
        courses.get(0).setUnits(4.0);
        assertEquals(10.0, quarter.getTotalUnits());
        assertEquals(true, quarter.hasValidUnitNum());
        // note: below 12, but valid at this point only because we check for at or below 22 (8/4/20)
    }



    /*
    @Test
    public void testMaxConstructor() { // test getters and default values for course

    }
    */
}