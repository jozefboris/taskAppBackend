package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static com.example.cgi.jozefb.task.TaskApp.prototype.TaskPrototype.aTask;
import static com.example.cgi.jozefb.task.TaskApp.prototype.TaskPrototype.aTaskNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getTasks() throws Exception {
        taskService.addTask( aTask());
        String tasksJson = mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Task> returnedTask = objectMapper.readValue(tasksJson, new TypeReference<List<Task>>(){});

        Assert.assertEquals(returnedTask.size(), 1);

    }

    @Test
    void getById() throws Exception {
        Task addedTask = taskService.addTask(aTask());
        String taskS = mockMvc.perform(get("/api/tasks/" + addedTask.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Task returnedTask = objectMapper.readValue(taskS, Task.class);
        Assert.assertEquals(returnedTask.getId(), addedTask.getId());
        Assert.assertEquals(returnedTask.getTitle(), addedTask.getTitle());
        Assert.assertEquals(returnedTask.getDescription(), addedTask.getDescription());
    }

    @Test
    void addTask() throws Exception {

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aTask())))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void updateTask() throws Exception {
        Task addedTask = taskService.addTask(aTask());
       String taskS = mockMvc.perform(put("/api/tasks/" + addedTask.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aTaskNull())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Task returnedTask = objectMapper.readValue(taskS, Task.class);
        Assert.assertEquals(null, returnedTask.getDescription());
        Assert.assertEquals(null, returnedTask.getDeadline());
        Assert.assertEquals(aTaskNull().getTitle(), returnedTask.getTitle());

    }

    @Test
    void deleteById() throws Exception {
        Task addedTask = taskService.addTask(aTask());
        mockMvc.perform(delete("/api/tasks/" + addedTask.getId()))
                .andExpect(status().isOk());
    }
}