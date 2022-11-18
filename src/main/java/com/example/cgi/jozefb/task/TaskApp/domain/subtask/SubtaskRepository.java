package com.example.cgi.jozefb.task.TaskApp.domain.subtask;

import com.example.cgi.jozefb.task.TaskApp.domain.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

    List<Subtask> findByTaskId(Long taskId);

    @Modifying
    @Query(value = "DELETE FROM Subtask e WHERE e.task.id = ?1")
    void deleteByTask_Id(long taskId);

    @Override
    long count();
}
