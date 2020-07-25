package com.zeeshan.jwt.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeeshan.jwt.entity.ApplicationUser;

import static com.zeeshan.jwt.constants.SecurityConstants.SECRET;
import static com.zeeshan.jwt.constants.SecurityConstants.EXPIRATION_TIME;
import static com.zeeshan.jwt.constants.SecurityConstants.HEADER_STRING;
import static com.zeeshan.jwt.constants.SecurityConstants.TOKEN_PREFIX;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {

			ApplicationUser cred = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(cred.getUsername(), cred.getPassword(), new ArrayList<>()));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

	}

	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = JWT.create()
									.withSubject(((User) authResult.getPrincipal()).getUsername())
									.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
									.sign(HMAC512(SECRET.getBytes()));
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

	}

}
