package ua.khpi.oop.lab11.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab11.model.Developer;
import ua.khpi.oop.lab11.model.Sprint;
import ua.khpi.oop.lab11.model.Task;
import ua.khpi.oop.lab11.model.TaskStatus;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeamTaskPlannerTest {

    private TeamTaskPlanner planner;
    private Developer developer1;
    private Developer developer2;
    private Developer developer3;
    private Sprint sprint;
    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    void setUp() {
        planner = new TeamTaskPlanner();

        developer1 = new Developer("Zakhar Isaiev", "zakhar@gmail.com", "Backend");
        developer2 = new Developer("Oleg Vinnik", "oleg@gmail.com", "Frontend");
        developer3 = new Developer("Valentin Strikalo", "valentin@gmail.com", "QA");

        sprint = new Sprint("Sprint 1", "2026-05-18", "2026-05-25");

        task1 = new Task(
                "T-1",
                "Create task model",
                "Implement the Task class for storing team tasks",
                2,
                developer1,
                sprint
        );

        task2 = new Task(
                "T-2",
                "Create main page",
                "Create a simple user interface for the application",
                1,
                developer2,
                sprint
        );

        task3 = new Task(
                "T-3",
                "Test planner service",
                "Test adding, searching and removing tasks",
                3,
                developer3,
                sprint
        );
    }

    @Test
    void initialPlannerShouldBeEmpty() {
        assertTrue(planner.isEmpty());
        assertEquals(0, planner.getTaskCount());
        assertEquals(0, planner.getDeveloperCount());
        assertTrue(planner.getAllTasks().isEmpty());
        assertTrue(planner.getDevelopers().isEmpty());
    }

    @Test
    void addTaskShouldIncreaseTaskCount() {
        planner.addTask(task1);

        assertFalse(planner.isEmpty());
        assertEquals(1, planner.getTaskCount());
        assertEquals(task1, planner.findTaskById("T-1"));
    }

    @Test
    void addTaskShouldAlsoAddDeveloperToSet() {
        planner.addTask(task1);

        Set<Developer> developers = planner.getDevelopers();

        assertEquals(1, developers.size());
        assertTrue(developers.contains(developer1));
    }

    @Test
    void findTaskByIdShouldReturnCorrectTask() {
        planner.addTask(task1);
        planner.addTask(task2);

        Task foundTask = planner.findTaskById("T-2");

        assertNotNull(foundTask);
        assertEquals(task2, foundTask);
        assertEquals("Create main page", foundTask.getTitle());
    }

    @Test
    void findTaskByWrongIdShouldReturnNull() {
        planner.addTask(task1);

        Task foundTask = planner.findTaskById("Wrong-ID");

        assertNull(foundTask);
    }

    @Test
    void removeTaskShouldDeleteTaskFromListAndQueue() {
        planner.addTask(task1);
        planner.addTask(task2);

        boolean removed = planner.removeTask("T-1");

        assertTrue(removed);
        assertEquals(1, planner.getTaskCount());
        assertNull(planner.findTaskById("T-1"));
        assertEquals(1, planner.getTaskQueue().size());
    }

    @Test
    void removeWrongTaskShouldReturnFalse() {
        planner.addTask(task1);

        boolean removed = planner.removeTask("Wrong-ID");

        assertFalse(removed);
        assertEquals(1, planner.getTaskCount());
    }

    @Test
    void updateTaskStatusShouldChangeStatus() {
        planner.addTask(task1);

        boolean updated = planner.updateTaskStatus("T-1", TaskStatus.IN_PROGRESS);

        assertTrue(updated);
        assertEquals(TaskStatus.IN_PROGRESS, planner.findTaskById("T-1").getStatus());
    }

    @Test
    void updateWrongTaskStatusShouldReturnFalse() {
        planner.addTask(task1);

        boolean updated = planner.updateTaskStatus("Wrong-ID", TaskStatus.DONE);

        assertFalse(updated);
        assertEquals(TaskStatus.TODO, task1.getStatus());
    }

    @Test
    void queueShouldReturnFirstAddedTask() {
        planner.addTask(task1);
        planner.addTask(task2);
        planner.addTask(task3);

        Task nextTask = planner.takeNextTask();

        assertEquals(task1, nextTask);
        assertEquals(2, planner.getTaskQueue().size());
    }

    @Test
    void developerSetShouldNotAddDuplicates() {
        Developer sameDeveloper = new Developer("Another Name", "zakhar@gmail.com", "Backend");

        planner.addDeveloper(developer1);
        planner.addDeveloper(sameDeveloper);

        assertEquals(1, planner.getDeveloperCount());
    }

    @Test
    void findTasksByDeveloperShouldReturnOnlyHisTasks() {
        Task additionalTask = new Task(
                "T-4",
                "Fix backend bug",
                "Fix problem in backend service",
                4,
                developer1,
                sprint
        );

        planner.addTask(task1);
        planner.addTask(task2);
        planner.addTask(additionalTask);

        List<Task> developerTasks = planner.findTasksByDeveloper(developer1);

        assertEquals(2, developerTasks.size());
        assertTrue(developerTasks.contains(task1));
        assertTrue(developerTasks.contains(additionalTask));
        assertFalse(developerTasks.contains(task2));
    }

    @Test
    void tasksShouldBeSortedByPriority() {
        planner.addTask(task1); // priority 2
        planner.addTask(task2); // priority 1
        planner.addTask(task3); // priority 3

        List<Task> sortedTasks = planner.getTasksSortedByPriority();

        assertEquals(task2, sortedTasks.get(0));
        assertEquals(task1, sortedTasks.get(1));
        assertEquals(task3, sortedTasks.get(2));
    }

    @Test
    void duplicateTaskIdShouldThrowException() {
        Task duplicateTask = new Task(
                "T-1",
                "Duplicate task",
                "This task has the same id",
                5,
                developer2,
                sprint
        );

        planner.addTask(task1);

        assertThrows(IllegalArgumentException.class, () -> planner.addTask(duplicateTask));
    }

    @Test
    void addingNullTaskShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> planner.addTask(null));
    }

    @Test
    void addingNullDeveloperShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> planner.addDeveloper(null));
    }

    @Test
    void returnedTaskListShouldBeUnmodifiable() {
        planner.addTask(task1);

        List<Task> tasks = planner.getAllTasks();

        assertThrows(UnsupportedOperationException.class, () -> tasks.add(task2));
    }

    @Test
    void returnedQueueShouldBeCopy() {
        planner.addTask(task1);
        planner.addTask(task2);

        Queue<Task> queueCopy = planner.getTaskQueue();
        queueCopy.poll();

        assertEquals(2, planner.getTaskQueue().size());
    }
}