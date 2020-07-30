package com.example.degreeplanner;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.enums.GradingOption;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
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
    }
/*
    @Test
    public void testCourseWithInfo() { // test setters for route
        Route route = new Route();

        route.setName("Neighborhood Walk");

        assertEquals(route.getName(), "Neighborhood Walk");
    }
*/
}