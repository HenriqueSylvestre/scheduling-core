package com.magalutest.schedulingcore.controller;

import com.magalutest.schedulingcore.business.StatusBusiness;
import com.magalutest.schedulingcore.controller.dto.Response;
import com.magalutest.schedulingcore.controller.dto.StatusRequestDTO;
import com.magalutest.schedulingcore.controller.mapper.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/status")
public class StatusController extends AbstractController{

    private final StatusBusiness statusBusiness;
    private final StatusMapper statusMapper;

    @Autowired
    public StatusController(final StatusBusiness statusBusiness, final StatusMapper statusMapper) {
        this.statusBusiness = statusBusiness;
        this.statusMapper = statusMapper;
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody @Valid final StatusRequestDTO requestDTO) {
        final var status = statusBusiness.create(statusMapper.mapper(requestDTO));
        return super.build(HttpStatus.CREATED, statusMapper.mapper(status));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> find(@PathVariable(value = "id") final long id) {
        final var status = statusBusiness.findById(id);
        return super.build(HttpStatus.OK, statusMapper.mapper(status));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable(value = "id") final long id) {
        statusBusiness.deleteById(id);
        return super.build(HttpStatus.NO_CONTENT);
    }
}
