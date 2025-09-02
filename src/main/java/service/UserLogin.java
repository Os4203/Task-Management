package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserLogin {
    
    private UserRepository userRepo;
    
    @Autowired
    public UserLogin(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
