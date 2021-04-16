package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.model.Status;
import com.magalutest.schedulingcore.repository.StatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatusBusinessImplTest {

    @InjectMocks
    StatusBusinessImpl statusBusinessImpl;

    @Mock
    StatusRepository statusRepository;

    final Status status = Status.builder()
            .name("Agendado")
            .build();

    final Status statusPersisted = Status.builder()
            .id(1)
            .name("Agendado")
            .build();

    @Test
    void createSuccess() {
        Mockito.when(statusRepository.save(status)).thenReturn(statusPersisted);

        final var statusResult = statusBusinessImpl.create(status);

        Assertions.assertNotNull(statusResult);
        Assertions.assertNotEquals(status.getId(), statusResult.getId());
        Assertions.assertEquals(status.getName(), statusResult.getName());
    }

    @Test
    void findByIdSuccess() {
        when(statusRepository.findById(statusPersisted.getId())).thenReturn(Optional.ofNullable(statusPersisted));

        final var statusResult = statusBusinessImpl.findById(statusPersisted.getId());

        Assertions.assertNotNull(statusResult);
        Assertions.assertEquals(statusPersisted.getId(), statusResult.getId());
        Assertions.assertEquals(statusPersisted.getName(), statusResult.getName());
    }

    @Test
    public void findByIdThrowNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            when(statusBusinessImpl.findById(4)).thenThrow(NotFoundException.class);
            statusBusinessImpl.findById(4);
        });
    }

    @Test
    void deleteById() {
        statusBusinessImpl.deleteById(statusPersisted.getId());
        verify(statusRepository).deleteById(statusPersisted.getId());
    }

    @Test
    void validateName() {
        var status = Status.builder()
                .name("")
                .build();
        statusBusinessImpl.validateName(status);
        Assertions.assertNull(status.getName());
    }
}