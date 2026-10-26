package com.bennu.project.model;

import cl.bennu.commons.domain.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Jornada extends BaseDomain implements Serializable {

    private Horario horario;
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalTime horaInicioDescanso;
    private LocalTime horaFinDescanso;
}