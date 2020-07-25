package com.zeeshan.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeeshan.jwt.entity.Task;
import com.zeeshan.jwt.repository.TaskRepository;

@RestController
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@PostMapping("/task")
	public void addTask(@RequestBody Task task) {

		taskRepository.save(task);

	}

	@GetMapping("/task/{id}")
	public Task getTask(@PathVariable Long id) {

		return taskRepository.findById(id).get();

	}

	@GetMapping("/task/all")
	public List<Task> allTasks() {

		return taskRepository.findAll();

	}

	@PutMapping("/task/{id}")
	public void editTask(@RequestBody Task task, @PathVariable Long id) {

		Task newTask = taskRepository.findById(id).get();
		newTask.setDescription(task.getDescription());
		taskRepository.save(newTask);

	}

	@DeleteMapping("/task/{id}")
	public void deleteTask(@PathVariable Long id) {

		taskRepository.deleteById(id);

	}
}
