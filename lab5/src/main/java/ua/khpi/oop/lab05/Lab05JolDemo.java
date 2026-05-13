package ua.khpi.oop.lab05;

import org.openjdk.jol.info.ClassLayout;

public class Lab05JolDemo {
    public static void main(String[] args) {
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

        System.out.println(ClassLayout.parseInstance(cardiacSurgeon).toPrintable());
    }
}