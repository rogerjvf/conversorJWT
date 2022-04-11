package com.conversor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonedaDTO implements Serializable {


    @JsonProperty("id")
    private Integer id;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("simbolo")
    private String simbolo;

    @JsonProperty("valor")
    private BigDecimal valor;

    @JsonProperty("monedaBase")
    private boolean monedaBase;
}
