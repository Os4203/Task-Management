package com.eastnets.taskmanagement.manager.service;

import com.eastnets.taskmanagement.manager.model.*;
import com.eastnets.taskmanagement.manager.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepo taskRepository;
    public void save(Tasks task){
        if ((task instanceof UrgentTasks) || (task.getTaskType() != null && task.getTaskType() == TaskType.URGENT)) {
            task.setPriority(Priority.High);
        }
        taskRepository.save(task);
    }
    public void update(Tasks task){
        if ((task instanceof UrgentTasks) || (task.getTaskType() != null && task.getTaskType() == TaskType.URGENT)) {
            task.setPriority(Priority.High);
        }
        taskRepository.save(task);
    }
    public Tasks findById(int id){
        return taskRepository.findById(id).orElse(null);
    }
    public List<Tasks> findAll(){
        return taskRepository.findAll();
    }
    public List<Tasks> findByType(TaskType type){
        return taskRepository.findByTaskType(type);
    }
    public void deleteById(int id){
        taskRepository.deleteById(id);
    }
    public void deleteAll(){
        taskRepository.deleteAll();
    }
    public boolean addDependency(int taskId, int dependsOnId){
        if (taskId == dependsOnId) {
            return false;
        }
        Tasks task = findById(taskId);
        Tasks dependsOn = findById(dependsOnId);
        if (task == null || dependsOn == null) {
            return false;
        }
        if (task.getDependencies().stream().anyMatch(t -> t.getTaskId() == dependsOnId)) {
            return true;
        }
        task.addDependency(dependsOn);
        taskRepository.save(task);
        return true;
    }
    public void addDependTask(DependTasks task, Tasks dependentTask){
        task.setDependentTask(dependentTask);
        if (dependentTask instanceof DependTasks dependTasks) {
            dependTasks.setDependentOnTask(task);
        }
        taskRepository.save(task);
    }
    public void addGroupedTask(GroupedTasks task, Tasks groupedTask){
        task.setGroupedTask(groupedTask);
        taskRepository.save(task);
    }
    public void addUrgentTask(UrgentTasks task, Tasks urgentTask){
        task.setPriority(Priority.High);
        task.setUrgentTask(urgentTask);
        taskRepository.save(task);
    }
    public void addDefaultTask(DefaultTasks task, Tasks defaultTask){
        task.setDefaultTask(defaultTask);
        taskRepository.save(task);
    }
}
