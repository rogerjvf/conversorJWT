package com.conversor.repo;

import com.conversor.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuditoriaRepository extends JpaRepository<Auditoria,Integer> {

}
