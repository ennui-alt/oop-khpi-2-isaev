package ua.khpi.oop.lab05;

public final class CardiacSurgeon extends Surgeon {
    private final int heartOperationsCount;
    private final boolean usesHeartLungMachine;

    public CardiacSurgeon(String fullName, String hospitalName, int experienceYears,
                          String specialization, String licenseNumber,
                          int operationsCount, String operatingRoom,
                          int heartOperationsCount, boolean usesHeartLungMachine) {
        super(fullName, hospitalName, experienceYears, specialization, licenseNumber,
                operationsCount, operatingRoom);
        this.heartOperationsCount = heartOperationsCount;
        this.usesHeartLungMachine = usesHeartLungMachine;
    }

    public int getHeartOperationsCount() {
        return heartOperationsCount;
    }

    public boolean isUsesHeartLungMachine() {
        return usesHeartLungMachine;
    }

    @Override
    public String description() {
        return super.description()
                + ", heartOperationsCount=" + heartOperationsCount
                + ", usesHeartLungMachine=" + usesHeartLungMachine;
    }
}