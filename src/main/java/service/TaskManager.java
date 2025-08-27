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


    public void createTask(String title, LocalDateTime dueDate, Priority priority,
                           User createdBy, User assignedTo) {
        Task task = new Task( title, dueDate, priority,
                Status.Pending, createdBy, assignedTo);
        taskRepo.save(task);
        System.out.println("Task created: " + title);
    }


    public void createTask(String title, LocalDateTime dueDate, Priority priority, User createdBy) {
        createTask(title, dueDate, priority, createdBy, createdBy);
    }


    public void createSimpleTask(String title, LocalDateTime dueDate, User createdBy) {
        createTask(title, dueDate, Priority.Medium, createdBy);
    }


    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }


    public Task getTaskById(int id) {
        return taskRepo.findById(id);
    }


    public void updateTask(Task task) {
        taskRepo.save(task);
        System.out.println("Task updated: " + task.getTitle());
    }


    public void deleteTaskById(int taskId) {
        taskRepo.deleteById(taskId);
        System.out.println("Task deleted with ID: " + taskId);
    }


    public void deleteAllTasks() {
        taskRepo.deleteAll();
        System.out.println("All tasks deleted");
    }

  

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


    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepo.findByPriority(priority);
    }


    public List<Task> getTasksByStatus(Status status) {
        return taskRepo.findByStatus(status);
    }


    public List<Task> getTasksByDueDate(LocalDateTime dueDate) {
        return taskRepo.findByDueDate(dueDate);
    }
}
