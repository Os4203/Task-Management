package model;

import java.time.LocalDateTime;

public class UrgentTask extends Task {
    public UrgentTask( String title, LocalDateTime dueDate, Priority priority, User createdBy) {
        super( title, dueDate, priority, createdBy);
    }

    public UrgentTask( String title, LocalDateTime dueDate, Priority priority,
                      Status status, User createdBy, User assignedTo) {
        super( title, dueDate, priority, status, createdBy, assignedTo);
    }

    public boolean isHighPriority() {
        return getPriority() == Priority.High;
    }

    @Override
    public String toString() {
        return "UrgentTask{" +
                "taskId=" + getTaskId() +
                ", title='" + getTitle() + '\'' +
                ", dueDate=" + getDueDate() +
                ", priority=" + getPriority() +
                ", status=" + getStatus() +
                ", createdBy=" + getCreatedBy() +
                ", assignedTo=" + getAssignedTo() +
                '}';
    }
}