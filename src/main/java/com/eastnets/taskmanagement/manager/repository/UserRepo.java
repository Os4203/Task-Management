package com.eastnets.taskmanagement.manager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eastnets.taskmanagement.manager.model.*;

public interface UserRepo extends JpaRepository<Users, Integer> {

}
