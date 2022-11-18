package com.example.cgi.jozefb.task.TaskApp.domain.User;

import com.example.cgi.jozefb.task.TaskApp.domain.Task.Task;

import java.util.Optional;

interface UserService {
    User register(User user);
    User signIn(User user);
    boolean isFreeLogin(String login);
    User getByLogin(String login);

}
