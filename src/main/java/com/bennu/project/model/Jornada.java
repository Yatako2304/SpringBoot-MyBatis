package com.bennu.project.model;

import cl.bennu.commons.domain.base.BaseDomain;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Jornada extends BaseDomain implements Serializable {

    @NotNull(message = "El horario es obligatorio")
    private Horario horario;

    @NotNull(message = "El día de la semana es obligatorio")
    @Min(value = 1, message = "El día debe estar entre 1 (lunes) y 7 (domingo)")
    @Max(value = 7, message = "El día debe estar entre 1 (lunes) y 7 (domingo)")
    private Integer diaSemana;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    private LocalTime horaInicioDescanso;
    private LocalTime horaFinDescanso;
}