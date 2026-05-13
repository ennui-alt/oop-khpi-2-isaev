package ua.khpi.oop.lab05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurgeonTest {

    @Test
    void surgeonStoresInheritedAndOwnFields() {
        Surgeon surgeon = new Surgeon(
                "Baha Doto",
                "Regional Clinical Hospital",
                12,
                "Surgery",
                "SUR-2048",
                560,
                "Operating room 3"
        );

        assertEquals("Baha Doto", surgeon.getFullName());
        assertEquals("Regional Clinical Hospital", surgeon.getHospitalName());
        assertEquals(12, surgeon.getExperienceYears());
        assertEquals("Surgery", surgeon.getSpecialization());
        assertEquals("SUR-2048", surgeon.getLicenseNumber());
        assertEquals(560, surgeon.getOperationsCount());
        assertEquals("Operating room 3", surgeon.getOperatingRoom());
    }

    @Test
    void descriptionContainsSurgeonFields() {
        Surgeon surgeon = new Surgeon(
                "Baha Doto",
                "Regional Clinical Hospital",
                12,
                "Surgery",
                "SUR-2048",
                560,
                "Operating room 3"
        );

        assertEquals(
                "MedicalWorker: Baha Doto, hospitalName=Regional Clinical Hospital, experienceYears=12"
                        + ", specialization=Surgery, licenseNumber=SUR-2048"
                        + ", operationsCount=560, operatingRoom=Operating room 3",
                surgeon.description()
        );
    }
}