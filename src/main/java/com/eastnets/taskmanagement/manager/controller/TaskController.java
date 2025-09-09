package com.eastnets.taskmanagement.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.eastnets.taskmanagement.manager.model.*;
import com.eastnets.taskmanagement.manager.service.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @GetMapping
    public List<Tasks> getAllTasks(){
        return taskService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable Integer id){
        Tasks task = taskService.findById(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }
    @PostMapping
        public ResponseEntity<Tasks> createTask(@RequestBody Tasks task){
        if (task.getCreatedBy() != null && task.getCreatedBy().getId() != 0) {
            Users creator = userService.findById(task.getCreatedBy().getId());
            if (creator == null) return ResponseEntity.badRequest().build();
            task.setCreatedBy(creator);
        } else {
            return ResponseEntity.badRequest().build();
        }
        if (task.getAssignedTo() != null && task.getAssignedTo().getId() != 0) {
            Users assignee = userService.findById(task.getAssignedTo().getId());
            if (assignee == null) return ResponseEntity.badRequest().build();
            task.setAssignedTo(assignee);
        } else {
            task.setAssignedTo(null);
        }
        taskService.save(task);
        return ResponseEntity.ok(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tasks> updateTask(@PathVariable Integer id, @RequestBody Tasks task){
        Tasks existing = taskService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setTitle(task.getTitle());
        existing.setDueDate(task.getDueDate());
        existing.setPriority(task.getPriority());
        existing.setStatus(task.getStatus());
        if (task.getCreatedBy() != null && task.getCreatedBy().getId() != 0) {
            Users creator = userService.findById(task.getCreatedBy().getId());
            if (creator == null) return ResponseEntity.badRequest().build();
            existing.setCreatedBy(creator);
        }
        if (task.getAssignedTo() != null) {
            if (task.getAssignedTo().getId() != 0) {
                Users assignee = userService.findById(task.getAssignedTo().getId());
                if (assignee == null) return ResponseEntity.badRequest().build();
                existing.setAssignedTo(assignee);
            } else {
                existing.setAssignedTo(null);
            }
        }
        taskService.update(existing);
        return ResponseEntity.ok(existing);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id){
        Tasks existing = taskService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTasks(){
        taskService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
