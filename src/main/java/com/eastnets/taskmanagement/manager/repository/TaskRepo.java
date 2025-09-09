package com.eastnets.taskmanagement.manager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eastnets.taskmanagement.manager.model.*;
import java.util.List;

public interface TaskRepo extends JpaRepository<Tasks, Integer> {
    List<Tasks> findByTaskType(TaskType taskType);
 
}
