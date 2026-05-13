package ua.khpi.oop.lab05;

public class MedicalWorker {
    private final String fullName;
    private final String hospitalName;
    private final int experienceYears;

    public MedicalWorker(String fullName, String hospitalName, int experienceYears) {
        this.fullName = fullName;
        this.hospitalName = hospitalName;
        this.experienceYears = experienceYears;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public String description() {
        return "MedicalWorker: " + fullName
                + ", hospitalName=" + hospitalName
                + ", experienceYears=" + experienceYears;
    }
}