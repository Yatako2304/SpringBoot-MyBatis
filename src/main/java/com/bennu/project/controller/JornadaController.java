package com.bennu.project.controller;

import com.bennu.project.constants.Constants;
import com.bennu.project.controller.base.BaseController;
import com.bennu.project.model.Jornada;
import com.bennu.project.service.JornadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constants.BASE_API, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class JornadaController extends BaseController {

    private static final String ENDPOINT = "/jornada";

    private final JornadaService jornadaService;

    @GetMapping(ENDPOINT)
    public ResponseEntity<?> getAll() {
        return ok(jornadaService.getAll());
    }

    @GetMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ok(jornadaService.get(id));
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<?> create(@RequestBody Jornada jornada) {
        jornada.setId(null);
        jornadaService.save(jornada);
        return ok(jornada);
    }

    @PutMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Jornada jornada) {
        jornada.setId(id);
        jornadaService.save(jornada);
        return ok(jornada);
    }

    @DeleteMapping(ENDPOINT + "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        jornadaService.delete(id);
        return ok();
    }
}