package ua.khpi.oop.lab15.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void mainShouldRunAllScenariosAndPrintResult() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

        try {
            assertDoesNotThrow(() -> Main.main(new String[0]));
        } finally {
            System.setOut(originalOut);
        }

        String console = output.toString(StandardCharsets.UTF_8);

        assertTrue(console.contains("Лабораторна робота №15"), "Має бути назва лабораторної роботи");
        assertTrue(console.contains("Варіант 3: бронювання номерів"), "Має бути номер варіанта");
        assertTrue(console.contains("Тема: синхронізація потоків у Java"), "Має бути тема роботи");
        assertTrue(console.contains("ВАРІАНТ БЕЗ SYNCHRONIZED"), "Має бути небезпечний варіант");
        assertTrue(console.contains("СИНХРОНІЗОВАНИЙ ВАРІАНТ"), "Має бути синхронізований варіант");
        assertTrue(console.contains("ПОРІВНЯННЯ ЧАСУ"), "Має бути порівняння часу");
        assertTrue(console.contains("Кількість потоків: 4"), "Має бути кількість потоків");
        assertTrue(console.contains("Ітерацій на потік: 100000"), "Має бути кількість ітерацій");
        assertTrue(console.contains("Послідовний варіант"), "Має бути послідовний варіант");
        assertTrue(console.contains("Паралельний варіант"), "Має бути паралельний варіант");
    }
}