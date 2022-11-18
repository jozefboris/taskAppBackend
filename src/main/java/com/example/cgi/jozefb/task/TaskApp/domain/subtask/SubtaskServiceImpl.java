package com.example.cgi.jozefb.task.TaskApp.domain.subtask;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubtaskServiceImpl implements SubtaskService {

    SubtaskRepository subtaskRepository;

    public SubtaskServiceImpl(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public Subtask addSubtask(Subtask subtask) {
        return subtaskRepository.save(subtask);
    }

    @Override
    public Subtask updateSubtask(Subtask subtask, long id) {
        return subtaskRepository.findById(id).map(taskItem -> {
            taskItem.setTitle(subtask.getTitle());
            return subtaskRepository.save(taskItem);
        }).orElseGet(() ->{
            return subtaskRepository.save(subtask);
        });
    }

    @Override
    public List<Subtask> getSubtaskByIdTask(long taskId) {
        return subtaskRepository.findByTaskId(taskId);
    }

    @Override
    public void deleteSubtaskById(long id) {
        subtaskRepository.deleteById(id);
    }

    @Override
    public void deleteByTaskId(long taskId) {
        subtaskRepository.deleteByTask_Id(taskId);
    }

    @Override
    public Optional<Subtask> getSubtaskById(long id) {
        return subtaskRepository.findById(id);
    }
}
