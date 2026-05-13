package ua.khpi.oop.lab05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardiacSurgeonTest {

    @Test
    void cardiacSurgeonStoresInheritedAndOwnFields() {
        CardiacSurgeon cardiacSurgeon = new CardiacSurgeon(
                "Chotyk Sergeivich",
                "Cardiology Center",
                15,
                "Cardiac surgery",
                "CARD-4096",
                820,
                "Operating room 1",
                370,
                true
        );

        assertEquals("Chotyk Sergeivich", cardiacSurgeon.getFullName());
        assertEquals("Cardiology Center", cardiacSurgeon.getHospitalName());
        assertEquals(15, cardiacSurgeon.getExperienceYears());
        assertEquals("Cardiac surgery", cardiacSurgeon.getSpecialization());
        assertEquals("CARD-4096", cardiacSurgeon.getLicenseNumber());
        assertEquals(820, cardiacSurgeon.getOperationsCount());
        assertEquals("Operating room 1", cardiacSurgeon.getOperatingRoom());
        assertEquals(370, cardiacSurgeon.getHeartOperationsCount());
        assertTrue(cardiacSurgeon.isUsesHeartLungMachine());
    }

    @Test
    void descriptionContainsCardiacSurgeonFields() {
        CardiacSurgeon cardiacSurgeon = new CardiacSurgeon(
                "Chotyk Sergeivich",
                "Cardiology Center",
                15,
                "Cardiac surgery",
                "CARD-4096",
                820,
                "Operating room 1",
                370,
                true
        );

        assertEquals(
                "MedicalWorker: Chotyk Sergeivich, hospitalName=Cardiology Center, experienceYears=15"
                        + ", specialization=Cardiac surgery, licenseNumber=CARD-4096"
                        + ", operationsCount=820, operatingRoom=Operating room 1"
                        + ", heartOperationsCount=370, usesHeartLungMachine=true",
                cardiacSurgeon.description()
        );
    }
}