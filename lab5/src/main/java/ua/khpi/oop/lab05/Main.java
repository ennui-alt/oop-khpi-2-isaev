package ua.khpi.oop.lab05;

public class Main {
    public static void main(String[] args) {
        MedicalWorker worker = new MedicalWorker(
                "Danya Varenik",
                "Kharkiv City Hospital",
                3
        );

        Doctor doctor = new Doctor(
                "Kolya Kamazyaka",
                "Kharkiv City Hospital",
                8,
                "Therapy",
                "DOC-1024"
        );

        Surgeon surgeon = new Surgeon(
                "Baha Doto",
                "Regional Clinical Hospital",
                12,
                "Surgery",
                "SUR-2048",
                560,
                "Operating room 3"
        );

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

        Pediatrician pediatrician = new Pediatrician(
                "Sanya Vasyan",
                "Children Hospital",
                10,
                "Pediatrics",
                "PED-3001",
                18,
                true
        );

        System.out.println(worker.description());
        System.out.println(doctor.description());
        System.out.println(surgeon.description());
        System.out.println(cardiacSurgeon.description());
        System.out.println(pediatrician.description());
    }
}