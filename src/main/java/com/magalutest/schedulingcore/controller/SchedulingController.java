package com.magalutest.schedulingcore.controller;

import com.magalutest.schedulingcore.controller.dto.Response;
import com.magalutest.schedulingcore.controller.dto.SchedulingRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/schedules")
public class SchedulingController extends AbstractController{

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody @Valid final SchedulingRequestDTO requestDTO) {
        return super.build(HttpStatus.OK, null);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<Response> find(@PathVariable(value = "uuid") final UUID uuid) {
        return super.build(HttpStatus.OK, null);
    }

    @GetMapping(path = "/{uuid}/status")
    public ResponseEntity<Response> findStatus(@PathVariable(value = "uuid") final UUID uuid) {
        return super.build(HttpStatus.CREATED, null);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<Response> delete(@PathVariable(value = "uuid") final UUID uuid) {
        return super.build(HttpStatus.OK, null);
    }
}
