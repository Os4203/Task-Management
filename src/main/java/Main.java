import model.Priority;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.TaskManager;
import service.UserManager;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(config.AppConfig.class);

        TaskManager taskManager = context.getBean("taskManager", TaskManager.class);

        UserManager userManager = context.getBean("userManager", UserManager.class);
        System.out.println("Spring context loaded successfully!");
        taskManager.deleteAllTasks();   
        userManager.deleteAll();
        

        User admin = userManager.registerUser("admin", "admin@example.com", "admin123", true);
        User user1 = userManager.registerUser("user", "user@example.com", "user123", false);
        User user2 = userManager.registerUser("user2", "user2@example.com", "user2123", false);
        User user3 = userManager.registerUser("user3", "user3@example.com", "user3123", false);
        User user4 = userManager.registerUser("user4", "user4@example.com", "user4123", false);
        User user5 = userManager.registerUser("user5", "user5@example.com", "user5123", false);
        User user6 = userManager.registerUser("user6", "user6@example.com", "user6623", false);
        User user7 = userManager.registerUser("user7", "user7@example.com", "user7723", false);

        taskManager.createTask("Task 1", LocalDateTime.now(), Priority.High, admin);
        taskManager.createTask("Task 2", LocalDateTime.now(), Priority.Medium, user1);
        taskManager.createTask("Task 3", LocalDateTime.now(), Priority.Low, user2);
        taskManager.createTask("Task 4", LocalDateTime.now(), Priority.High, user3);
        taskManager.createTask("Task 5", LocalDateTime.now(), Priority.Medium, user4);
        taskManager.createTask("Task 6", LocalDateTime.now(), Priority.Low, user5);
        taskManager.createTask("Task 7", LocalDateTime.now(), Priority.High, user6);
        taskManager.createTask("Task 8", LocalDateTime.now(), Priority.High, user7);


    }
}
