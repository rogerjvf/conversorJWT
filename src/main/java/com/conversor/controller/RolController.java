package com.conversor.controller;

import com.conversor.dto.RolDTO;
import com.conversor.service.IRolService;
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
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolController {

    @Autowired
    private IRolService iRolService;

    @PostMapping("/create")
    public ResponseEntity<Void> registerRol(@RequestBody RolDTO rolDTO){
        iRolService.registrarRol(rolDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
