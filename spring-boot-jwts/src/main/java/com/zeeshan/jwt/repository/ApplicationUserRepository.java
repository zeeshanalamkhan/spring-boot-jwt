package com.zeeshan.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.jwt.entity.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

	public ApplicationUser findByUsername(String username);
	
}
