package com.example.cgi.jozefb.task.TaskApp.prototype;

import com.example.cgi.jozefb.task.TaskApp.domain.Task.Task;
import com.example.cgi.jozefb.task.TaskApp.domain.subtask.Subtask;

import java.util.ArrayList;
import java.util.List;

public class TaskPrototype {
    public static Task aTask(){


        Task task = new Task();
        task.setTitle("Task1");
        task.setDescription("Spring project");
        java.util.Date date=new java.util.Date();
        task.setDeadline(date);
        List<Subtask> subtasks = new ArrayList();
        Subtask subtask = new Subtask("Clean", task);
        subtasks.add(subtask);
        task.setSubtasksList(subtasks);
        return task;
    }

    public static Task aTaskNull(){
        Task task = new Task();
        task.setTitle("Task1");
        task.setDescription(null);
        task.setDeadline(null);
        task.setSubtasksList(null);
        return task;
    }
}
