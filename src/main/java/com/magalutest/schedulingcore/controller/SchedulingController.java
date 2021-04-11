package com.magalutest.schedulingcore.controller;

import com.magalutest.schedulingcore.business.SchedulingBusiness;
import com.magalutest.schedulingcore.controller.dto.Response;
import com.magalutest.schedulingcore.controller.dto.SchedulingRequestDTO;
import com.magalutest.schedulingcore.controller.mapper.SchedulingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/schedules")
public class SchedulingController extends AbstractController{

    private final SchedulingMapper schedulingMapper;
    private final SchedulingBusiness schedulingBusiness;

    @Autowired
    public SchedulingController(final SchedulingMapper schedulingMapper, final SchedulingBusiness schedulingBusiness) {
        this.schedulingMapper = schedulingMapper;
        this.schedulingBusiness = schedulingBusiness;
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody @Valid final SchedulingRequestDTO requestDTO) {
        var scheduling = schedulingBusiness.create(schedulingMapper.mapper(requestDTO));
        return super.build(HttpStatus.OK, schedulingMapper.mapper(scheduling));
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<Response> find(@PathVariable(value = "uuid") final UUID uuid) {
        var scheduling = schedulingBusiness.findByUuid(uuid);
        return super.build(HttpStatus.OK, schedulingMapper.mapper(scheduling));
    }

    @GetMapping(path = "/{uuid}/status")
    public ResponseEntity<Response> findStatus(@PathVariable(value = "uuid") final UUID uuid) {
        var scheduling = schedulingBusiness.findByUuid(uuid);
        return super.build(HttpStatus.CREATED, schedulingMapper.mapperStatus(scheduling));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<Response> delete(@PathVariable(value = "uuid") final UUID uuid) {
        schedulingBusiness.deleteByUuid(uuid);
        return super.build(HttpStatus.NO_CONTENT);
    }
}
