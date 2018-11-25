package com.tadeu.estudo.springbootionic.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tadeu.estudo.springbootionic.security.UserSpringScy;

public class UserService {

	public static UserSpringScy usuarioLogado() {
		try {
			
			return  (UserSpringScy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {

			return null;
		}
	}
}
