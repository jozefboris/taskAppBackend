package com.example.cgi.jozefb.task.TaskApp.domain.subtask;

import com.example.cgi.jozefb.task.TaskApp.domain.Task.Task;
import com.example.cgi.jozefb.task.TaskApp.domain.Task.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubtaskController {
    SubtaskService subtaskService;
    TaskService taskService;

    public SubtaskController(SubtaskService subtaskService, TaskService taskService) {
        this.subtaskService = subtaskService;
        this.taskService = taskService;
    }

    @ApiOperation(value = "Add new subtask", notes = " " ,response = SubtaskResponse.class)
    @PostMapping("/subtasks" )
    @CrossOrigin(origins = "http://localhost:4200" )
    ResponseEntity<SubtaskResponse> addSubtask(@RequestBody SubtaskRequest body){
        Subtask subtask = new Subtask();
        body.toSubtask(subtask, taskService);
        SubtaskResponse subtaskResponse = new SubtaskResponse(subtaskService.addSubtask(subtask));
        return  new ResponseEntity(subtaskResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update by subtask`s id ", notes = " " ,response = Subtask.class)
    @PutMapping("/subtasks/{id}")
    @CrossOrigin(origins = "http://localhost:4200" )
    ResponseEntity<SubtaskResponse> updateSubtask(@ApiParam(value = "ID value for the subtask you need to update", required = true) @RequestBody Subtask subtask, @PathVariable("id") long id){
        SubtaskResponse subtaskResponse = new SubtaskResponse(subtaskService.updateSubtask(subtask, id));
        return new ResponseEntity(subtaskResponse, HttpStatus.ACCEPTED);
    }

   @DeleteMapping("/subtasks/{id}")
    @ApiOperation(value = "Delete by subtask`s id")
    @CrossOrigin(origins = "http://localhost:4200" )
    void deleteById(@ApiParam(value = "ID value for the subtask you need to remove", required = true) @PathVariable long id){
        subtaskService.deleteSubtaskById(id);
    }

    @ApiOperation(value = "Find subtask by task`s id", notes = " " ,response = SubtaskResponse.class)
    @GetMapping("/subtasks")
    ResponseEntity<List<SubtaskResponse>> getByTaskId(@ApiParam(value = "ID task value for the subtask you need to retrieve", required = true) @RequestParam("taskId") long id){
        List<SubtaskResponse> subtaskList = new SubtaskResponse().toList( subtaskService.getSubtaskByIdTask(id));
        return  subtaskList.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND) : new ResponseEntity<>(subtaskList, HttpStatus.OK);
    }

    @DeleteMapping("/subtasks/")
    @ApiOperation(value = "Delete by subtask`s id")
    @CrossOrigin(origins = "http://localhost:4200" )
    void deleteByTaskId(@ApiParam(value = "ID value for the subtask you need to remove", required = true) @RequestParam("taskId") long taskId){
        subtaskService.deleteByTaskId(taskId);
    }



}
