package ua.khpi.oop.lab08.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ExamReminder extends CampusService {
    private static final long serialVersionUID = 1L;

    private String courseName;
    private String auditorium;

    public ExamReminder(String id,
                        String studentName,
                        LocalDate date,
                        LocalTime time,
                        String courseName,
                        String auditorium) {
        super(id, studentName, date, time);

        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException("Course name must not be empty");
        }

        if (auditorium == null || auditorium.isBlank()) {
            throw new IllegalArgumentException("Auditorium must not be empty");
        }

        this.courseName = courseName;
        this.auditorium = auditorium;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getAuditorium() {
        return auditorium;
    }

    @Override
    public String getType() {
        return "EXAM";
    }

    @Override
    public String toTextLine() {
        return getType() + ";" + baseTextLine() + ";" + courseName + ";" + auditorium;
    }

    @Override
    public String toString() {
        return "ExamReminder {id='" + getId()
                + "', studentName='" + getStudentName()
                + "', date=" + getDate()
                + ", time=" + getTime()
                + ", courseName='" + courseName
                + "', auditorium='" + auditorium
                + "'}";
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        if (!(other instanceof ExamReminder reminder)) {
            return false;
        }

        return Objects.equals(courseName, reminder.courseName)
                && Objects.equals(auditorium, reminder.auditorium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courseName, auditorium);
    }
}