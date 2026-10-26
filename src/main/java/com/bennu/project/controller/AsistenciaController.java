package com.bennu.project.controller;

import com.bennu.project.constants.Constants;
import com.bennu.project.controller.base.BaseController;
import com.bennu.project.model.Asistencia;
import com.bennu.project.service.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constants.BASE_API, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AsistenciaController extends BaseController {

    private static final String ENDPOINT = "/asistencia";

    private final AsistenciaService asistenciaService;

    @GetMapping(ENDPOINT)
    public ResponseEntity<?> getAll() {
        return ok(asistenciaService.getAll());
    }

    @GetMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ok(asistenciaService.get(id));
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<?> create(@RequestBody Asistencia asistencia) {
        asistencia.setId(null);
        asistenciaService.save(asistencia);
        return ok(asistencia);
    }

    @PutMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        asistencia.setId(id);
        asistenciaService.save(asistencia);
        return ok(asistencia);
    }

    @DeleteMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        asistenciaService.delete(id);
        return ok();
    }
}