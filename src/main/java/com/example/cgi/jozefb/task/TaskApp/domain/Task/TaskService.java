package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import java.util.Optional;

public interface TaskService {
    Iterable<Task> getTasks();
    Optional<Task> getById(long id);
    Task addTask(Task task);
    Task updateTask(Task task, long id);
    void deleteTaskById(long id);
}
