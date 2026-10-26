package com.bennu.project.service;

import cl.bennu.commons.exception.NoDataException;
import com.bennu.project.mapper.JornadaMapper;
import com.bennu.project.model.Jornada;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JornadaService {

    private final JornadaMapper jornadaMapper;

    public List<Jornada> getAll() {
        return jornadaMapper.getAll();
    }

    public Jornada get(Long id) throws NoDataException {
        Jornada jornada = jornadaMapper.get(id);
        if (jornada == null) {
            throw new NoDataException("Jornada " + id + " no existe");
        }
        return jornada;
    }

    @Transactional
    public void save(Jornada jornada) {
        if (jornada.getId() == null) {
            jornadaMapper.insert(jornada);
        } else {
            jornadaMapper.update(jornada);
        }
    }

    @Transactional
    public void delete(Long id) {
        jornadaMapper.delete(id);
    }
}