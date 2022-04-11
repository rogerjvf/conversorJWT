package com.conversor.service;

import com.conversor.dto.AuditoriaDTO;
import com.conversor.model.Auditoria;

public interface IAuditoriaService {

    void registrarAuditoria(AuditoriaDTO auditoriaDTO);

    String getFechaActual();

    AuditoriaDTO getAuditoriaDTO(String tipoModificacion,String usuario);
}
