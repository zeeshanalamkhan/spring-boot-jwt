package com.zeeshan.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.jwt.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
