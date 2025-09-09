package com.eastnets.taskmanagement.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.eastnets.taskmanagement.manager.model.*;
import com.eastnets.taskmanagement.manager.service.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<Users> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")    //http://localhost:8080/users/1
    public ResponseEntity<Users> getUserById(@PathVariable Integer id){
        Users user = userService.findById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public Users createUser(@RequestBody Users user){
        userService.save(user);
        return user;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users user){
        Users existing = userService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setUsername(user.getUsername());
        existing.setPassword(user.getPassword());
        existing.setEmail(user.getEmail());
        existing.setAdmin(user.isAdmin());
        userService.update(existing);
        return ResponseEntity.ok(existing);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers(){
        userService.deleteAllUsersAndTasks();
        return ResponseEntity.noContent().build();
    }
    
   
}
