package com.zeeshan.jwt.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zeeshan.jwt.entity.User;
import com.zeeshan.jwt.exception.CustomException;
import com.zeeshan.jwt.repository.UserRepository;
import com.zeeshan.jwt.security.JwtTokenProvider;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public String signin(String username, String password) {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());

		} catch (AuthenticationException ex) {

			throw new CustomException("Invalid Username/Password supplied.", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	public String signup(User user) {

		if (!userRepository.existsByUsername(user.getUsername())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
		} else {
			throw new CustomException("Username already exists.", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	public void deleteUser(String username) {

		userRepository.deleteByUsername(username);
	}

	public User search(String username) {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new CustomException("The User doesn't exists", HttpStatus.NOT_FOUND);
		}
		return user;
	}

	public User whoAmI(HttpServletRequest request) {

		return userRepository.findByUsername(jwtTokenProvider.getUserName(jwtTokenProvider.resolveToken(request)));

	}

	public String refresh(String username) {

		return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());

	}

}
