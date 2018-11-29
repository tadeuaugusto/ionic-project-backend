package com.tadeu.estudo.springbootionic.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tadeu.estudo.springbootionic.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			CredenciaisDTO dto = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					dto.getEmail(), dto.getSenha(), new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(authToken);
			
			return auth;
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, 
											HttpServletResponse resp, 
											FilterChain chain,
											Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String username = ((UserSpringScy)auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		resp.addHeader("Authorization", "Bearer " + token);
		resp.addHeader("access-control-expose-headers", "Authorization");
	}
}
