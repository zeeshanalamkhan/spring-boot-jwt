package com.zeeshan.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeeshan.jwt.entity.ApplicationUser;
import com.zeeshan.jwt.repository.ApplicationUserRepository;

@RestController
public class UserControlller {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptpasswordEncoder;

	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) {

		user.setPassword(bCryptpasswordEncoder.encode(user.getPassword()));
		applicationUserRepository.save(user);

	}

}
