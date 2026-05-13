package ua.khpi.oop.lab05;

public class Pediatrician extends Doctor {
    private final int maxPatientAge;
    private final boolean vaccinationCertified;

    public Pediatrician(String fullName, String hospitalName, int experienceYears,
                        String specialization, String licenseNumber,
                        int maxPatientAge, boolean vaccinationCertified) {
        super(fullName, hospitalName, experienceYears, specialization, licenseNumber);
        this.maxPatientAge = maxPatientAge;
        this.vaccinationCertified = vaccinationCertified;
    }

    public int getMaxPatientAge() {
        return maxPatientAge;
    }

    public boolean isVaccinationCertified() {
        return vaccinationCertified;
    }

    @Override
    public String description() {
        return super.description()
                + ", maxPatientAge=" + maxPatientAge
                + ", vaccinationCertified=" + vaccinationCertified;
    }
}