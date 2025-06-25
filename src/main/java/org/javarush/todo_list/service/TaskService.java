package org.javarush.todo_list.service;

import org.javarush.todo_list.dao.TaskDAO;
import org.javarush.todo_list.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDAO dao;

    @Transactional
    public List<Task> findPaginated(int page, int size) {
        return dao.findPaginated(page, size);
    }

    @Transactional
    public long countTasks() {
        return dao.countTasks();
    }

    @Transactional
    public void save(Task task) {
        dao.save(task);
    }

    @Transactional
    public Task findById(int id) {
        return dao.findById(id);
    }

    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

}
