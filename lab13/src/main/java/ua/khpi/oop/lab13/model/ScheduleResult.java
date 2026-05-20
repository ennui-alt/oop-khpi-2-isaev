package ua.khpi.oop.lab13.model;

import java.util.ArrayList;
import java.util.List;

public class ScheduleResult {
    private final List<Lesson> lessons = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void addError(String error) {
        errors.add(error);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<String> getErrors() {
        return errors;
    }
}