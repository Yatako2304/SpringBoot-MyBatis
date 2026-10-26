package com.bennu.project.service;

import com.bennu.project.mapper.EmpleadoMapper;
import com.bennu.project.model.Empleado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoMapper empleadoMapper;

    public List<Empleado> getAll() {
        return empleadoMapper.getAll();
    }

    public Empleado get(Long id) {
        return empleadoMapper.get(id);
    }

    @Transactional
    public void save(Empleado empleado) {
        if (empleado.getId() == null) {
            empleadoMapper.insert(empleado);
        } else {
            empleadoMapper.update(empleado);
        }
    }

    @Transactional
    public void delete(Long id) {
        empleadoMapper.delete(id);
    }
}