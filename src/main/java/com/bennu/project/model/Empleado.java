package com.bennu.project.model;

import cl.bennu.commons.domain.base.BaseDomain;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Empleado extends BaseDomain implements Serializable {

    private String nombre;
    private String apellido;
    private String cargo;
    private Horario horario;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Empleado valueOf(Long id) {
        Empleado o = new Empleado();
        o.setId(id);
        return o;
    }
}