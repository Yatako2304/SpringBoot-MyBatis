package com.bennu.project.controller;

import com.bennu.project.constants.Constants;
import com.bennu.project.controller.base.BaseController;
import com.bennu.project.model.Horario;
import com.bennu.project.service.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constants.BASE_API, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HorarioController extends BaseController {

    private static final String ENDPOINT = "/horario";

    private final HorarioService horarioService;

    @GetMapping(ENDPOINT)
    public ResponseEntity<?> getAll() {
        return ok(horarioService.getAll());
    }

    @GetMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ok(horarioService.get(id));
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<?> create(@RequestBody Horario horario) {
        horario.setId(null);
        horarioService.save(horario);
        return ok(horario);
    }

    @PutMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Horario horario) {
        horario.setId(id);
        horarioService.save(horario);
        return ok(horario);
    }

    @DeleteMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        horarioService.delete(id);
        return ok();
    }
}