package com.bennu.project.service;

import cl.bennu.commons.exception.NoDataException;
import com.bennu.project.mapper.EmpleadoMapper;
import com.bennu.project.model.Empleado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoMapper empleadoMapper;

    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    void getAll_devuelveLoDelMapper() {
        when(empleadoMapper.getAll()).thenReturn(List.of(new Empleado()));

        assertThat(empleadoService.getAll()).hasSize(1);
    }

    @Test
    void get_existente_loDevuelve() throws NoDataException {
        Empleado e = new Empleado();
        when(empleadoMapper.get(1L)).thenReturn(e);

        assertThat(empleadoService.get(1L)).isSameAs(e);
    }

    @Test
    void get_inexistente_lanzaNoData() {
        when(empleadoMapper.get(99L)).thenReturn(null);

        assertThrows(NoDataException.class, () -> empleadoService.get(99L));
    }

    @Test
    void save_sinId_inserta() {
        Empleado e = new Empleado();

        empleadoService.save(e);

        verify(empleadoMapper).insert(e);
    }

    @Test
    void save_conId_actualiza() {
        Empleado e = Empleado.valueOf(1L);

        empleadoService.save(e);

        verify(empleadoMapper).update(e);
    }

    @Test
    void delete_llamaAlMapper() {
        empleadoService.delete(1L);

        verify(empleadoMapper).delete(1L);
    }
}