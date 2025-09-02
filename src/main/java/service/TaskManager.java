package service;

import model.Priority;
import model.Status;
import model.Task;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskManager {
    @Autowired
    private TaskRepository taskRepo;

   
    public TaskManager(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }


    @Transactional
    public void createTask(String title, LocalDateTime dueDate, Priority priority,
                           User createdBy, User assignedTo) {
        Task task = new Task( title, dueDate, priority,
                Status.Pending, createdBy, assignedTo);
        taskRepo.save(task);
        System.out.println("Task created: " + title);
    }


    @Transactional
    public void createTask(String title, LocalDateTime dueDate, Priority priority, User createdBy) {
        createTask(title, dueDate, priority, createdBy, createdBy);
    }


    @Transactional
    public void createSimpleTask(String title, LocalDateTime dueDate, User createdBy) {
        createTask(title, dueDate, Priority.Medium, createdBy);
    }


    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }


    public Task getTaskById(int id) {
        return taskRepo.findById(id);
    }


    @Transactional
    public void updateTask(Task task) {
        taskRepo.save(task);
        System.out.println("Task updated: " + task.getTitle());
    }


    @Transactional
    public void deleteTaskById(int taskId) {
        taskRepo.deleteById(taskId);
        System.out.println("Task deleted with ID: " + taskId);
    }


    @Transactional
    public void deleteAllTasks() {
        taskRepo.deleteAll();
        System.out.println("All tasks deleted");
    }

  

    @Transactional
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


    @Transactional
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
