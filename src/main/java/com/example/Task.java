package com.example;

import java.sql.Time;
import java.sql.Timestamp;

public class Task {
    public Task(){
        this.id = 0;
        this.name = "";
        this.taskTypeId = 0;
        this.date = new Timestamp(System.currentTimeMillis());
        this.time = 0;
        this.assignee = "";
        this.reviewer = "";
    }

    public Task(int id, String name, int taskTypeId, Timestamp date, float time, String assignee, String reviewer) {
        this.id = id;
        this.name = name;
        this.taskTypeId = taskTypeId;
        this.date = date;
        this.time = time;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }

    public Task(String name, int taskTypeId, Timestamp date, float time, String assignee, String reviewer) {
        this.id = 0;
        this.name = name;
        this.taskTypeId = taskTypeId;
        this.date = date;
        this.time = time;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }

    private int id;
    private String name;
    private int taskTypeId;
    private Timestamp date;
    private float time; // e.g., hours worked
    private String assignee;
    private String reviewer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(int taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
