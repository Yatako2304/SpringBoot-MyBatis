package com.bennu.project.mapper;

import cl.bennu.commons.mapper.base.BaseMapper;
import com.bennu.project.model.Jornada;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JornadaMapper extends BaseMapper<Jornada> {

    Jornada getByHorarioAndDia(@Param("horarioId") Long horarioId, @Param("diaSemana") Integer diaSemana);
}
