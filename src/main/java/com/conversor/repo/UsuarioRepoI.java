package com.conversor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conversor.model.Usuario;


public interface UsuarioRepoI  extends JpaRepository<Usuario, Integer> {


	Usuario findOneByUsername(String username);
}
