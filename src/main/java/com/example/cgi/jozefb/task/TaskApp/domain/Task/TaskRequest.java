package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import com.example.cgi.jozefb.task.TaskApp.domain.subtask.Subtask;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskRequest {

    @ApiModelProperty(example = "Task1", required = true)
    @NotNull
    private String title;

    @ApiModelProperty(example = "Clean room", required = true)
    private String description;

    @ApiModelProperty(example = "22.08.2021", required = true)
    @NotNull
    private Date deadline;

    @ApiModelProperty(example = "22.08.2020", required = true)
    @NotNull
    private List<Subtask> subtasksList;

    public Task toTask(Task task) {
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setDeadline(this.deadline);
        task.setSubtasksList(this.subtasksList);
        return task;
    }
}

