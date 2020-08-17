package com.example.degreeplanner;

import com.example.degreeplanner.classes.Checklist;
import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.RequirementCategory;
import com.example.degreeplanner.enums.GradingOption;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ChecklistTest {
    ArrayList<Course> CogsCourses; // COGS 1, COGS 2, and COGS3
    ArrayList<Course> CseCourses; // CSE11, CSE 12, and CSE 30
    ArrayList<Course> PsycCourses; // PSYC 100, PSYC 101, and PSYC 102

    Course CSE20;
    Course DSC20;
    Course JAP1C;
    ArrayList<Course> completed; // CSE 20, DSC 20, and JAP 10C

    RequirementCategory COGS;
    RequirementCategory CSE;
    RequirementCategory PSYC;
    ArrayList<RequirementCategory> all;

    ArrayList<RequirementCategory> one;

    @Before
    public void setUp() {
        // cogs courses
        Course COGS1 = new Course("COGS", "1", GradingOption.LETTER, 4, "");
        Course COGS2 = new Course("COGS", "2", GradingOption.LETTER, 4, "");
        Course COGS3 = new Course("COGS", "3", GradingOption.LETTER, 4, "");
        CogsCourses = new ArrayList<Course>();
        CogsCourses.add(COGS1);
        CogsCourses.add(COGS2);
        CogsCourses.add(COGS3);
        COGS = new RequirementCategory("COGS", CogsCourses);
        // cse courses
        Course CSE11 = new Course("CSE", "11", GradingOption.LETTER, 4, "");
        Course CSE12 = new Course("CSE", "12", GradingOption.LETTER, 4, "");
        Course CSE30 = new Course("CSE", "30", GradingOption.LETTER, 4, "");
        CseCourses = new ArrayList<Course>();
        CseCourses.add(CSE11);
        CseCourses.add(CSE12);
        CseCourses.add(CSE30);
        CSE = new RequirementCategory("CSE", CseCourses);
        // psyc courses
        Course PSYC100 = new Course("PSYC", "100", GradingOption.LETTER, 4, "");
        Course PSYC101 = new Course("PSYC", "101", GradingOption.LETTER, 4, "");
        Course PSYC102 = new Course("PSYC", "102", GradingOption.LETTER, 4, "");
        PsycCourses = new ArrayList<Course>();
        PsycCourses.add(CSE11);
        PsycCourses.add(CSE12);
        PsycCourses.add(CSE30);
        PSYC = new RequirementCategory("PSYC", CseCourses);
        // completed courses
        CSE20 = new Course("CSE", "20", GradingOption.LETTER, 4, "");
        DSC20 = new Course("DSC", "20", GradingOption.LETTER, 4, "");
        JAP1C = new Course("JAP", "1C", GradingOption.LETTER, 5, "");
        completed = new ArrayList<Course>();
        completed.add(CSE20);
        completed.add(DSC20);
        completed.add(JAP1C);

        all = new ArrayList<>();
        all.add(CSE);
        all.add(COGS);
        all.add(PSYC);

        one = new ArrayList<>();
        one.add(CSE);
    }

    @Test
    public void testMinConstructor() { // test getters and default values
        Checklist min = new Checklist();
        assertNotNull(min.getRequirementCategories());
        assertNotNull(min.getCompletedCourses());
    }

    @Test
    public void testMaxConstructor() { // test getters and values
        Checklist max = new Checklist(all, completed);
        assertEquals(max.getCompletedCourses(), completed);
        assertEquals(max.getRequirementCategories(), all);
    }

    @Test
    public void testSetters() { // test setters
        Checklist test = new Checklist(all, completed);
        Course CSE12 = new Course("CSE", "12", GradingOption.LETTER, 4, "");
        Course DSC10 = new Course("DSC", "10", GradingOption.LETTER, 4, "");
        Course JAP1B = new Course("JAP", "1B", GradingOption.LETTER, 5, "");
        ArrayList<Course> newComp = new ArrayList<Course>();
        newComp.add(CSE12);
        newComp.add(DSC10);
        newComp.add(JAP1B);

        test.setCompletedCourses(newComp);
        assertEquals(test.getCompletedCourses(), newComp);


        test.setRequirementCategories(one);
        assertEquals(test.getRequirementCategories(), one);
    }

    @Test
    public void testAddRequirementCategory() {
        Checklist test = new Checklist(one, completed);
        test.addRequirementCategory(COGS);
        test.addRequirementCategory(PSYC);
        assertEquals(test.getRequirementCategories(), all);
    }

    @Test
    public void testRemoveRequirementCategory() {
        Checklist test = new Checklist(all, completed);
        test.removeRequirementCategory(COGS);
        test.removeRequirementCategory(PSYC);
        assertEquals(test.getRequirementCategories(), one);
    }

    @Test
    public void testAddCompletedCourse() {
        Checklist test = new Checklist(one, completed);
        Course CSE11 = new Course("CSE", "11", GradingOption.LETTER, 4, "");
        test.addCompletedCourse(CSE11);
        assertTrue(test.getCompletedCourses().contains(CSE11));
        assertTrue(test.getCompletedCourses().contains(CSE20));
        assertTrue(test.getCompletedCourses().contains(DSC20));
        assertTrue(test.getCompletedCourses().contains(JAP1C));
        Course PSYC100 = new Course("PSYC", "100", GradingOption.LETTER, 4, "");
        test.addCompletedCourse(PSYC100);
        assertTrue(test.getCompletedCourses().contains(PSYC100));
        assertTrue(test.getCompletedCourses().contains(CSE20));
        assertTrue(test.getCompletedCourses().contains(DSC20));
        assertTrue(test.getCompletedCourses().contains(JAP1C));
    }

    @Test
    public void testRemoveCompletedCourse() {
        Checklist test = new Checklist(one, completed);
        test.removeCompletedCourse(CSE20);
        assertFalse(test.getCompletedCourses().contains(CSE20));
        assertTrue(test.getCompletedCourses().contains(DSC20));
        assertTrue(test.getCompletedCourses().contains(JAP1C));
    }

    @Test
    public void testGetUncompletedRequirementCategories() {
        Checklist test1 = new Checklist(one, completed);
        ArrayList<RequirementCategory> unComp1 = test1.getUncompletedRequirementCategories();
        assertEquals(unComp1, one);

        Checklist test2 = new Checklist(all, completed);
        ArrayList<RequirementCategory> unComp2 = test2.getUncompletedRequirementCategories();
        assertEquals(unComp2, all);
    }

}