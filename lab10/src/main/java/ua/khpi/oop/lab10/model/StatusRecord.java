package ua.khpi.oop.lab10.model;

import java.time.LocalDateTime;

public class StatusRecord {

    private TaskStatus status;
    private String comment;
    private LocalDateTime date;
    private Developer developer;

    public StatusRecord(TaskStatus status, String comment, Developer developer) {
        this.status = status;
        this.comment = comment;
        this.developer = developer;
        this.date = LocalDateTime.now();
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Developer getDeveloper() {
        return developer;
    }

    @Override
    public String toString() {
        return "status = " + status
                + ", comment = " + comment
                + ", changed by = " + developer.getName()
                + ", date = " + date;
    }
}