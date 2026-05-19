package ua.khpi.oop.lab10.model;

public class Task {

    private int id;
    private String title;
    private String description;
    private Developer developer;
    private TaskStatus status;

    private StatusRecord[] history;
    private int historySize;

    public Task(int id, String title, String description, Developer developer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.status = TaskStatus.NEW;

        this.history = new StatusRecord[5];
        this.historySize = 0;

        addStatusRecord(TaskStatus.NEW, "Task was created", developer);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getHistorySize() {
        return historySize;
    }

    public StatusRecord getHistoryRecord(int index) {
        if (index < 0 || index >= historySize) {
            throw new IndexOutOfBoundsException("Wrong history index");
        }

        return history[index];
    }

    public void changeStatus(TaskStatus newStatus, String comment, Developer developer) {
        this.status = newStatus;
        addStatusRecord(newStatus, comment, developer);
    }

    private void addStatusRecord(TaskStatus status, String comment, Developer developer) {
        if (historySize == history.length) {
            increaseHistoryArray();
        }

        history[historySize] = new StatusRecord(status, comment, developer);
        historySize++;
    }

    private void increaseHistoryArray() {
        StatusRecord[] newHistory = new StatusRecord[history.length * 2];

        for (int i = 0; i < history.length; i++) {
            newHistory[i] = history[i];
        }

        history = newHistory;
    }

    @Override
    public String toString() {
        return "Task #" + id
                + ": " + title
                + " | developer: " + developer
                + " | status: " + status;
    }
}