package repository;

import model.Priority;
import model.Status;
import model.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Task save(Task task) {
        if (task.getTaskId() == 0) {
            entityManager.persist(task);
            return task;
        }
        return entityManager.merge(task);
    }

    public Task findById(int id) {
        try {
            return entityManager.find(Task.class, id);
        } finally {
        }
    }

    public List<Task> findAll() {
        try {
            return entityManager.createQuery("SELECT t FROM Task t ORDER BY t.taskId", Task.class)
                    .getResultList();
        } finally {
        }
    }

    public List<Task> findByStatus(Status status) {
        try {
            return entityManager.createQuery("SELECT t FROM Task t WHERE t.status = :status ORDER BY t.dueDate", Task.class)
                    .setParameter("status", status)
                    .getResultList();
        } finally {
        }
    }

    public void deleteById(int id) {
        Task task = entityManager.find(Task.class, id);
        if (task != null) {
            entityManager.remove(task);
        }
    }

    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Task").executeUpdate();
    }

    public List<Task> findByPriority(Priority priority) {
        try {
            return entityManager.createQuery("SELECT t FROM Task t WHERE t.priority = :priority ORDER BY t.dueDate", Task.class)
                    .setParameter("priority", priority)
                    .getResultList();
        } finally {
        }
    }

    public List<Task> findByDueDate(LocalDateTime dueDate) {
        try {
            return entityManager.createQuery("SELECT t FROM Task t WHERE t.dueDate = :dueDate ORDER BY t.dueDate", Task.class)
                    .setParameter("dueDate", dueDate)
                    .getResultList();
        } finally {
        }
    }
}
