package ua.khpi.oop.lab12.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private final PrintStream originalOut = System.out;

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldRunMainWithoutExceptions() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }

    @Test
    void shouldPrintShortDemoSections() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

        Main.main(new String[0]);

        String output = outputStream.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("=== Lab 12: Stream API / Online Store ==="));

        assertTrue(output.contains("1. All orders"));
        assertTrue(output.contains("2. Completed orders: filter()"));
        assertTrue(output.contains("3. Product names: flatMap() + map() + distinct() + sorted()"));
        assertTrue(output.contains("4. Orders sorted by total price: sorted()"));
        assertTrue(output.contains("5. Income from completed orders: filter() + sum()"));
        assertTrue(output.contains("6. Order count by status: groupingBy() + counting()"));
        assertTrue(output.contains("7. Customers with completed orders"));
        assertTrue(output.contains("8. Order price statistics: summarizingDouble()"));
        assertTrue(output.contains("9. Short text report: joining()"));
        assertTrue(output.contains("10. Imperative loop vs Stream API"));
    }

    @Test
    void shouldPrintImportantDemoData() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

        Main.main(new String[0]);

        String output = outputStream.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("Total orders: 6"));
        assertTrue(output.contains("Completed orders: #101, #104"));
        assertTrue(output.contains("Most expensive order: Order #106"));

        assertTrue(output.contains("Zakhar Isaiev"));
        assertTrue(output.contains("Oleg Vinnik"));
        assertTrue(output.contains("Valentina Budeiko"));

        assertTrue(output.contains("Lenovo IdeaPad Laptop"));
        assertTrue(output.contains("Xiaomi Power Bank"));
    }

    @Test
    void shouldPrintStreamApiOperationNames() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

        Main.main(new String[0]);

        String output = outputStream.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("filter()"));
        assertTrue(output.contains("flatMap()"));
        assertTrue(output.contains("map()"));
        assertTrue(output.contains("distinct()"));
        assertTrue(output.contains("sorted()"));
        assertTrue(output.contains("groupingBy()"));
        assertTrue(output.contains("counting()"));
        assertTrue(output.contains("summarizingDouble()"));
        assertTrue(output.contains("joining()"));
    }
}