package service;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import model.User;
import repository.UserRepository;

public class UserManager {
    private UserRepository userRepo;

    public UserManager(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(String username, String email, String password, boolean isAdmin) {
        User user = new User( username, password, email, isAdmin);
        userRepo.save(user);
        return user;
    }
    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public User findById(int id) {
        return userRepo.findById(id);
    }
    public void save(User user) {
        userRepo.save(user);
    }
    public void delete(int id) {
        userRepo.deleteById(id);
    }
    public List<User> findAll() {
        return userRepo.findAll();
    }
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
  
}
