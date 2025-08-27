package service;

import model.User;
import repository.UserRepository;

public class UserLogin {
    public User login(String email, String password) {
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    

    
}
