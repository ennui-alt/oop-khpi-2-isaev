package ua.khpi.oop.lab06;

import java.util.Locale;

public abstract class Assessment {
    protected final String assessmentId;
    protected final String courseName;
    protected final String studentName;
    protected final String studentGroup;
    protected final double maxScore;
    protected final double earnedScore;

    protected Assessment(
            String assessmentId,
            String courseName,
            String studentName,
            String studentGroup,
            double maxScore,
            double earnedScore
    ) {
        if (assessmentId == null || assessmentId.isBlank()) {
            throw new IllegalArgumentException("Assessment id must not be empty");
        }

        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException("Course name must not be empty");
        }

        if (studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException("Student name must not be empty");
        }

        if (studentGroup == null || studentGroup.isBlank()) {
            throw new IllegalArgumentException("Student group must not be empty");
        }

        if (maxScore <= 0) {
            throw new IllegalArgumentException("Max score must be positive");
        }

        if (earnedScore < 0 || earnedScore > maxScore) {
            throw new IllegalArgumentException("Earned score must be between 0 and max score");
        }

        this.assessmentId = assessmentId;
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentGroup = studentGroup;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
    }

    public double getPercentage() {
        return earnedScore / maxScore * 100.0;
    }

    public boolean hasMinimumPercentage(double minimumPercentage) {
        return getPercentage() >= minimumPercentage;
    }

    public String summary() {
        return String.format(
                Locale.US,
                "%s | %s | %s | %s | %.1f/%.1f points | %.1f%%",
                assessmentId,
                courseName,
                studentName,
                studentGroup,
                earnedScore,
                maxScore,
                getPercentage()
        );
    }

    public abstract String evaluateResult();
}