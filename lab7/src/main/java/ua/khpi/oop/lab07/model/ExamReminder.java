package ua.khpi.oop.lab07.model;

import ua.khpi.oop.lab07.contracts.Notifiable;

public class ExamReminder implements Notifiable {

    private final String subject;
    private final String examDate;

    public ExamReminder(String subject, String examDate) {
        this.subject = subject;
        this.examDate = examDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getExamDate() {
        return examDate;
    }

    @Override
    public String notifyStudent(String studentName) {
        return "Student " + studentName + ", exam in " + subject
                + " will be on " + examDate + ".";
    }
}