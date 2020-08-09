package com.example.degreeplanner;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;
import com.example.degreeplanner.enums.GradingOption;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequirementCategoryTest {

    /*@Before
    public void setUp() {
        //intent = new Intent(ApplicationProvider.getApplicationContext(), HeightActivity.class);
    }*/

    @Test
    public void testMinConstructor() { // test getters and default values
        RequirementCategory reqCat = new RequirementCategory();
        assertEquals(null, reqCat.getName());
        assertEquals(new ArrayList<Course>(), reqCat.getCourses());
    }

    @Test
    public void testMaxConstructor() { // test getters and values
        ArrayList<Course> compCourses = getSampleCourses1();
        RequirementCategory reqCat = new RequirementCategory("CS Major", compCourses);

        assertEquals("CS Major", reqCat.getName());
        assertEquals(compCourses, reqCat.getCourses());
    }

    @Test
    public void testSetters() { // test setters
        RequirementCategory reqCat = getSampleRequirementCategory();
        reqCat.setName("Design Minor");
        ArrayList<Course> courses = getSampleCourses2();
        reqCat.setCourses(courses);

        assertEquals("Design Minor", reqCat.getName());
        //System.out.println("course1: " + reqCat.getCourses().get(0).getDept() + reqCat.getCourses().get(0).getCode());
        assertEquals(courses, reqCat.getCourses());
    }

    @Test
    public void testAdder() {
        RequirementCategory reqCat = getSampleRequirementCategory();
        Course newCourse = new Course("CSE", "120");
        reqCat.addCourse(newCourse);
        assertTrue(reqCat.getCourses().contains(newCourse));
    }

    @Test
    public void testRemover() {
        RequirementCategory reqCat = getSampleRequirementCategory();
        Course deleted = new Course("CSE", "110");
        assertTrue(reqCat.containsCourse(deleted));
        reqCat.removeCourse(deleted);
        assertTrue(!reqCat.containsCourse(deleted));
    }

    @Test
    public void testUncompletedCourseGetter() {
        RequirementCategory reqCat = getSampleRequirementCategory();
        ArrayList<Course> compCourses = new ArrayList<Course>();
        Course course = new Course("CSE", "100", GradingOption.LETTER,
                4.0, "Why is this class so hard?");
        ArrayList<Course> uncompCourses = reqCat.getUncompletedCourses(compCourses);
        assertEquals(2, uncompCourses.size());

        compCourses.add(course);
        uncompCourses = reqCat.getUncompletedCourses(compCourses);
        assertEquals(2, uncompCourses.size());

        course = new Course("CSE", "110");
        compCourses.add(course);
        uncompCourses = reqCat.getUncompletedCourses(compCourses);
        assertEquals(1, uncompCourses.size());

        course = new Course("COGS", "102C");
        compCourses.add(course);
        uncompCourses = reqCat.getUncompletedCourses(compCourses);
        assertEquals(0, uncompCourses.size());
    }


    // produces a sample req cat with the max constructor
    private RequirementCategory getSampleRequirementCategory() {
        ArrayList<Course> compCourses = getSampleCourses1();
        RequirementCategory reqCat = new RequirementCategory("CS Major", compCourses);

        return reqCat;
    }

    private ArrayList<Course> getSampleCourses1() {
        ArrayList<Course> compCourses = new ArrayList<Course>();
        Course course1 = new Course("CSE", "110");
        Course course2 = new Course("COGS", "102C", GradingOption.LETTER,
                6.0, "Why is this class so many units?");
        compCourses.add(course1);
        compCourses.add(course2);

        return compCourses;
    }

    private ArrayList<Course> getSampleCourses2() {
        ArrayList<Course> compCourses = new ArrayList<Course>();
        Course course1 = new Course("COGS", "100");
        Course course2 = new Course("CSE", "100", GradingOption.LETTER,
                4.0, "Why is this class so hard?");
        compCourses.add(course1);
        compCourses.add(course2);

        return compCourses;
    }

}