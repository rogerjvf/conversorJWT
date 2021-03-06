package com.conversor.controller;

import com.conversor.dto.AuditoriaDTO;
import com.conversor.service.IAuditoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    @Autowired
    private IAuditoriaService iAuditoriaService;

    @PostMapping("/create")
    public ResponseEntity<Void> registerAuditoria(@RequestBody AuditoriaDTO auditoriaDTO){
        iAuditoriaService.registrarAuditoria(auditoriaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
