package com.example.cgi.jozefb.task.TaskApp.domain.User;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRequest {

    @ApiModelProperty(example = "name", required = true)
    @NotNull
    private String login;

    @ApiModelProperty(example = "pass", required = true)
    @NotNull
    private String password;

    public User toUser(User user) {
        user.setLogin(this.login);
        user.setPassword(this.password);
        return user;
    }
}
