package com.example.cgi.jozefb.task.TaskApp.domain.subtask;
import com.example.cgi.jozefb.task.TaskApp.domain.Task.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "subtask")
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique id of the subtask")
    Long id;

    @ApiModelProperty(notes = "Name of subtask")
    String title;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    @JsonIgnoreProperties("subtasksList")
    @ApiModelProperty(notes = "Assigned task for the subtask")
    Task task;

    public Subtask(String title, Task task) {
        this.title = title;
        this.task = task;
    }

    public Subtask() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
