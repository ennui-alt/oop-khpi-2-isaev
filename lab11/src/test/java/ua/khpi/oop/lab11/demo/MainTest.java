package ua.khpi.oop.lab11.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void mainShouldRunWithoutExceptions() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }

    @Test
    void mainShouldPrintMainDemoSections() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(outputStream));

            Main.main(new String[0]);

            String output = outputStream.toString();

            assertTrue(output.contains("All team tasks"));
            assertTrue(output.contains("Team developers"));
            assertTrue(output.contains("Search task by id"));
            assertTrue(output.contains("Change task status"));
            assertTrue(output.contains("Task queue"));
            assertTrue(output.contains("Sorting by priority"));
            assertTrue(output.contains("Removing task"));

            assertTrue(output.contains("T-1"));
            assertTrue(output.contains("T-2"));
            assertTrue(output.contains("Create task model"));
            assertTrue(output.contains("Create main page"));
            assertTrue(output.contains("IN_PROGRESS"));
        } finally {
            System.setOut(originalOut);
        }
    }
}