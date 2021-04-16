package com.magalutest.schedulingcore.controller;

import com.magalutest.schedulingcore.business.CustomerBusiness;
import com.magalutest.schedulingcore.controller.dto.CustomerRequestDTO;
import com.magalutest.schedulingcore.controller.dto.Response;
import com.magalutest.schedulingcore.controller.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController extends AbstractController {

    private final CustomerBusiness customerBusiness;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(final CustomerBusiness customerBusiness, final CustomerMapper customerMapper) {
        this.customerBusiness = customerBusiness;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody @Valid final CustomerRequestDTO requestDTO) {
        final var customer = customerBusiness.create(customerMapper.mapper(requestDTO));
        return super.build(HttpStatus.CREATED, customerMapper.mapper(customer));
    }

    @GetMapping()
    public ResponseEntity<Response> findByPhone(@RequestParam(value = "phone") final String phone) {
        final var customer = customerBusiness.findByPhone(phone);
        return super.build(HttpStatus.OK, customerMapper.mapper(customer));
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<Response> find(@PathVariable(value = "uuid") final UUID uuid) {
        final var customer = customerBusiness.findByUuid(uuid);
        return super.build(HttpStatus.OK, customerMapper.mapper(customer));
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<Response> delete(@PathVariable(value = "uuid") final UUID uuid) {
        customerBusiness.deleteByUuid(uuid);
        return super.build(HttpStatus.NO_CONTENT);
    }
}
