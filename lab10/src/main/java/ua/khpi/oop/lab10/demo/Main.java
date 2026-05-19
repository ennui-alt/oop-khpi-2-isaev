package ua.khpi.oop.lab10.demo;

import ua.khpi.oop.lab10.container.TaskContainer;
import ua.khpi.oop.lab10.model.Developer;
import ua.khpi.oop.lab10.model.Task;
import ua.khpi.oop.lab10.model.TaskStatus;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        Developer dev1 = new Developer(1, "Zakhar Isaiev", "Backend developer");
        Developer dev2 = new Developer(2, "Oleg Vinnik", "Frontend developer");
        Developer dev3 = new Developer(3, "Valentin Strikalo", "Tester");

        Task task1 = new Task(
                1,
                "Authorization",
                "Create login and registration",
                dev1
        );

        Task task2 = new Task(
                2,
                "Main page",
                "Create main page layout",
                dev2
        );

        Task task3 = new Task(
                3,
                "Testing",
                "Check task module",
                dev3
        );

        TaskContainer<Task> taskList = new TaskContainer<>(2);

        System.out.println("Adding tasks to container...");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        System.out.println("Size: " + taskList.size());
        System.out.println("Capacity: " + taskList.capacity());

        System.out.println();
        System.out.println("Task by index:");
        System.out.println(taskList.get(0));
        System.out.println(taskList.get(1));
        System.out.println(taskList.get(2));

        task1.changeStatus(TaskStatus.IN_PROGRESS, "Developer started this task", dev1);
        task2.changeStatus(TaskStatus.CHECKING, "Page is ready for checking", dev2);
        task3.changeStatus(TaskStatus.DONE, "Tests were completed", dev3);

        System.out.println();
        System.out.println("Traversal with Iterator:");

        Iterator<Task> iterator = taskList.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println();
        System.out.println("Traversal with for-each:");

        for (Task task : taskList) {
            System.out.println(task);
        }

        System.out.println();
        System.out.println("Status history for first task:");

        for (int i = 0; i < task1.getHistorySize(); i++) {
            System.out.println(task1.getHistoryRecord(i));
        }

        System.out.println();
        System.out.println("Removing second task...");
        Task removedTask = taskList.remove(1);

        System.out.println("Removed task: " + removedTask);

        System.out.println();
        System.out.println("Tasks after removing:");

        for (Task task : taskList) {
            System.out.println(task);
        }
    }
}