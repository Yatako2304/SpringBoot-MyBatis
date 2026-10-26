package com.bennu.project.model;

import cl.bennu.commons.domain.base.BaseDomain;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Empleado extends BaseDomain implements Serializable {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    private String cargo;

    @NotNull(message = "El horario es obligatorio")
    private Horario horario;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Empleado valueOf(Long id) {
        Empleado o = new Empleado();
        o.setId(id);
        return o;
    }
}