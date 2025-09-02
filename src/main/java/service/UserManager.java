package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
public class UserManager {
    @Autowired
    private UserRepository userRepo;

    
    public UserManager(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
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
    @Transactional
    public void save(User user) {
        userRepo.save(user);
    }
    @Transactional
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
    
    @Transactional
    public void deleteAll() {
        userRepo.deleteAll();
    }
}
