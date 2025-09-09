package com.eastnets.taskmanagement.manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "dueDate", nullable = false)
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type", nullable = false)
    private TaskType taskType = TaskType.DEFAULT;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private Users assignedTo;

    @ManyToMany
    @JoinTable(
        name = "task_dependencies",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "depends_on_id")

    )
    private List<Tasks> dependencies = new ArrayList<>();

    public void addDependency(Tasks task) {
        dependencies.add(task);
    }

    public List<Tasks> getDependencies() {
        return new ArrayList<>(dependencies);
    }

    public boolean canStart() {
        for (Tasks dependency : dependencies) {
            if (dependency.getStatus() != Status.Completed) {
                return false;
            }
        }
        return true;
    }

}
