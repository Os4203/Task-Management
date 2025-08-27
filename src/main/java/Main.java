import model.Priority;
import model.User;
import repository.TaskRepository;
import repository.UserRepository;
import service.TaskManager;
import service.UserManager;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepo = new UserRepository();
        TaskRepository taskRepo = new TaskRepository();
        

        taskRepo.deleteAll();
        userRepo.deleteAll();
        
        UserManager userManager=new UserManager(userRepo);
        TaskManager taskManager=new TaskManager(taskRepo);
        

        User admin = userManager.registerUser("admin", "admin@example.com", "admin123", true);
        User user1 = userManager.registerUser("user", "user@example.com", "user123", false);
        User user2 = userManager.registerUser("user2", "user2@example.com", "user2123", false);
        User user3 = userManager.registerUser("user3", "user3@example.com", "user3123", false);
        User user4 = userManager.registerUser("user4", "user4@example.com", "user4123", false);
        User user5 = userManager.registerUser("user5", "user5@example.com", "user5123", false);
        

        taskManager.createTask("Task 1", LocalDateTime.now(), Priority.High, admin);
        taskManager.createTask("Task 2", LocalDateTime.now(), Priority.Medium, user1);
        taskManager.createTask("Task 3", LocalDateTime.now(), Priority.Low, user2);
        taskManager.createTask("Task 4", LocalDateTime.now(), Priority.High, user3);
        taskManager.createTask("Task 5", LocalDateTime.now(), Priority.Medium, user4);
        taskManager.createTask("Task 6", LocalDateTime.now(), Priority.Low, user5);
        taskManager.createTask("Task 7", LocalDateTime.now(), Priority.High, admin);
    }
}
