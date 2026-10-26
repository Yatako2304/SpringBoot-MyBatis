package com.bennu.project.controller;

import cl.bennu.commons.exception.NoDataException;
import com.bennu.project.constants.Constants;
import com.bennu.project.controller.base.BaseController;
import com.bennu.project.model.Empleado;
import com.bennu.project.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constants.BASE_API, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmpleadoController extends BaseController {

    private static final String ENDPOINT = "/empleado";

    private final EmpleadoService empleadoService;

    @GetMapping(ENDPOINT)
    public ResponseEntity<?> getAll() {
        return ok(empleadoService.getAll());
    }

    @GetMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) throws NoDataException {
        return ok(empleadoService.get(id));
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<?> create(@Valid @RequestBody Empleado empleado) {
        empleado.setId(null);
        empleadoService.save(empleado);
        return ok(empleado);
    }

    @PutMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Empleado empleado) {
        empleado.setId(id);
        empleadoService.save(empleado);
        return ok(empleado);
    }

    @DeleteMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        empleadoService.delete(id);
        return ok();
    }
}