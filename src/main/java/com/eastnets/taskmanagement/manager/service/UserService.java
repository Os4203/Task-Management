package com.eastnets.taskmanagement.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.eastnets.taskmanagement.manager.model.*;
import com.eastnets.taskmanagement.manager.repository.UserRepo;
import com.eastnets.taskmanagement.manager.repository.TaskRepo;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private TaskRepo taskRepository;
    public void save(Users user){
        userRepository.save(user);
    }
    public void update(Users user){
        userRepository.save(user);
    }   
    public Users findById(Integer id){
        return userRepository.findById(id).orElse(null);
    }
    public List<Users> findAll(){
        return userRepository.findAll();
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }
    public void deleteAllUsersAndTasks(){
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }
    
}
