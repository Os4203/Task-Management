package repository;
import javax.persistence.*;
import model.Task;
import model.*;

import java.time.LocalDateTime;
import java.util.List;

public class TaskRepository {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("my-persistence-unit");

    public Task save(Task task) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (task.getTaskId() == 0) {
                em.persist(task);
            } else {
                task = em.merge(task);
            }
            tx.commit();
            return task;
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public Task findById(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    public List<Task> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Task t ORDER BY t.taskId", Task.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Task> findByStatus(Status status) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Task t WHERE t.status = :status ORDER BY t.dueDate", Task.class)
                    .setParameter("status", status)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteById(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Task task = em.find(Task.class, id);
            if (task != null) {
                em.remove(task);
            }
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void deleteAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createQuery("DELETE FROM Task").executeUpdate();
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Task> findByPriority(Priority priority) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Task t WHERE t.priority = :priority ORDER BY t.dueDate", Task.class)
                    .setParameter("priority", priority)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Task> findByDueDate(LocalDateTime dueDate) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Task t WHERE t.dueDate = :dueDate ORDER BY t.dueDate", Task.class)
                    .setParameter("dueDate", dueDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}

