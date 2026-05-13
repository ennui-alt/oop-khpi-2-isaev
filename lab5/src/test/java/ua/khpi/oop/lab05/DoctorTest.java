package ua.khpi.oop.lab05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorTest {

    @Test
    void doctorStoresInheritedAndOwnFields() {
        Doctor doctor = new Doctor(
                "Kolya Kamazyaka",
                "Kharkiv City Hospital",
                8,
                "Therapy",
                "DOC-1024"
        );

        assertEquals("Kolya Kamazyaka", doctor.getFullName());
        assertEquals("Kharkiv City Hospital", doctor.getHospitalName());
        assertEquals(8, doctor.getExperienceYears());
        assertEquals("Therapy", doctor.getSpecialization());
        assertEquals("DOC-1024", doctor.getLicenseNumber());
    }

    @Test
    void descriptionContainsDoctorFields() {
        Doctor doctor = new Doctor(
                "Kolya Kamazyaka",
                "Kharkiv City Hospital",
                8,
                "Therapy",
                "DOC-1024"
        );

        assertEquals(
                "MedicalWorker: Kolya Kamazyaka, hospitalName=Kharkiv City Hospital, experienceYears=8"
                        + ", specialization=Therapy, licenseNumber=DOC-1024",
                doctor.description()
        );
    }
}