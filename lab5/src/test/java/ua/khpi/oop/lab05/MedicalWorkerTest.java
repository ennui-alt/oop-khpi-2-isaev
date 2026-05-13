package ua.khpi.oop.lab05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicalWorkerTest {

    @Test
    void constructorInitializesOwnFields() {
        MedicalWorker worker = new MedicalWorker(
                "Danya Varenik",
                "Kharkiv City Hospital",
                3
        );

        assertEquals("Danya Varenik", worker.getFullName());
        assertEquals("Kharkiv City Hospital", worker.getHospitalName());
        assertEquals(3, worker.getExperienceYears());
    }

    @Test
    void descriptionContainsMedicalWorkerFields() {
        MedicalWorker worker = new MedicalWorker(
                "Danya Varenik",
                "Kharkiv City Hospital",
                3
        );

        assertEquals(
                "MedicalWorker: Danya Varenik, hospitalName=Kharkiv City Hospital, experienceYears=3",
                worker.description()
        );
    }
}