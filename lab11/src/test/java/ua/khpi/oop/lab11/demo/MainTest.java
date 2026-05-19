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
    void mainShouldPrintMainSections() {
        String output = runMainAndGetOutput();

        assertTrue(output.contains("=== All team tasks ==="));
        assertTrue(output.contains("=== Team developers ==="));
        assertTrue(output.contains("=== Search task by id ==="));
        assertTrue(output.contains("=== Change task status ==="));
        assertTrue(output.contains("=== Task queue ==="));
        assertTrue(output.contains("=== Sorting by priority ==="));
        assertTrue(output.contains("=== Removing task ==="));
    }

    @Test
    void mainShouldPrintCreatedTasksAndDevelopers() {
        String output = runMainAndGetOutput();

        assertTrue(output.contains("T-1"));
        assertTrue(output.contains("T-2"));
        assertTrue(output.contains("T-3"));

        assertTrue(output.contains("Zakhar Isaiev"));
        assertTrue(output.contains("Oleg Vinnik"));
        assertTrue(output.contains("Valentin Strikalo"));
    }

    @Test
    void mainShouldShowStatusQueueSortingAndRemoving() {
        String output = runMainAndGetOutput();

        assertTrue(output.contains("status=IN_PROGRESS"));
        assertTrue(output.contains("Next task:"));
        assertTrue(output.contains("Tasks left in queue: 2"));

        int removingIndex = output.lastIndexOf("=== Removing task ===");
        assertTrue(removingIndex >= 0);

        String outputAfterRemoving = output.substring(removingIndex);

        assertTrue(outputAfterRemoving.contains("T-1"));
        assertTrue(outputAfterRemoving.contains("T-2"));
        assertFalse(outputAfterRemoving.contains("T-3"));
    }

    private String runMainAndGetOutput() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(outputStream));
            Main.main(new String[0]);
            return outputStream.toString();
        } finally {
            System.setOut(originalOut);
        }
    }
}