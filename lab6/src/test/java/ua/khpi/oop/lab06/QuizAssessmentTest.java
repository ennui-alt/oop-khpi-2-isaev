package ua.khpi.oop.lab06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuizAssessmentTest {

    @Test
    void shouldPassQuizWhenScoreIsEnoughAndAttemptIsValid() {
        QuizAssessment quiz = new QuizAssessment(
                "QUIZ-1",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                20,
                13,
                1,
                3,
                15
        );

        assertEquals(
                "Quiz passed: minimum quiz score is reached",
                quiz.evaluateResult()
        );
    }

    @Test
    void shouldFailQuizWhenScoreIsLessThanFiftyPercent() {
        QuizAssessment quiz = new QuizAssessment(
                "QUIZ-2",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                20,
                8,
                1,
                3,
                15
        );

        assertEquals(
                "Quiz failed: at least 50% is required",
                quiz.evaluateResult()
        );
    }

    @Test
    void shouldRejectQuizWhenAttemptLimitExceeded() {
        QuizAssessment quiz = new QuizAssessment(
                "QUIZ-3",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                20,
                18,
                4,
                3,
                15
        );

        assertEquals(
                "Quiz result not accepted: attempt limit exceeded",
                quiz.evaluateResult()
        );
    }

    @Test
    void shouldReturnQuizSpecificFields() {
        QuizAssessment quiz = new QuizAssessment(
                "QUIZ-4",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                20,
                15,
                2,
                3,
                10
        );

        assertEquals(2, quiz.getAttemptNumber());
        assertEquals(3, quiz.getMaxAttempts());
        assertEquals(10, quiz.getTimeLimitMinutes());
    }

    @Test
    void shouldRejectInvalidAttemptNumber() {
        assertThrows(IllegalArgumentException.class, () -> new QuizAssessment(
                "QUIZ-5",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                20,
                15,
                0,
                3,
                15
        ));
    }

    @Test
    void shouldRejectInvalidMaxAttempts() {
        assertThrows(IllegalArgumentException.class, () -> new QuizAssessment(
                "QUIZ-6",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                20,
                15,
                1,
                0,
                15
        ));
    }

    @Test
    void shouldRejectInvalidTimeLimit() {
        assertThrows(IllegalArgumentException.class, () -> new QuizAssessment(
                "QUIZ-7",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                20,
                15,
                1,
                3,
                0
        ));
    }
}