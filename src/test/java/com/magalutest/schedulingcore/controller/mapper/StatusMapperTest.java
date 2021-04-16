package com.magalutest.schedulingcore.controller.mapper;

import com.magalutest.schedulingcore.controller.dto.StatusRequestDTO;
import com.magalutest.schedulingcore.controller.dto.StatusResponseDTO;
import com.magalutest.schedulingcore.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class StatusMapperTest {

    @InjectMocks
    StatusMapper statusMapper;

    @Test
    void mapperDTOtoStatusSuccess() {
        var statusRequestDTO = StatusRequestDTO.builder()
                .name("Agendado")
                .build();

        var statusResult = statusMapper.mapper(statusRequestDTO);

        Assertions.assertNotNull(statusResult);
        Assertions.assertEquals(statusRequestDTO.getName(), statusResult.getName());
    }

    @Test
    void testMapper() {
        var status = Status.builder()
                .id(1)
                .name("Agendado")
                .build();

        var statusResponseDTO = statusMapper.mapper(status);

        Assertions.assertNotNull(statusResponseDTO);
        Assertions.assertEquals(status.getId(), statusResponseDTO.getId());
        Assertions.assertEquals(status.getName(), statusResponseDTO.getName());
    }
}