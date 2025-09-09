// package com.eastnets.taskmanagement.manager.repository;

// import org.springframework.stereotype.Repository;
// import java.util.List;
// import jakarta.persistence.*;
// import com.eastnets.taskmanagement.manager.model.*;

// @Repository
// public class UserRepository {
//     @PersistenceContext
//     private EntityManager entityManager;
    
//     public void save(Users user){
//         entityManager.persist(user);
//     }

//     public void update(Users user){
//         entityManager.merge(user);
//     }

//     public void delete(Users user){
//         entityManager.remove(user);
//     }

//     public Users findById(int id){
//         return entityManager.find(Users.class, id);
//     }

//     public List<Users> findAll(){
//         return entityManager.createQuery("FROM Users", Users.class).getResultList();
//     }

// }
