package com.conversor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = -3558822777222260735L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("fecha_registro")
    private String fechaRegistro;

    @JsonProperty("tipo_modificacion")
    private String tipoModificacion;

    @JsonProperty("usuario")
    private String usuario;
}
