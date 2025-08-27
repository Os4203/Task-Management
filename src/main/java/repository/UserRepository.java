package repository;
import java.util.List;

import javax.persistence.*;

import model.User;

public class UserRepository {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("my-persistence-unit");

    public User save(User user) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
            return user;
        } finally {
            em.close();
        }
    }

    public User findById(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    public void deleteById(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(findById(id));
            tx.commit();
        } finally {
            em.close();
        }
    }
    public void deleteAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createQuery("DELETE FROM User").executeUpdate();
            tx.commit();
        } finally {
            em.close();
        }
    }

    public User findByUsername(String username) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }

    public User findByEmail(String email) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }

    public User findByUsernameAndPassword(String username, String password) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }

    public User findByEmailAndPassword(String email, String password) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }
}