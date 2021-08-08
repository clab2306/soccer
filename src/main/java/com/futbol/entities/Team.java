package com.futbol.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private String id;

    @NonNull
    private String nombre;

    private String ciudad;

    private String propietario;

    @JsonProperty("capacidad_estadio")
    private Integer capacidadEstadio;

    private Integer division;

    private String competicion;

    @JsonProperty("numero_jugadores")
    private Integer numeroJugadores;

    @JsonProperty("fecha_creacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;
}
