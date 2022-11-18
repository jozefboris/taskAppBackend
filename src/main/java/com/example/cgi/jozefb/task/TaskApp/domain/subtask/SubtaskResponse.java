package com.example.cgi.jozefb.task.TaskApp.domain.subtask;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SubtaskResponse{

    public SubtaskResponse() {
    }

    public SubtaskResponse(Subtask subtask) {
        this.id = subtask.getId();
        this.title = subtask.getTitle();
    }

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(example = "Subtask1", required = true)
    @NotNull
    private String title;

    public List<SubtaskResponse> toList(List<Subtask> subtaskList) {
        List<SubtaskResponse> subtaskResponseList = new ArrayList<>();
        for (Subtask subtask: subtaskList)
            subtaskResponseList.add(new SubtaskResponse(subtask));
        return subtaskResponseList;
    }
}
