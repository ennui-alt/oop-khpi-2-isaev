package ua.khpi.oop.lab10.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusRecordTest {

    @Test
    void constructorShouldCreateStatusRecordWithCorrectData() {
        Developer developer = new Developer(1, "Zakhar Isaiev", "Backend developer");

        StatusRecord record = new StatusRecord(
                TaskStatus.IN_PROGRESS,
                "Work started",
                developer
        );

        assertEquals(TaskStatus.IN_PROGRESS, record.getStatus());
        assertEquals("Work started", record.getComment());
        assertSame(developer, record.getDeveloper());
        assertNotNull(record.getDate());
    }

    @Test
    void toStringShouldContainMainStatusRecordInfo() {
        Developer developer = new Developer(1, "Zakhar Isaiev", "Backend developer");

        StatusRecord record = new StatusRecord(
                TaskStatus.DONE,
                "Task completed",
                developer
        );

        String text = record.toString();

        assertTrue(text.contains("DONE"));
        assertTrue(text.contains("Task completed"));
        assertTrue(text.contains("Zakhar Isaiev"));
    }
}