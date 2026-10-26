package com.bennu.project.service;

import com.bennu.project.mapper.HorarioMapper;

import com.bennu.project.model.Horario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioMapper horarioMapper;

    public List<Horario> getAll() {
        return horarioMapper.getAll();
    }

    public Horario get(Long id) {
        return horarioMapper.get(id);
    }

    @Transactional
    public void save(Horario horario) {
        if (horario.getId() == null) {
            horarioMapper.insert(horario);
        } else {
            horarioMapper.update(horario);
        }
    }

    @Transactional
    public void delete(Long id) {
        horarioMapper.delete(id);
    }

}
