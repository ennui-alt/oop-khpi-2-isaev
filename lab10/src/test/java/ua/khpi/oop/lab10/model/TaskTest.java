package ua.khpi.oop.lab10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Developer developer;
    private Task task;

    @BeforeEach
    void setUp() {
        developer = new Developer(1, "Zakhar Isaiev", "Backend developer");
        task = new Task(
                1,
                "Authorization",
                "Create login and registration",
                developer
        );
    }

    @Test
    void constructorShouldCreateTaskWithCorrectData() {
        assertEquals(1, task.getId());
        assertEquals("Authorization", task.getTitle());
        assertSame(developer, task.getDeveloper());
        assertEquals(TaskStatus.NEW, task.getStatus());
    }

    @Test
    void newTaskShouldHaveOneHistoryRecord() {
        assertEquals(1, task.getHistorySize());

        StatusRecord record = task.getHistoryRecord(0);

        assertEquals(TaskStatus.NEW, record.getStatus());
        assertEquals("Task was created", record.getComment());
        assertSame(developer, record.getDeveloper());
        assertNotNull(record.getDate());
    }

    @Test
    void changeStatusShouldChangeCurrentStatus() {
        task.changeStatus(
                TaskStatus.IN_PROGRESS,
                "Developer started this task",
                developer
        );

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void changeStatusShouldAddNewHistoryRecord() {
        task.changeStatus(
                TaskStatus.CHECKING,
                "Task is ready for checking",
                developer
        );

        assertEquals(2, task.getHistorySize());

        StatusRecord record = task.getHistoryRecord(1);

        assertEquals(TaskStatus.CHECKING, record.getStatus());
        assertEquals("Task is ready for checking", record.getComment());
        assertSame(developer, record.getDeveloper());
        assertNotNull(record.getDate());
    }

    @Test
    void getHistoryRecordWithWrongIndexShouldThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> task.getHistoryRecord(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> task.getHistoryRecord(1));
    }

    @Test
    void historyArrayShouldGrowWhenThereAreManyStatusChanges() {
        task.changeStatus(TaskStatus.IN_PROGRESS, "Started", developer);
        task.changeStatus(TaskStatus.CHECKING, "Checking", developer);
        task.changeStatus(TaskStatus.DONE, "Done", developer);
        task.changeStatus(TaskStatus.IN_PROGRESS, "Returned to work", developer);
        task.changeStatus(TaskStatus.CHECKING, "Checking again", developer);
        task.changeStatus(TaskStatus.DONE, "Finished again", developer);

        assertEquals(7, task.getHistorySize());

        StatusRecord lastRecord = task.getHistoryRecord(6);

        assertEquals(TaskStatus.DONE, lastRecord.getStatus());
        assertEquals("Finished again", lastRecord.getComment());
    }

    @Test
    void toStringShouldContainMainTaskInfo() {
        String text = task.toString();

        assertTrue(text.contains("Task #1"));
        assertTrue(text.contains("Authorization"));
        assertTrue(text.contains("Zakhar Isaiev"));
        assertTrue(text.contains("NEW"));
    }
}