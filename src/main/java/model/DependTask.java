package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DependTask extends Task {
    private List<Task> dependencies;

    public DependTask( String title, LocalDateTime dueDate, Priority priority, User createdBy) {
        super( title, dueDate, priority, createdBy);
        this.dependencies = new ArrayList<>();
    }

    public DependTask( String title, LocalDateTime dueDate, Priority priority,
                      Status status, User createdBy, User assignedTo) {
        super( title, dueDate, priority, status, createdBy, assignedTo);
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(Task task) {
        dependencies.add(task);
    }

    public List<Task> getDependencies() {
        return new ArrayList<>(dependencies);
    }

    public boolean canStart() {
        for (Task dependency : dependencies) {
            if (dependency.getStatus() != Status.Completed) {
                return false;
            }
        }
        return true;
    }
}