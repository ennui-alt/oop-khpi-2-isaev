package ua.khpi.oop.lab13.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void mainShouldRunProgramAndPrintReport() {
        PrintStream originalOut = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(outputStream, true, StandardCharsets.UTF_8);

        System.setOut(testOut);

        try {
            assertDoesNotThrow(() -> Main.main(new String[0]));
        } finally {
            System.setOut(originalOut);
        }

        String output = outputStream.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("Звіт з обробки розкладу занять"));
        assertTrue(output.contains("Коректних записів"));
        assertTrue(output.contains("Помилкових записів"));
        assertTrue(output.contains("Розклад"));
    }

    @Test
    void mainShouldShowThatThreadsAreUsed() {
        PrintStream originalOut = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(outputStream, true, StandardCharsets.UTF_8);

        System.setOut(testOut);

        try {
            Main.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        String output = outputStream.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("Потік"));
        assertTrue(output.contains("обробляє рядок"));
    }
}