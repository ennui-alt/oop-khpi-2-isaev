package ua.khpi.oop.lab05;

public class Doctor extends MedicalWorker {
    private final String specialization;
    private final String licenseNumber;

    public Doctor(String fullName, String hospitalName, int experienceYears,
                  String specialization, String licenseNumber) {
        super(fullName, hospitalName, experienceYears);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String description() {
        return super.description()
                + ", specialization=" + specialization
                + ", licenseNumber=" + licenseNumber;
    }
}