package com.example.degreeplanner;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.Quarter;
import com.example.degreeplanner.enums.GradingOption;

import org.junit.Test;

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
    public void testExtractDigits() {
        Course COGS1 = new Course("COGS", "1", GradingOption.LETTER, 4, "");
        Course CSE11 = new Course("CSE", "11", GradingOption.LETTER, 4, "");
        Course JAP1A = new Course("JAP", "1A", GradingOption.LETTER, 5, "");
        ArrayList<Course> testCourses1 = new ArrayList<Course>();
        testCourses1.add(COGS1);
        testCourses1.add(CSE11);
        testCourses1.add(JAP1A);
        Quarter firstFall = new Quarter(13, "firstFall", testCourses1);
        assertEquals(1, firstFall.extractDigits(COGS1.getCode()));
        assertEquals(11, firstFall.extractDigits(CSE11.getCode()));
        assertEquals(1, firstFall.extractDigits(JAP1A.getCode()));
    }
    */
    /*
    @Test
    public void testExtractLetters() {
        Course COGS1 = new Course("COGS", "1", GradingOption.LETTER, 4, "");
        Course CSE11 = new Course("CSE", "11", GradingOption.LETTER, 4, "");
        Course JAP1A = new Course("JAP", "1A", GradingOption.LETTER, 5, "");
        ArrayList<Course> testCourses1 = new ArrayList<Course>();
        testCourses1.add(COGS1);
        testCourses1.add(CSE11);
        testCourses1.add(JAP1A);
        Quarter firstFall = new Quarter(13, "firstFall", testCourses1);
        assertEquals("", firstFall.extractLetters(COGS1.getCode()));
        assertEquals("", firstFall.extractLetters(CSE11.getCode()));
        assertEquals("A", firstFall.extractLetters(JAP1A.getCode()));
    }
    */
//TODO: add more tests
    /*
    @Test
    public void testMaxConstructor() { // test getters and default values for course

    }
    */
    @Test
    public void testReorder() {
        Course COGS1 = new Course("COGS", "1", GradingOption.LETTER, 4, "");
        Course CSE11 = new Course("CSE", "11", GradingOption.LETTER, 4, "");
        Course JAP1A = new Course("JAP", "1A", GradingOption.LETTER, 5, "");
        ArrayList<Course> testCourses1 = new ArrayList<Course>();
        testCourses1.add(COGS1);
        testCourses1.add(CSE11);
        testCourses1.add(JAP1A);
        Quarter firstFall = new Quarter(13, "firstFall", testCourses1);
        // setting order of the test quarter: COGS1, CSE11, JAP1A
        assertEquals(COGS1, firstFall.getCourses().get(0));
        assertEquals(CSE11, firstFall.getCourses().get(1));
        assertEquals(JAP1A, firstFall.getCourses().get(2));
        // expected after reordering: COGS1, JAP1A, CSE11
        firstFall.reorder(0, firstFall.getCourses().size() - 1);
        assertEquals(COGS1, firstFall.getCourses().get(0));
        assertEquals(JAP1A, firstFall.getCourses().get(1));
        assertEquals(CSE11, firstFall.getCourses().get(2));
    }
}