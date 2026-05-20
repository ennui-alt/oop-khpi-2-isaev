package ua.khpi.oop.lab06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssessmentTest {

    @Test
    void shouldCalculatePercentageUsingBaseClassMethod() {
        Assessment assessment = new Exam(
                "EXAM-1",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                75,
                90,
                true
        );

        assertEquals(75.0, assessment.getPercentage(), 0.001);
    }

    @Test
    void shouldCheckMinimumPercentageUsingBaseClassMethod() {
        Assessment assessment = new QuizAssessment(
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

        assertTrue(assessment.hasMinimumPercentage(50.0));
    }

    @Test
    void shouldCreateSummaryWithSharedState() {
        Assessment assessment = new CourseProject(
                "PROJECT-1",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                82,
                true,
                "https://github.com/ennui-alt/oop-khpi-2-isaev"
        );

        String summary = assessment.summary();

        assertTrue(summary.contains("PROJECT-1"));
        assertTrue(summary.contains("Object-Oriented Programming"));
        assertTrue(summary.contains("Zakhar Isaiev"));
        assertTrue(summary.contains("KN-924V"));
        assertTrue(summary.contains("82.0/100.0"));
        assertTrue(summary.contains("82.0%"));
    }

    @Test
    void shouldDispatchEvaluateResultThroughBaseType() {
        Assessment[] assessments = {
                new Exam(
                        "EXAM-1",
                        "Object-Oriented Programming",
                        "Zakhar Isaiev",
                        "KN-924V",
                        100,
                        78,
                        90,
                        true
                ),
                new QuizAssessment(
                        "QUIZ-1",
                        "Object-Oriented Programming",
                        "Zakhar Isaiev",
                        "KN-924V",
                        20,
                        13,
                        1,
                        3,
                        15
                ),
                new CourseProject(
                        "PROJECT-1",
                        "Object-Oriented Programming",
                        "Zakhar Isaiev",
                        "KN-924V",
                        100,
                        82,
                        true,
                        "https://github.com/ennui-alt/oop-khpi-2-isaev"
                )
        };

        assertEquals(
                "Exam passed: final exam score is acceptable",
                assessments[0].evaluateResult()
        );

        assertEquals(
                "Quiz passed: minimum quiz score is reached",
                assessments[1].evaluateResult()
        );

        assertEquals(
                "Course project passed: implementation and defense are accepted",
                assessments[2].evaluateResult()
        );
    }

    @Test
    void shouldRejectInvalidScore() {
        assertThrows(IllegalArgumentException.class, () -> new Exam(
                "EXAM-2",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                120,
                90,
                true
        ));
    }
}