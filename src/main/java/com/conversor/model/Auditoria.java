package com.conversor.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1578248342149700353L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="fecha_registro")
    private String fechaRegistro;

    @Column(name="tipo_modificacion")
    private String tipoModificacion;

    @Column(name="usuario")
    private String usuario;
}
