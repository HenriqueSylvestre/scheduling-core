package com.magalutest.schedulingcore.controller.mapper;

import com.magalutest.schedulingcore.controller.dto.CustomerRequestDTO;
import com.magalutest.schedulingcore.controller.dto.CustomerResponseDTO;
import com.magalutest.schedulingcore.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer mapper(final CustomerRequestDTO requestDTO) {
        return Customer.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .push(requestDTO.getPush())
                .whatsapp(requestDTO.isWhatsapp())
                .build();
    }

    public CustomerResponseDTO mapper(final Customer customer) {
        return CustomerResponseDTO.builder()
                .uuid(customer.getUuid())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .push(customer.getPush())
                .whatsapp(customer.isWhatsapp())
                .build();
    }
}
