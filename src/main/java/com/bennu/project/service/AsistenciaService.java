package com.bennu.project.service;

import com.bennu.project.enums.EstadoAsistenciaEnum;
import com.bennu.project.mapper.AsistenciaMapper;
import com.bennu.project.mapper.EmpleadoMapper;
import com.bennu.project.mapper.JornadaMapper;
import com.bennu.project.model.Asistencia;
import com.bennu.project.model.Empleado;
import com.bennu.project.model.Jornada;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaMapper asistenciaMapper;
    private final EmpleadoMapper empleadoMapper;
    private final JornadaMapper jornadaMapper;

    public List<Asistencia> getAll() {
        return asistenciaMapper.getAll();
    }

    public Asistencia get(Long id) {
        return asistenciaMapper.get(id);
    }

    @Transactional
    public void save(Asistencia asistencia) {
        if (asistencia.getEstado() == null) {
            asistencia.setEstado(EstadoAsistenciaEnum.PRESENTE);
        }
        asistencia.setMinutosRetraso(calcularRetraso(asistencia));

        if (asistencia.getId() == null) {
            asistenciaMapper.insert(asistencia);
        } else {
            asistenciaMapper.update(asistencia);
        }
    }

    @Transactional
    public void delete(Long id) {
        asistenciaMapper.delete(id);
    }

    private int calcularRetraso(Asistencia asistencia) {
        if (asistencia.getHoraEntrada() == null) {
            return 0;
        }
        Empleado empleado = empleadoMapper.get(asistencia.getEmpleado().getId());
        int diaSemana = asistencia.getFecha().getDayOfWeek().getValue(); // ISO: 1=lunes
        Jornada jornada = jornadaMapper.getByHorarioAndDia(empleado.getHorario().getId(), diaSemana);

        if (jornada == null) {
            return 0; // ese día no le toca trabajar
        }
        long minutos = Duration.between(jornada.getHoraInicio(), asistencia.getHoraEntrada()).toMinutes();
        return (int) Math.max(minutos, 0);
    }
}