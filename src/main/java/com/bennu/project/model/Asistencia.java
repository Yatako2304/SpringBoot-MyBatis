package com.bennu.project.model;

import cl.bennu.commons.domain.base.BaseDomain;
import com.bennu.project.enums.EstadoAsistenciaEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Asistencia extends BaseDomain implements Serializable {

    private Empleado empleado;
    private LocalDate fecha;
    private EstadoAsistenciaEnum estado;
    private LocalTime horaEntrada;
    private LocalTime horaInicioDescanso;
    private LocalTime horaFinDescanso;
    private LocalTime horaSalida;
    private Integer minutosRetraso;
}