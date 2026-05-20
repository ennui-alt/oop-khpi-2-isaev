package ua.khpi.oop.lab13.model;

import java.time.LocalTime;

public class Lesson {
    private final WeekDay day;
    private final int number;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String classroom;
    private final String teacher;
    private final String subject;

    public Lesson(
            WeekDay day,
            int number,
            LocalTime startTime,
            LocalTime endTime,
            String classroom,
            String teacher,
            String subject
    ) {
        this.day = day;
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
        this.teacher = teacher;
        this.subject = subject;
    }

    public WeekDay getDay() {
        return day;
    }

    public int getNumber() {
        return number;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getSubject() {
        return subject;
    }

    public String getTime() {
        return startTime + "-" + endTime;
    }

    @Override
    public String toString() {
        return "Пара " + number
                + " | " + getTime()
                + " | ауд. " + classroom
                + " | " + teacher
                + " | " + subject;
    }
}