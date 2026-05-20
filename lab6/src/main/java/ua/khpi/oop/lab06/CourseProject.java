package ua.khpi.oop.lab06;

public class CourseProject extends Assessment {
    private final boolean defensePassed;
    private final String repositoryLink;

    public CourseProject(
            String assessmentId,
            String courseName,
            String studentName,
            String studentGroup,
            double maxScore,
            double earnedScore,
            boolean defensePassed,
            String repositoryLink
    ) {
        super(assessmentId, courseName, studentName, studentGroup, maxScore, earnedScore);

        if (repositoryLink == null || repositoryLink.isBlank()) {
            throw new IllegalArgumentException("Repository link must not be empty");
        }

        this.defensePassed = defensePassed;
        this.repositoryLink = repositoryLink;
    }

    @Override
    public String evaluateResult() {
        if (!defensePassed) {
            return "Course project failed: defense is not passed";
        }

        if (hasMinimumPercentage(70.0)) {
            return "Course project passed: implementation and defense are accepted";
        }

        return "Course project failed: at least 70% is required";
    }

    public boolean isDefensePassed() {
        return defensePassed;
    }

    public String getRepositoryLink() {
        return repositoryLink;
    }
}