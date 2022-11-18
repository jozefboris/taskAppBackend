package com.example.cgi.jozefb.task.TaskApp.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(value = "User")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200" )
    @PostMapping("/user/registration")
    @ApiOperation(value = "Registration", notes = " " ,response = UserRequest.class)
    ResponseEntity<User> addUser(@RequestBody UserRequest body){
        User user = new User();
        body.toUser(user);
        User userResult = userService.register(user);
        return userResult == null ? new ResponseEntity(HttpStatus.CONFLICT) : new ResponseEntity(new UserResponse(userResult), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200" )
    @PostMapping("/user/signin")
    @ApiOperation(value = "Sign in", notes = " " ,response = User.class)
    User signUser(@RequestBody UserRequest body){
        User user = new User();
        body.toUser(user);
        return userService.signIn(user);
    }
}
