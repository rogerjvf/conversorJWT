package com.conversor.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RestAuthServiceImpl {

	public boolean hasAccess(String path) {
		boolean rpta = false;

		String metodoRol = "";

		// /listar
		switch (path) {
		case "admin":
			metodoRol = "ADMIN";
			break;

		case "all":
			metodoRol = "ADMIN,USER,DBA";
			break;
		}
		
		String metodoRoles[] = metodoRol.split(",");
		
		Authentication autho = SecurityContextHolder.getContext().getAuthentication();
		if(!(autho instanceof AnonymousAuthenticationToken)) {
			log.info("name: "+autho.getName());
			
			for (GrantedAuthority auth : autho.getAuthorities()) {
				String rolUser = auth.getAuthority();
				log.info("rolUser: "+rolUser);
				
				for (String rolMet : metodoRoles) { 
					if (rolUser.equalsIgnoreCase(rolMet)) {
						rpta = true;
					}
				}
			}
		}

		return rpta;
	}
}
