package com.conversor.service.impl;

import com.conversor.dto.AuditoriaDTO;
import com.conversor.model.Auditoria;
import com.conversor.repo.IAuditoriaRepository;
import com.conversor.service.IAuditoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Log4j2
@Service
@RequiredArgsConstructor
public class IAuditoriaServiceImpl implements IAuditoriaService {

    @Autowired
    private IAuditoriaRepository iAuditoriaRepository;

    @Override
    public void registrarAuditoria(AuditoriaDTO auditoriaDTO) {
        Auditoria auditoria = new Auditoria();
        auditoria.setFechaRegistro(auditoriaDTO.getFechaRegistro());
        auditoria.setTipoModificacion(auditoriaDTO.getTipoModificacion());
        auditoria.setUsuario(auditoriaDTO.getUsuario());
        iAuditoriaRepository.save(auditoria);
    }

    @Override
    public String getFechaActual() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public AuditoriaDTO getAuditoriaDTO(String tipoModificacion,String usuario) {
        AuditoriaDTO auditoriaDTO = new AuditoriaDTO();
        auditoriaDTO.setFechaRegistro(getFechaActual());
        auditoriaDTO.setTipoModificacion(tipoModificacion);
        auditoriaDTO.setUsuario(usuario);
        return auditoriaDTO;
    }
}
