package com.example.degreeplanner;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.enums.GradingOption;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

public class CourseTest {

    /*@Before
    public void setUp() {
        //intent = new Intent(ApplicationProvider.getApplicationContext(), HeightActivity.class);
    }*/

    @Test
    public void testDefaultCourse() { // test getters and default values for course
        Course course = new Course("COGS", "102C");
        assertEquals("COGS", course.getDept());
        assertEquals("102C", course.getCode());
        assertEquals(0.0, course.getUnits());
        assertEquals(GradingOption.UNCOUNTED, course.getOption());
        assertEquals("", course.getNotes());
    }

    @Test
    public void testCourseWithInfo() { // test setters for course
        // setup
        Course course = new Course("COGS", "102C");
        course.setDept("CSE");
        course.setCode("110");
        course.setUnits(4.0);
        course.setOption(GradingOption.LETTER);
        course.setNotes("please let me pass");

        // testing
        assertEquals("CSE", course.getDept());
        assertEquals("110", course.getCode());
        assertEquals(4.0, course.getUnits());
        assertEquals(GradingOption.LETTER, course.getOption());
        assertEquals("please let me pass", course.getNotes());
    }

}