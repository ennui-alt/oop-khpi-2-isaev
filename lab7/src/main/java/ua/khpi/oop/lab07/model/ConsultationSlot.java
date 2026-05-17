package ua.khpi.oop.lab07.model;

import ua.khpi.oop.lab07.contracts.Notifiable;
import ua.khpi.oop.lab07.contracts.Schedulable;

public class ConsultationSlot implements Notifiable, Schedulable {

    private final String teacherName;
    private final String subject;
    private String time;

    public ConsultationSlot(String teacherName, String subject) {
        this.teacherName = teacherName;
        this.subject = subject;
        this.time = "not scheduled";
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String schedule(String dateTime) {
        this.time = dateTime;
        return "Consultation with " + teacherName + " in "
                + subject + " is scheduled at " + time + ".";
    }

    @Override
    public String notifyStudent(String studentName) {
        if (time.equals("not scheduled")) {
            return "Student " + studentName + ", consultation in "
                    + subject + " is not scheduled yet.";
        }

        return "Student " + studentName + ", consultation in "
                + subject + " with " + teacherName
                + " will be at " + time + ".";
    }
}