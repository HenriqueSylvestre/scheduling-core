package com.magalutest.schedulingcore.controller.mapper;

import com.magalutest.schedulingcore.controller.dto.CustomerRequestDTO;
import com.magalutest.schedulingcore.controller.dto.CustomerResponseDTO;
import com.magalutest.schedulingcore.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CustomerMapperTest {

    @InjectMocks
    CustomerMapper customerMapper;

    @Test
    void mapperDTOtoCustomerSuccess() {
        final var customerRequestDTO = CustomerRequestDTO.builder()
                .name("João da Silva")
                .email("joaosilva@gmail.com")
                .phone("5516991778455")
                .push("cP9ZoomxpYgetvIhAp6AhYECKsj1")
                .whatsapp(false)
                .build();

        final var customerResult = customerMapper.mapper(customerRequestDTO);

        Assertions.assertNotNull(customerResult);
        Assertions.assertEquals(customerRequestDTO.getName(), customerResult.getName());
        Assertions.assertEquals(customerRequestDTO.getEmail(), customerResult.getEmail());
        Assertions.assertEquals(customerRequestDTO.getPhone(), customerResult.getPhone());
        Assertions.assertEquals(customerRequestDTO.getPush(), customerResult.getPush());
        Assertions.assertEquals(customerRequestDTO.isWhatsapp(), customerResult.isWhatsapp());
    }

    @Test
    void mapperCustomerToResponseDTOSuccess() {
        final var  customer = Customer.builder()
                .name("João da Silva")
                .email("joaosilva@gmail.com")
                .phone("5516991778455")
                .push("cP9ZoomxpYgetvIhAp6AhYECKsj1")
                .whatsapp(true)
                .build();

        var customerResponseDTO = customerMapper.mapper(customer);
        Assertions.assertNotNull(customerResponseDTO);
        Assertions.assertEquals(customer.getName(), customerResponseDTO.getName());
        Assertions.assertEquals(customer.getEmail(), customerResponseDTO.getEmail());
        Assertions.assertEquals(customer.getPhone(), customerResponseDTO.getPhone());
        Assertions.assertEquals(customer.getPush(), customerResponseDTO.getPush());
        Assertions.assertEquals(customer.isWhatsapp(), customerResponseDTO.isWhatsapp());
    }
}