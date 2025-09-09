// package com.eastnets.taskmanagement.manager.repository;

// import org.springframework.stereotype.Repository;
// import java.util.List;
// import jakarta.persistence.*;
// import com.eastnets.taskmanagement.manager.model.*;

// @Repository
// public class TaskRepository {
//     @PersistenceContext
//     private EntityManager entityManager;

//     public void save(Tasks task){
//         entityManager.persist(task);
//     }

//     public void update(Tasks task){
//         entityManager.merge(task);
//     }

//     public void delete(Tasks task){
//         entityManager.remove(task);
//     }

//     public Tasks findById(int id){
//         return entityManager.find(Tasks.class, id);
//     }

//     public List<Tasks> findAll(){
//         return entityManager.createQuery("FROM Tasks", Tasks.class).getResultList();
//     }
// }
