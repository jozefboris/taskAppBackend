package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import com.example.cgi.jozefb.task.TaskApp.domain.subtask.Subtask;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TaskResponse{

    public TaskResponse() {
    }

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.deadline = task.getDeadline();
        this.subtasksList = task.getSubtasksList();
    }

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(example = "Subtask1", required = true)
    @NotNull
    private String title;

    @ApiModelProperty(example = "Clean car", required = true)
    @NotNull
    private String description;

    @ApiModelProperty(example = "22.08.2020", required = true)
    @NotNull
    private Date deadline;

    @ApiModelProperty(example = "22.08.2020", required = true)
    @NotNull
    private List<Subtask> subtasksList;


    public List<TaskResponse> toList(Iterable<Task> taskList) {
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task: taskList)
            taskResponseList.add(new TaskResponse(task));
        return taskResponseList;
    }
}

