package model;

import java.time.LocalDateTime;

public class DefaultTask extends Task {
    public DefaultTask( String title, LocalDateTime dueDate, Priority priority, User createdBy) {
        super( title, dueDate, priority, createdBy);
    }

    public DefaultTask( String title, LocalDateTime dueDate, Priority priority,
                       Status status, User createdBy, User assignedTo) {
        super( title, dueDate, priority, status, createdBy, assignedTo);
    }
}