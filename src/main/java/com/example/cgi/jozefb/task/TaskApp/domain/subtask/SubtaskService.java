package com.example.cgi.jozefb.task.TaskApp.domain.subtask;

import java.util.List;
import java.util.Optional;

public interface SubtaskService {
    Subtask addSubtask(Subtask subtask);
    Subtask updateSubtask(Subtask subtask, long id);
    List<Subtask> getSubtaskByIdTask(long taskId);
    void deleteSubtaskById(long id);
    void deleteByTaskId(long taskId);
    Optional<Subtask> getSubtaskById(long id);

}
