package com.bennu.project.service;

import cl.bennu.commons.exception.NoDataException;
import com.bennu.project.mapper.HorarioMapper;
import com.bennu.project.model.Horario;
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
class HorarioServiceTest {

    @Mock
    private HorarioMapper horarioMapper;

    @InjectMocks
    private HorarioService horarioService;

    @Test
    void getAll_devuelveLoDelMapper() {
        when(horarioMapper.getAll()).thenReturn(List.of(new Horario()));

        assertThat(horarioService.getAll()).hasSize(1);
    }

    @Test
    void get_existente_loDevuelve() throws NoDataException {
        Horario h = new Horario();
        when(horarioMapper.get(1L)).thenReturn(h);

        assertThat(horarioService.get(1L)).isSameAs(h);
    }

    @Test
    void get_inexistente_lanzaNoData() {
        when(horarioMapper.get(99L)).thenReturn(null);

        assertThrows(NoDataException.class, () -> horarioService.get(99L));
    }

    @Test
    void save_sinId_inserta() {
        Horario h = new Horario();

        horarioService.save(h);

        verify(horarioMapper).insert(h);
    }

    @Test
    void save_conId_actualiza() {
        Horario h = Horario.valueOf(1L);

        horarioService.save(h);

        verify(horarioMapper).update(h);
    }

    @Test
    void delete_llamaAlMapper() {
        horarioService.delete(1L);

        verify(horarioMapper).delete(1L);
    }
}