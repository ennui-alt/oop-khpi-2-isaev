package ua.khpi.oop.lab08.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ConsultationSlot extends CampusService {
    private static final long serialVersionUID = 1L;

    private String teacherName;
    private String subject;

    public ConsultationSlot(String id,
                            String studentName,
                            LocalDate date,
                            LocalTime time,
                            String teacherName,
                            String subject) {
        super(id, studentName, date, time);

        if (teacherName == null || teacherName.isBlank()) {
            throw new IllegalArgumentException("Teacher name must not be empty");
        }

        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject must not be empty");
        }

        this.teacherName = teacherName;
        this.subject = subject;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String getType() {
        return "CONSULTATION";
    }

    @Override
    public String toTextLine() {
        return getType() + ";" + baseTextLine() + ";" + teacherName + ";" + subject;
    }

    @Override
    public String toString() {
        return "ConsultationSlot {id='" + getId()
                + "', studentName='" + getStudentName()
                + "', date=" + getDate()
                + ", time=" + getTime()
                + ", teacherName='" + teacherName
                + "', subject='" + subject
                + "'}";
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        if (!(other instanceof ConsultationSlot slot)) {
            return false;
        }

        return Objects.equals(teacherName, slot.teacherName)
                && Objects.equals(subject, slot.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacherName, subject);
    }
}