package com.conversor.service.impl;

import com.conversor.dto.RolDTO;
import com.conversor.model.Rol;
import com.conversor.repo.IRolRepository;
import com.conversor.service.IRolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class IRolServiceImpl implements IRolService {

    @Autowired
    IRolRepository iRolRepository;

    @Override
    public void registrarRol(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setNombre(rolDTO.getNombre());
        rol.setDescripcion(rolDTO.getDescripcion());
        iRolRepository.save(rol);
    }
}
