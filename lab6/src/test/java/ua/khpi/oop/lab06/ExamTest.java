package ua.khpi.oop.lab06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExamTest {

    @Test
    void shouldPassExamWhenScoreIsEnoughAndProctoringIsEnabled() {
        Exam exam = new Exam(
                "EXAM-1",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                78,
                90,
                true
        );

        assertEquals(
                "Exam passed: final exam score is acceptable",
                exam.evaluateResult()
        );
    }

    @Test
    void shouldFailExamWhenScoreIsLessThanSixtyPercent() {
        Exam exam = new Exam(
                "EXAM-2",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                55,
                90,
                true
        );

        assertEquals(
                "Exam failed: at least 60% is required",
                exam.evaluateResult()
        );
    }

    @Test
    void shouldRejectExamWithoutProctoring() {
        Exam exam = new Exam(
                "EXAM-3",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                90,
                90,
                false
        );

        assertEquals(
                "Exam result not accepted: proctoring is required",
                exam.evaluateResult()
        );
    }

    @Test
    void shouldReturnExamSpecificFields() {
        Exam exam = new Exam(
                "EXAM-4",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                70,
                120,
                true
        );

        assertEquals(120, exam.getDurationMinutes());
        assertTrue(exam.isProctored());
    }

    @Test
    void shouldCreateNotProctoredExamObject() {
        Exam exam = new Exam(
                "EXAM-5",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                70,
                90,
                false
        );

        assertFalse(exam.isProctored());
    }

    @Test
    void shouldRejectInvalidDuration() {
        assertThrows(IllegalArgumentException.class, () -> new Exam(
                "EXAM-6",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                70,
                0,
                true
        ));
    }
}