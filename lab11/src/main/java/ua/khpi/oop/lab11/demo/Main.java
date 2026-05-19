package ua.khpi.oop.lab11.demo;

import ua.khpi.oop.lab11.model.Developer;
import ua.khpi.oop.lab11.model.Sprint;
import ua.khpi.oop.lab11.model.Task;
import ua.khpi.oop.lab11.model.TaskStatus;
import ua.khpi.oop.lab11.service.TeamTaskPlanner;

public class Main {

    public static void main(String[] args) {
        TeamTaskPlanner planner = new TeamTaskPlanner();

        Developer developer1 = new Developer("Zakhar Isaiev", "zakhar@gmail.com", "Backend");
        Developer developer2 = new Developer("Oleg Vinnik", "oleg@gmail.com", "Frontend");
        Developer developer3 = new Developer("Valentin Strikalo", "valentin@gmail.com", "QA");

        Sprint sprint = new Sprint("Sprint 1", "2026-05-18", "2026-05-25");

        Task task1 = new Task(
                "T-1",
                "Create task model",
                "Implement the Task class for storing team tasks",
                2,
                developer1,
                sprint
        );

        Task task2 = new Task(
                "T-2",
                "Create main page",
                "Create a simple user interface for the application",
                1,
                developer2,
                sprint
        );

        Task task3 = new Task(
                "T-3",
                "Test planner service",
                "Test adding, searching and removing tasks",
                3,
                developer3,
                sprint
        );

        planner.addTask(task1);
        planner.addTask(task2);
        planner.addTask(task3);

        planner.printAllTasks();

        System.out.println();
        planner.printDevelopers();

        System.out.println();
        System.out.println("=== Search task by id ===");
        System.out.println(planner.findTaskById("T-2"));

        System.out.println();
        System.out.println("=== Change task status ===");
        planner.updateTaskStatus("T-1", TaskStatus.IN_PROGRESS);
        System.out.println(planner.findTaskById("T-1"));

        System.out.println();
        System.out.println("=== Task queue ===");
        System.out.println("Next task: " + planner.takeNextTask());
        System.out.println("Tasks left in queue: " + planner.getTaskQueue().size());

        System.out.println();
        System.out.println("=== Sorting by priority ===");
        for (Task task : planner.getTasksSortedByPriority()) {
            System.out.println(task);
        }

        System.out.println();
        System.out.println("=== Removing task ===");
        planner.removeTask("T-3");
        planner.printAllTasks();
    }
}