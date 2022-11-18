package com.example.cgi.jozefb.task.TaskApp.domain.User;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserResponse {

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    @ApiModelProperty(example = "name", required = true)
    @NotNull
    private String login;

    @ApiModelProperty(example = "pass", required = true)
    @NotNull
    private String password;

}