package ua.khpi.oop.lab05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PediatricianTest {

    @Test
    void pediatricianStoresInheritedAndOwnFields() {
        Pediatrician pediatrician = new Pediatrician(
                "Sanya Vasyan",
                "Children Hospital",
                10,
                "Pediatrics",
                "PED-3001",
                18,
                true
        );

        assertEquals("Sanya Vasyan", pediatrician.getFullName());
        assertEquals("Children Hospital", pediatrician.getHospitalName());
        assertEquals(10, pediatrician.getExperienceYears());
        assertEquals("Pediatrics", pediatrician.getSpecialization());
        assertEquals("PED-3001", pediatrician.getLicenseNumber());
        assertEquals(18, pediatrician.getMaxPatientAge());
        assertTrue(pediatrician.isVaccinationCertified());
    }

    @Test
    void descriptionContainsPediatricianFields() {
        Pediatrician pediatrician = new Pediatrician(
                "Sanya Vasyan",
                "Children Hospital",
                10,
                "Pediatrics",
                "PED-3001",
                18,
                true
        );

        assertEquals(
                "MedicalWorker: Sanya Vasyan, hospitalName=Children Hospital, experienceYears=10"
                        + ", specialization=Pediatrics, licenseNumber=PED-3001"
                        + ", maxPatientAge=18, vaccinationCertified=true",
                pediatrician.description()
        );
    }
}