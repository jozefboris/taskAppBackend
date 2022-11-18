package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import com.example.cgi.jozefb.task.TaskApp.domain.subtask.Subtask;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.cgi.jozefb.task.TaskApp.prototype.TaskPrototype.aTask;
import static com.example.cgi.jozefb.task.TaskApp.prototype.TaskPrototype.aTaskNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
    void getTasks() {
        taskService.addTask( aTask());
        taskService.addTask( aTaskNull());

        List<Task> taskList = Lists.newArrayList(taskService.getTasks());
        System.out.println(taskList.size());
        Assert.assertEquals(2, taskList.size());
    }

    @Test
    void getById() {

        Task addedTask = taskService.addTask(aTask());

        Optional<Task> getedTask = taskService.getById(addedTask.getId());
        Assert.assertEquals(getedTask.get().getId(), addedTask.getId());
        Assert.assertEquals(getedTask.get().getDescription(), aTask().getDescription());
        Assert.assertEquals(getedTask.get().getTitle(), aTask().getTitle());

    }

    @Test
    void addTask() {

        Task addedTask = taskService.addTask(aTask());
        Assert.assertEquals(addedTask.getTitle(), aTask().getTitle());
        Assert.assertEquals(addedTask.getDescription(), aTask().getDescription());
    }

    @Test
    void updateTask() {

        Task addedTask = taskService.addTask(aTask());

        addedTask.setDescription("Spring");
        Task updatedTask = taskService.updateTask(addedTask, addedTask.getId() );
        Assert.assertEquals(updatedTask.getTitle(), addedTask.getTitle());
        Assert.assertEquals(updatedTask.getDescription(), "Spring");

    }

    @Test
    void deleteTaskById() {
        Task addedTask = taskService.addTask(aTask());
        taskService.deleteTaskById(addedTask.getId());
        Optional<Task> getedTask = taskService.getById(addedTask.getId());
        Assert.assertEquals(Optional.empty(), getedTask);


    }
}