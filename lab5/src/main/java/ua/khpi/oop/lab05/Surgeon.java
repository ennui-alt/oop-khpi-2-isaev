package ua.khpi.oop.lab05;

public class Surgeon extends Doctor {
    private final int operationsCount;
    private final String operatingRoom;

    public Surgeon(String fullName, String hospitalName, int experienceYears,
                   String specialization, String licenseNumber,
                   int operationsCount, String operatingRoom) {
        super(fullName, hospitalName, experienceYears, specialization, licenseNumber);
        this.operationsCount = operationsCount;
        this.operatingRoom = operatingRoom;
    }

    public int getOperationsCount() {
        return operationsCount;
    }

    public String getOperatingRoom() {
        return operatingRoom;
    }

    @Override
    public String description() {
        return super.description()
                + ", operationsCount=" + operationsCount
                + ", operatingRoom=" + operatingRoom;
    }
}