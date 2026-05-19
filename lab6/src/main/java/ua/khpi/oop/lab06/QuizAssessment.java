package ua.khpi.oop.lab06;

public class QuizAssessment extends Assessment {
    private final int attemptNumber;
    private final int maxAttempts;
    private final int timeLimitMinutes;

    public QuizAssessment(
            String assessmentId,
            String courseName,
            String studentName,
            String studentGroup,
            double maxScore,
            double earnedScore,
            int attemptNumber,
            int maxAttempts,
            int timeLimitMinutes
    ) {
        super(assessmentId, courseName, studentName, studentGroup, maxScore, earnedScore);

        if (attemptNumber <= 0) {
            throw new IllegalArgumentException("Attempt number must be positive");
        }

        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("Max attempts must be positive");
        }

        if (timeLimitMinutes <= 0) {
            throw new IllegalArgumentException("Time limit must be positive");
        }

        this.attemptNumber = attemptNumber;
        this.maxAttempts = maxAttempts;
        this.timeLimitMinutes = timeLimitMinutes;
    }

    @Override
    public String evaluateResult() {
        if (attemptNumber > maxAttempts) {
            return "Quiz result not accepted: attempt limit exceeded";
        }

        if (hasMinimumPercentage(50.0)) {
            return "Quiz passed: minimum quiz score is reached";
        }

        return "Quiz failed: at least 50% is required";
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getTimeLimitMinutes() {
        return timeLimitMinutes;
    }
}