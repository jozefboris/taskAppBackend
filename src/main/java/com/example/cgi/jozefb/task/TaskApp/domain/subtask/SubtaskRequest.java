package com.example.cgi.jozefb.task.TaskApp.domain.subtask;

import com.example.cgi.jozefb.task.TaskApp.domain.Task.TaskService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubtaskRequest {

    @ApiModelProperty(example = "Subtask1", required = true)
    @NotNull
    private String title;

    @ApiModelProperty(example = "2", required = true)
    @NotNull
    private Long taskId;

    public Subtask toSubtask(Subtask subtask, TaskService taskService) {
        subtask.setTitle(this.title);
        subtask.setTask(taskService.getById(this.taskId).get());

        return subtask;
    }
}
