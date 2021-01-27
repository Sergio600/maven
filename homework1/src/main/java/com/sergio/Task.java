package com.sergio;

import java.util.Date;
import java.util.Objects;

public class Task extends TaskCalendar {
    private String toDo;
    private String date;
    private Date date1;
    private IsCompleted status;

    public Task(String date, String toDo, IsCompleted status) {
        this.toDo = toDo;
        this.date = date;
        this.status = status;
    }

    public Task() {
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public String getToDo() {
        return this.toDo;
    }

    public IsCompleted getIsCompleted() {
        return this.status;
    }

    public void setIsCompleted(IsCompleted status) {
        this.status = status;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public void setStatus(IsCompleted status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(toDo, task.toDo) &&
                Objects.equals(date, task.date) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toDo, date, status);
    }
}