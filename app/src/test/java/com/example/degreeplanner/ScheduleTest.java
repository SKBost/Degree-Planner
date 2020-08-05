package com.example.degreeplanner;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.degreeplanner.classes.Course;
import com.example.degreeplanner.classes.Quarter;
import com.example.degreeplanner.classes.Schedule;
import com.example.degreeplanner.enums.GradingOption;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class ScheduleTest {

    /*@Before
    public void setUp() {
        //intent = new Intent(ApplicationProvider.getApplicationContext(), HeightActivity.class);
    }*/

    @Test
    public void testDefaultSchedule() {
        Schedule schedule = new Schedule();
        ArrayList<Quarter> quarters = schedule.getQuarters();
        assertEquals(0, quarters.size());
        assertEquals(0.0, schedule.getTotalUnits());
    }
//TODO: add more tests
}