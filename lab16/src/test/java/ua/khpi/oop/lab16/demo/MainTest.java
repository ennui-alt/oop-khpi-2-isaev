package ua.khpi.oop.lab16.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void mainShouldRunAndPrintResults() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

        try {
            assertDoesNotThrow(() -> Main.main(new String[0]));
        } finally {
            System.setOut(originalOut);
        }

        String console = output.toString(StandardCharsets.UTF_8);

        assertTrue(console.contains("Лабораторна робота №16"), "Має бути номер лабораторної роботи");
        assertTrue(console.contains("Варіант 3"), "Має бути номер варіанта");
        assertTrue(console.contains("середнє значення"), "Має бути опис операції");
        assertTrue(console.contains("Результат Java-реалізації: 30.0"), "Має бути результат Java-реалізації");
        assertTrue(console.contains("Результат native-реалізації: 30.0"), "Має бути результат native-реалізації");
        assertTrue(console.contains("Результати збігаються: true"), "Має бути підтвердження збігу результатів");
    }
}