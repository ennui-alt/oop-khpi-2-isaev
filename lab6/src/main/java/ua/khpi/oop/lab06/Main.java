package ua.khpi.oop.lab06;

public class Main {
    public static void main(String[] args) {
        final String studentName = "Zakhar Isaiev";
        final String studentGroup = "KN-924V";
        final String courseName = "Object-Oriented Programming";

        Assessment[] assessments = {
                new Exam(
                        "EX-101",
                        courseName,
                        studentName,
                        studentGroup,
                        100,
                        78,
                        90,
                        true
                ),

                new QuizAssessment(
                        "QZ-205",
                        courseName,
                        studentName,
                        studentGroup,
                        20,
                        13,
                        1,
                        3,
                        15
                ),

                new CourseProject(
                        "CP-310",
                        courseName,
                        studentName,
                        studentGroup,
                        100,
                        82,
                        true,
                        "https://github.com/ennui-alt/oop-khpi-2-isaev  "
                )
        };

        for (Assessment assessment : assessments) {
            System.out.println(assessment.summary());
            System.out.println(assessment.evaluateResult());
            System.out.println();
        }
    }
}