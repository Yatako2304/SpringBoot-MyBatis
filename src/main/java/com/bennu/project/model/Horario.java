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
public class Horario extends BaseDomain implements Serializable {

    private String descripcion;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Horario valueOf(Long id) {
        Horario o = new Horario();
        o.setId(id);
        return o;
    }
}