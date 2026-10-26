package com.bennu.project.service;

import cl.bennu.commons.exception.NoDataException;
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

    public Horario get(Long id) throws NoDataException {
        Horario horario = horarioMapper.get(id);
        if (horario == null) {
            throw new NoDataException("Horario " + id + " no existe");
        }
        return horario;
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