package com.bennu.project.service;

import cl.bennu.commons.exception.NoDataException;
import com.bennu.project.mapper.JornadaMapper;
import com.bennu.project.model.Jornada;
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
class JornadaServiceTest {

    @Mock
    private JornadaMapper jornadaMapper;

    @InjectMocks
    private JornadaService jornadaService;

    @Test
    void getAll_devuelveLoDelMapper() {
        when(jornadaMapper.getAll()).thenReturn(List.of(new Jornada()));

        assertThat(jornadaService.getAll()).hasSize(1);
    }

    @Test
    void get_existente_laDevuelve() throws NoDataException {
        Jornada j = new Jornada();
        when(jornadaMapper.get(1L)).thenReturn(j);

        assertThat(jornadaService.get(1L)).isSameAs(j);
    }

    @Test
    void get_inexistente_lanzaNoData() {
        when(jornadaMapper.get(99L)).thenReturn(null);

        assertThrows(NoDataException.class, () -> jornadaService.get(99L));
    }

    @Test
    void save_sinId_inserta() {
        Jornada j = new Jornada();

        jornadaService.save(j);

        verify(jornadaMapper).insert(j);
    }

    @Test
    void save_conId_actualiza() {
        Jornada j = new Jornada();
        j.setId(1L);

        jornadaService.save(j);

        verify(jornadaMapper).update(j);
    }

    @Test
    void delete_llamaAlMapper() {
        jornadaService.delete(1L);

        verify(jornadaMapper).delete(1L);
    }
}