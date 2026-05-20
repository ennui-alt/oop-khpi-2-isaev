package ua.khpi.oop.lab06;

public class Exam extends Assessment {
    private final int durationMinutes;
    private final boolean proctored;

    public Exam(
            String assessmentId,
            String courseName,
            String studentName,
            String studentGroup,
            double maxScore,
            double earnedScore,
            int durationMinutes,
            boolean proctored
    ) {
        super(assessmentId, courseName, studentName, studentGroup, maxScore, earnedScore);

        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }

        this.durationMinutes = durationMinutes;
        this.proctored = proctored;
    }

    @Override
    public String evaluateResult() {
        if (!proctored) {
            return "Exam result not accepted: proctoring is required";
        }

        if (hasMinimumPercentage(60.0)) {
            return "Exam passed: final exam score is acceptable";
        }

        return "Exam failed: at least 60% is required";
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public boolean isProctored() {
        return proctored;
    }
}