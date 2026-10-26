package com.bennu.project.service;

import cl.bennu.commons.exception.NoDataException;
import com.bennu.project.enums.EstadoAsistenciaEnum;
import com.bennu.project.mapper.AsistenciaMapper;
import com.bennu.project.mapper.EmpleadoMapper;
import com.bennu.project.mapper.JornadaMapper;
import com.bennu.project.model.Asistencia;
import com.bennu.project.model.Empleado;
import com.bennu.project.model.Horario;
import com.bennu.project.model.Jornada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsistenciaServiceTest {

    @Mock
    private AsistenciaMapper asistenciaMapper;
    @Mock
    private EmpleadoMapper empleadoMapper;
    @Mock
    private JornadaMapper jornadaMapper;

    @InjectMocks
    private AsistenciaService asistenciaService;

    private static final LocalDate MARTES = LocalDate.of(2026, 10, 20);
    private static final LocalDate SABADO = LocalDate.of(2026, 10, 24);

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = Empleado.valueOf(1L);
        empleado.setHorario(Horario.valueOf(1L));
    }

    private Asistencia asistencia(LocalDate fecha, LocalTime horaEntrada) {
        Asistencia a = new Asistencia();
        a.setEmpleado(Empleado.valueOf(1L));
        a.setFecha(fecha);
        a.setHoraEntrada(horaEntrada);
        return a;
    }

    private Jornada jornadaDesde(LocalTime horaInicio) {
        Jornada j = new Jornada();
        j.setHoraInicio(horaInicio);
        return j;
    }

    @Test
    void getAll_devuelveLoDelMapper() {
        when(asistenciaMapper.getAll()).thenReturn(List.of(new Asistencia()));

        assertThat(asistenciaService.getAll()).hasSize(1);
    }

    @Test
    void get_existente_laDevuelve() throws NoDataException {
        Asistencia a = new Asistencia();
        when(asistenciaMapper.get(1L)).thenReturn(a);

        assertThat(asistenciaService.get(1L)).isSameAs(a);
    }

    @Test
    void get_inexistente_lanzaNoData() {
        when(asistenciaMapper.get(99L)).thenReturn(null);

        assertThrows(NoDataException.class, () -> asistenciaService.get(99L));
    }

    @Test
    void save_llegaTarde_calculaRetrasoYPoneEstadoPorDefecto() throws NoDataException {
        Asistencia a = asistencia(MARTES, LocalTime.of(8, 20));
        when(empleadoMapper.get(1L)).thenReturn(empleado);
        when(jornadaMapper.getByHorarioAndDia(1L, 2)).thenReturn(jornadaDesde(LocalTime.of(8, 0)));

        asistenciaService.save(a);

        assertThat(a.getMinutosRetraso()).isEqualTo(20);
        assertThat(a.getEstado()).isEqualTo(EstadoAsistenciaEnum.PRESENTE);
        verify(asistenciaMapper).insert(a);
    }

    @Test
    void save_llegaTemprano_retrasoCero() throws NoDataException {
        Asistencia a = asistencia(MARTES, LocalTime.of(7, 50));
        when(empleadoMapper.get(1L)).thenReturn(empleado);
        when(jornadaMapper.getByHorarioAndDia(1L, 2)).thenReturn(jornadaDesde(LocalTime.of(8, 0)));

        asistenciaService.save(a);

        assertThat(a.getMinutosRetraso()).isZero();
    }

    @Test
    void save_diaSinJornada_retrasoCero() throws NoDataException {
        Asistencia a = asistencia(SABADO, LocalTime.of(10, 0));
        when(empleadoMapper.get(1L)).thenReturn(empleado);
        when(jornadaMapper.getByHorarioAndDia(1L, 6)).thenReturn(null);

        asistenciaService.save(a);

        assertThat(a.getMinutosRetraso()).isZero();
    }

    @Test
    void save_sinHoraEntrada_retrasoCeroYNoConsultaEmpleado() throws NoDataException {
        Asistencia a = asistencia(MARTES, null);
        a.setEstado(EstadoAsistenciaEnum.AUSENTE);

        asistenciaService.save(a);

        assertThat(a.getMinutosRetraso()).isZero();
        assertThat(a.getEstado()).isEqualTo(EstadoAsistenciaEnum.AUSENTE);
        verifyNoInteractions(empleadoMapper);
    }

    @Test
    void save_empleadoInexistente_lanzaNoDataYNoGuarda() {
        Asistencia a = asistencia(MARTES, LocalTime.of(8, 0));
        when(empleadoMapper.get(1L)).thenReturn(null);

        assertThrows(NoDataException.class, () -> asistenciaService.save(a));
        verify(asistenciaMapper, never()).insert(any());
    }

    @Test
    void save_conId_actualiza() throws NoDataException {
        Asistencia a = asistencia(MARTES, null);
        a.setId(5L);

        asistenciaService.save(a);

        verify(asistenciaMapper).update(a);
        verify(asistenciaMapper, never()).insert(any());
    }

    @Test
    void delete_llamaAlMapper() {
        asistenciaService.delete(1L);

        verify(asistenciaMapper).delete(1L);
    }
}