package com.conversor.service.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.conversor.model.Usuario;
import com.conversor.repo.UsuarioRepoI;

@Log4j2
@Service
public class UsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepoI repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("UsuarioServiceImpl metodo loadUserByUsername");
		
		Usuario usuario = repo.findOneByUsername(username);
		
		usuario.getRoles().forEach((a)->log.info(a.getDescripcion()));
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		});

		UserDetails userDetails = new User(usuario.getUsername(), usuario.getPassword(), roles);
		return userDetails;

	}
}
