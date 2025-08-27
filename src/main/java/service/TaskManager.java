package service;

import model.Task;
import model.Priority;
import model.Status;
import model.User;
import repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TaskManager {
    private TaskRepository taskRepo;

    public TaskManager(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    /**
     * Create a new task with the specified details
     */
    public void createTask(String title, LocalDateTime dueDate, Priority priority,
                           User createdBy, User assignedTo) {
        Task task = new Task( title, dueDate, priority,
                Status.Pending, createdBy, assignedTo);
        taskRepo.save(task);
        System.out.println("Task created: " + title);
    }

    /**
     * Create a new task assigned to the creator by default
     * @return 
     *  
     */
    public void createTask(String title, LocalDateTime dueDate, Priority priority, User createdBy) {
        createTask(title, dueDate, priority, createdBy, createdBy);
    }

    /**
     * Create a simple task with basic priority
     */
    public void createSimpleTask(String title, LocalDateTime dueDate, User createdBy) {
        createTask(title, dueDate, Priority.Medium, createdBy);
    }

    /**
     * Get all tasks from the repository
     */
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    /**
     * Get a specific task by ID
     */
    public Task getTaskById(int id) {
        return taskRepo.findById(id);
    }

    /**
     * Update an existing task
     */
    public void updateTask(Task task) {
        taskRepo.save(task);
        System.out.println("Task updated: " + task.getTitle());
    }

    /**
     * Delete a task
     */
    public void deleteTaskById(int taskId) {
        taskRepo.deleteById(taskId);
        System.out.println("Task deleted with ID: " + taskId);
    }

    /**
     * Delete all tasks
     */
    public void deleteAllTasks() {
        taskRepo.deleteAll();
        System.out.println("All tasks deleted");
    }

  
    /**
     * Mark a task as completed
     */
    public void completeTask(int taskId) {
        Task task = taskRepo.findById(taskId);
        if (task != null) {
            task.markCompleted();
            taskRepo.save(task);
            System.out.println("Task marked as completed: " + task.getTitle());
        } else {    
            System.out.println("Task not found with ID: " + taskId);
        }
    }

    /**
     * Assign a task to a different user
     */
    public void assignTask(int taskId, User assignedTo) {
        Task task = taskRepo.findById(taskId);
        if (task != null) {
            task.assignTo(assignedTo);
            taskRepo.save(task);
            System.out.println("Task '" + task.getTitle() + "' assigned to: " + assignedTo.getName());
        } else {
            System.out.println("Task not found with ID: " + taskId);
        }
    }

    /**
     * Get tasks filtered by priority
     */
    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepo.findByPriority(priority);
    }

    /**
     * Get tasks filtered by status
     */
    public List<Task> getTasksByStatus(Status status) {
        return taskRepo.findByStatus(status);
    }

    /**
     * Get tasks due on a specific date
     */
    public List<Task> getTasksByDueDate(LocalDateTime dueDate) {
        return taskRepo.findByDueDate(dueDate);
    }
}
