package ua.khpi.oop.lab08.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public abstract class CampusService implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String studentName;
    private LocalDate date;
    private LocalTime time;

    public CampusService(String id, String studentName, LocalDate date, LocalTime time) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id must not be empty");
        }

        if (studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException("Student name must not be empty");
        }

        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }

        if (time == null) {
            throw new IllegalArgumentException("Time must not be null");
        }

        this.id = id;
        this.studentName = studentName;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public abstract String getType();

    public abstract String toTextLine();

    protected String baseTextLine() {
        return id + ";" + studentName + ";" + date + ";" + time;
    }

    @Override
    public String toString() {
        return getType()
                + " {id='" + id + '\''
                + ", studentName='" + studentName + '\''
                + ", date=" + date
                + ", time=" + time
                + '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof CampusService service)) {
            return false;
        }

        return Objects.equals(id, service.id)
                && Objects.equals(studentName, service.studentName)
                && Objects.equals(date, service.date)
                && Objects.equals(time, service.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, date, time);
    }
}