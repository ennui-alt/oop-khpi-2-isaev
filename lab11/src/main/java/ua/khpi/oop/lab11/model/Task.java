package ua.khpi.oop.lab11.model;

import java.util.Objects;

public class Task {

    private String id;
    private String title;
    private String description;
    private int priority;
    private Developer developer;
    private Sprint sprint;
    private TaskStatus status;

    public Task(String id, String title, String description, int priority,
                Developer developer, Sprint sprint) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.developer = developer;
        this.sprint = sprint;
        this.status = TaskStatus.TODO;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", developer=" + developer.getName() +
                ", sprint=" + sprint.getName() +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}