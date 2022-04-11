package com.conversor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {

    @JsonProperty("id_rol")
    private String idRol;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("nombre")
    private String nombre;

}
