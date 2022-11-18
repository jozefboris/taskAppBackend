package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task, long id) {
        return taskRepository.findById(id).map(taskItem -> {
            taskItem.setTitle(task.getTitle());
            taskItem.setDescription(task.getDescription());
            taskItem.setDeadline(task.getDeadline());
            return taskRepository.save(taskItem);
        }).orElseGet(() ->{
            return taskRepository.save(task);
        });
    }

    @Override
    public void deleteTaskById(long id) {
         taskRepository.deleteById(id);
    }


}
