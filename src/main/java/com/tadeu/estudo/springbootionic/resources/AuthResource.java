package com.tadeu.estudo.springbootionic.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tadeu.estudo.springbootionic.dto.EmailDTO;
import com.tadeu.estudo.springbootionic.security.JWTUtil;
import com.tadeu.estudo.springbootionic.security.UserSpringScy;
import com.tadeu.estudo.springbootionic.services.AuthService;
import com.tadeu.estudo.springbootionic.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse res) {
		UserSpringScy user = UserService.usuarioLogado();
		String token = jwtUtil.generateToken(user.getUsername());
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("access-control-expose-headers", "Authorization");
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/forgot", method=RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO dto) {
		authService.sendNewPassword(dto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
