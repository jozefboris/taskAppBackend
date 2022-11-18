package com.example.cgi.jozefb.task.TaskApp.domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
}
