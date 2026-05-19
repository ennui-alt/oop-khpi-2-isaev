package ua.khpi.oop.lab11.service;

import ua.khpi.oop.lab11.model.Developer;
import ua.khpi.oop.lab11.model.Task;
import ua.khpi.oop.lab11.model.TaskStatus;

import java.util.*;

public class TeamTaskPlanner {

    private final List<Task> tasks = new ArrayList<>();
    private final Queue<Task> taskQueue = new LinkedList<>();
    private final Set<Developer> developers = new LinkedHashSet<>();

    public void addDeveloper(Developer developer) {
        if (developer == null) {
            throw new IllegalArgumentException("Developer cannot be null");
        }

        developers.add(developer);
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        if (findTaskById(task.getId()) != null) {
            throw new IllegalArgumentException("Task with this id already exists: " + task.getId());
        }

        tasks.add(task);
        taskQueue.offer(task);
        developers.add(task.getDeveloper());
    }

    public Task findTaskById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }

        return null;
    }

    public List<Task> findTasksByDeveloper(Developer developer) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDeveloper().equals(developer)) {
                result.add(task);
            }
        }

        return result;
    }

    public boolean removeTask(String id) {
        Task task = findTaskById(id);

        if (task == null) {
            return false;
        }

        tasks.remove(task);
        taskQueue.remove(task);

        return true;
    }

    public boolean updateTaskStatus(String id, TaskStatus status) {
        Task task = findTaskById(id);

        if (task == null) {
            return false;
        }

        task.setStatus(status);
        return true;
    }

    public Task takeNextTask() {
        return taskQueue.poll();
    }

    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public Queue<Task> getTaskQueue() {
        return new LinkedList<>(taskQueue);
    }

    public Set<Developer> getDevelopers() {
        return Collections.unmodifiableSet(developers);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public int getDeveloperCount() {
        return developers.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getTasksSortedByPriority() {
        List<Task> sortedTasks = new ArrayList<>(tasks);

        sortedTasks.sort(Comparator.comparingInt(Task::getPriority));

        return sortedTasks;
    }

    public void printAllTasks() {
        System.out.println("=== All team tasks ===");

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void printDevelopers() {
        System.out.println("=== Team developers ===");

        Iterator<Developer> iterator = developers.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}