package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import com.example.cgi.jozefb.task.TaskApp.domain.subtask.Subtask;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity()
@Table(name = "task")
@ApiModel(description = "Detail about the task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique id of the task")
    Long id;

    @ApiModelProperty(notes = "Name of task")
    String title;

    @ApiModelProperty(notes = "Description of task")
    String description;

    @ApiModelProperty(notes = "Task deadline")
    Date deadline;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task")
    @JsonIgnoreProperties("task")
    private List<Subtask> subtasksList;

    public Task(String title, String description, Date deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    public Task() {
    }

    public List<Subtask> getSubtasksList() {
        return subtasksList;
    }

    public void setSubtasksList(List<Subtask> subtasksList) {
        this.subtasksList = subtasksList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadLine) {
        this.deadline = deadLine;
    }
}
