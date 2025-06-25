package org.javarush.todo_list.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.javarush.todo_list.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Task> findPaginated(int page, int size) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Task", Task.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public long countTasks() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(t.id) from Task t", Long.class)
                .getSingleResult();
    }

    public void save(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(task);
    }

    public Task findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Task.class, id);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Task where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
