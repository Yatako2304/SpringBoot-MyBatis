package com.bennu.project.mapper;

import cl.bennu.commons.mapper.base.BaseMapper;
import com.bennu.project.model.Empleado;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpleadoMapper extends BaseMapper<Empleado> {
}