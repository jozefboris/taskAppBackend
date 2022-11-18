package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import com.example.cgi.jozefb.task.TaskApp.domain.subtask.Subtask;
import com.example.cgi.jozefb.task.TaskApp.domain.subtask.SubtaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(value = "Task")
@PreAuthorize("isAuthenticated()")
public class TaskController {

    TaskService taskService;
    SubtaskService subtaskService;

    @Autowired
    private ModelMapper modelMapper;

    public TaskController(TaskService taskService, SubtaskService subtaskService) {
        this.taskService = taskService;
        this.subtaskService = subtaskService;
    }

    @CrossOrigin(origins = "http://localhost:4200" )
    @GetMapping("/tasks")
    @ApiOperation(value = "Find all tasks", notes = " " ,response = Task.class)
    ResponseEntity<Iterable<TaskResponse>> getTasks(){
        Iterable<TaskResponse> taskList = new TaskResponse().toList(taskService.getTasks());
        return  !taskList.iterator().hasNext() ? new ResponseEntity(HttpStatus.NOT_FOUND) : new ResponseEntity<>(taskList, HttpStatus.OK);

    }

    @ApiOperation(value = "Find tasks by task`s id", notes = " " ,response = Task.class)
    @GetMapping("/tasks/{id}")
    ResponseEntity<TaskResponse> getById(@ApiParam(value = "ID value for the task you need to retrieve", required = true) @PathVariable("id") long id) throws NotFoundException {
        TaskResponse taskResponse = new TaskResponse(taskService.getById(id).get());
        return new ResponseEntity(taskResponse, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Add new task", notes = " " ,response = Task.class)
    @PostMapping("/tasks" )
    @CrossOrigin(origins = "http://localhost:4200" )
    ResponseEntity<Task> addTask(@RequestBody TaskRequest body ){
        Task task = new Task();
        body.toTask(task);
        TaskResponse taskResponse = new TaskResponse(taskService.addTask(task));

        for(Subtask s: task.getSubtasksList()){
            task.setId(taskResponse.getId());
            s.setTask(task);
            System.out.println(task.id + task.getTitle() + task.deadline);
            subtaskService.addSubtask(s);
        }
        return  new ResponseEntity(taskResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update by task`s id ", notes = " " ,response = TaskRequest.class)
    @PutMapping("/tasks/{id}")
    @CrossOrigin(origins = "http://localhost:4200" )
    ResponseEntity<Task> updateTask(@RequestBody TaskRequest body, @PathVariable long id ){
        Task task = new Task();
        body.toTask(task);
        TaskResponse taskResponse = new TaskResponse(taskService.updateTask(task,id));
        task.setId(taskResponse.getId());
        Task taskReturn = taskService.getById(taskResponse.getId()).get();
        subtaskService.deleteByTaskId(taskReturn.getId());
        for(Subtask s: task.getSubtasksList()){
            s.setTask(taskReturn);
            subtaskService.addSubtask(s);
        }
        return  new ResponseEntity(taskResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/tasks/{id}")
    @ApiOperation(value = "Delete by task`s id")
    @CrossOrigin(origins = "http://localhost:4200" )
    void deleteById(@ApiParam(value = "ID value for the task you need to remove", required = true) @PathVariable long id){
        subtaskService.deleteByTaskId(id);
        taskService.deleteTaskById(id);
    }

}
