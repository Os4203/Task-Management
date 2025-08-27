package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupedTask extends Task {
    private List<Task> subTasks;

    public GroupedTask( String title, LocalDateTime dueDate, Priority priority, User createdBy) {
        super( title, dueDate, priority, createdBy);
        this.subTasks = new ArrayList<>();
    }

    public GroupedTask( String title, LocalDateTime dueDate, Priority priority,
                       Status status, User createdBy, User assignedTo) {
        super( title, dueDate, priority, status, createdBy, assignedTo);
        this.subTasks = new ArrayList<>();
    }

    public void addSubTask(Task task) {
        subTasks.add(task);
    }

    public List<Task> getSubTasks() {
        return new ArrayList<>(subTasks);
    }

    public double getCompletionPercentage() {
        if (subTasks.isEmpty()) {
            return getStatus() == Status.Completed ? 100.0 : 0.0;
        }

        long completedTasks = subTasks.stream()
                .mapToInt(task -> task.getStatus() == Status.Completed ? 1 : 0)
                .sum();

        return (completedTasks * 100.0) / subTasks.size();
    }
}