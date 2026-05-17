package ua.khpi.oop.lab07.demo;

import ua.khpi.oop.lab07.contracts.Notifiable;
import ua.khpi.oop.lab07.contracts.Schedulable;
import ua.khpi.oop.lab07.model.ConsultationSlot;
import ua.khpi.oop.lab07.model.ExamReminder;
import ua.khpi.oop.lab07.model.RoomBooking;

public class Main {

    public static void main(String[] args) {
        System.out.println("Lab 07");
        System.out.println("Student: Isaiev Zakhar Ihorovych");
        System.out.println("Variant: 3");
        System.out.println("Topic: Campus information services");
        System.out.println();

        ExamReminder examReminder = new ExamReminder(
                "Object-Oriented Programming",
                "25.05.2026"
        );

        RoomBooking roomBooking = new RoomBooking(
                "924",
                "Java laboratory work"
        );

        ConsultationSlot consultationSlot = new ConsultationSlot(
                "V. Volodymyrivna",
                "Java interfaces"
        );

        System.out.println("Notifications:");

        Notifiable[] notifications = {
                examReminder,
                consultationSlot
        };

        for (Notifiable item : notifications) {
            System.out.println(item.notifyStudent("Zakhar"));
        }

        System.out.println();

        System.out.println("Scheduling:");

        Schedulable[] schedules = {
                roomBooking,
                consultationSlot
        };

        for (Schedulable item : schedules) {
            System.out.println(item.schedule("20.05.2026 12:30"));
        }

        System.out.println();

        System.out.println("One object through two interfaces:");

        Notifiable notifyView = consultationSlot;
        Schedulable scheduleView = consultationSlot;

        System.out.println(scheduleView.schedule("21.05.2026 14:00"));
        System.out.println(notifyView.notifyStudent("Zakhar"));
    }
}