package ua.khpi.oop.lab14.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void shouldRunMainWithoutErrors() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            assertDoesNotThrow(() -> Main.main(new String[0]));
        } finally {
            System.setOut(originalOut);
        }

        String text = output.toString(StandardCharsets.UTF_8);

        assertTrue(text.contains("Запуск роботи інтернет-магазину"));
        assertTrue(text.contains("потоки завершили роботу"));
        assertTrue(text.contains("Оброблені замовлення"));
        assertTrue(text.contains("Товари з коротким описом"));
        assertTrue(text.contains("Сформовані накладні"));
    }
}