package com.zeeshan.jwt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public boolean existsByUsername(String username);

	public User findByUsername(String username);

	@Transactional
	public void deleteByUsername(String username);
}
