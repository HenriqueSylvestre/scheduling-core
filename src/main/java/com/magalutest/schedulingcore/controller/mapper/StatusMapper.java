package com.magalutest.schedulingcore.controller.mapper;

import com.magalutest.schedulingcore.controller.dto.StatusRequestDTO;
import com.magalutest.schedulingcore.controller.dto.StatusResponseDTO;
import com.magalutest.schedulingcore.model.Status;
import org.springframework.stereotype.Service;

@Service
public class StatusMapper {

    public Status mapper(final StatusRequestDTO requestDTO) {
        return Status.builder()
                .name(requestDTO.getName())
                .build();
    }

    public StatusResponseDTO mapper(final Status status) {
        return StatusResponseDTO.builder()
                .id(status.getId())
                .name(status.getName())
                .build();
    }
}
