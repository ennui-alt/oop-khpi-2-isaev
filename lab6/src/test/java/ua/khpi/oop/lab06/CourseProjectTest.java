package ua.khpi.oop.lab06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseProjectTest {

    @Test
    void shouldPassCourseProjectWhenDefenseIsPassedAndScoreIsEnough() {
        CourseProject project = new CourseProject(
                "PROJECT-1",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                82,
                true,
                "https://github.com/ennui-alt/oop-khpi-2-isaev"
        );

        assertEquals(
                "Course project passed: implementation and defense are accepted",
                project.evaluateResult()
        );
    }

    @Test
    void shouldFailCourseProjectWhenDefenseIsNotPassed() {
        CourseProject project = new CourseProject(
                "PROJECT-2",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                90,
                false,
                "https://github.com/ennui-alt/oop-khpi-2-isaev"
        );

        assertEquals(
                "Course project failed: defense is not passed",
                project.evaluateResult()
        );
    }

    @Test
    void shouldFailCourseProjectWhenScoreIsLessThanSeventyPercent() {
        CourseProject project = new CourseProject(
                "PROJECT-3",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                65,
                true,
                "https://github.com/ennui-alt/oop-khpi-2-isaev"
        );

        assertEquals(
                "Course project failed: at least 70% is required",
                project.evaluateResult()
        );
    }

    @Test
    void shouldReturnCourseProjectSpecificFields() {
        CourseProject project = new CourseProject(
                "PROJECT-4",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                85,
                true,
                "https://github.com/ennui-alt/oop-khpi-2-isaev"
        );

        assertTrue(project.isDefensePassed());
        assertEquals("https://github.com/ennui-alt/oop-khpi-2-isaev", project.getRepositoryLink());
    }

    @Test
    void shouldRejectEmptyRepositoryLink() {
        assertThrows(IllegalArgumentException.class, () -> new CourseProject(
                "PROJECT-5",
                "Object-Oriented Programming",
                "Zakhar Isaiev",
                "KN-924V",
                100,
                85,
                true,
                ""
        ));
    }
}