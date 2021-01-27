package com.sergio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class TaskCalendarTest {
    TaskCalendar taskCalendar = new TaskCalendar();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void checkList() {
        taskCalendar.checkList();

        Task expected = taskCalendar.list.getFirst();
        Task task = new Task("30/01/2021", "first", IsCompleted.NOTCOMPLETED);

        Assert.assertEquals(expected, task);
    }

    @Test
    public void addTask() throws ParseException {
        taskCalendar.checkList();

        int lengthBefore = taskCalendar.list.size();

        Task task = new Task("30/01/2021", "first", IsCompleted.NOTCOMPLETED);
        taskCalendar.list.add(task);

        int lengthAfter = taskCalendar.list.size();

        Assert.assertEquals(lengthBefore + 1, lengthAfter);
    }

    @Test
    public void saveList() {
        taskCalendar.checkList();
        taskCalendar.saveList();

        TaskCalendar taskCalendar1 = new TaskCalendar();
        taskCalendar1.checkList();

        for (int i = 0; i < taskCalendar.list.size(); i++) {
            Assert.assertEquals(taskCalendar.list.get(i), taskCalendar1.list.get(i));
        }
    }
}