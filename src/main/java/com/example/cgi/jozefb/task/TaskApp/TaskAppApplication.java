package com.example.cgi.jozefb.task.TaskApp;

import com.example.cgi.jozefb.task.TaskApp.domain.Task.Task;
import com.example.cgi.jozefb.task.TaskApp.domain.Task.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TaskAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskAppApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TaskRepository repository){
		return args -> {

			java.util.Date date=new java.util.Date();
			//repository.save(new Task("Task1", "Watering flowers", date));
			//repository.save(new Task("Task2", "Angular", date));
		};
	}

}
