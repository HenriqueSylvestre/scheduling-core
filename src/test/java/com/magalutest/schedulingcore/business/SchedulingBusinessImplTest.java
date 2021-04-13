package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.exception.ReceiverNotContainValidMeansCommunication;
import com.magalutest.schedulingcore.exception.SendDateInvalid;
import com.magalutest.schedulingcore.model.*;
import com.magalutest.schedulingcore.repository.CustomerRepository;
import com.magalutest.schedulingcore.repository.SchedulingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchedulingBusinessImplTest {

    final LocalDateTime send = LocalDateTime.now().plusMonths(1);

    final UUID uuidReceiver = UUID.randomUUID();
    final Customer receiverPersisted = Customer.builder()
                    .uuid(uuidReceiver)
                    .name("João da Silva")
                    .email("joaosilva@gmail.com")
                    .phone("5516991778431")
                    .push("cP9ZoomxpYgetvIhAp6AhYECKsj1")
                    .whatsapp(true)
                    .build();

    final UUID uuidScheduling = UUID.randomUUID();
    final Scheduling schedulingPersisted = Scheduling.builder()
            .uuid(uuidScheduling)
            .send(send)
            .message("Reunião amanhã as 09h00.")
            .receiver(receiverPersisted)
            .schedulingEmail(SchedulingEmail.builder()
                    .uuid(UUID.randomUUID())
                    .sended(null)
                    .status(Status.builder()
                            .id(1)
                            .name("Agendado")
                            .build())
                    .build())
            .schedulingSms(SchedulingSms.builder()
                    .uuid(UUID.randomUUID())
                    .sended(null)
                    .status(Status.builder()
                            .id(1)
                            .name("Agendado")
                            .build())
                    .build())
            .schedulingPush(SchedulingPush.builder()
                    .uuid(UUID.randomUUID())
                    .sended(null)
                    .status(Status.builder()
                            .id(1)
                            .name("Agendado")
                            .build())
                    .build())
            .schedulingWhatsapp(SchedulingWhatsapp.builder()
                    .uuid(UUID.randomUUID())
                    .sended(null)
                    .status(Status.builder()
                            .id(1)
                            .name("Agendado")
                            .build())
                    .build())
            .build();

    final Scheduling scheduling = Scheduling.builder()
            .send(send)
            .message("Reunião amanhã as 09h00.")
            .receiver(Customer.builder()
                    .uuid(uuidReceiver)
                    .build())
            .build();

    final Scheduling schedulingWithReceiver = Scheduling.builder()
            .send(send)
            .message("Reunião amanhã as 09h00.")
            .receiver(receiverPersisted)
            .build();

    @InjectMocks
    SchedulingBusinessImpl schedulingBusinessImpl;

    @Mock
    SchedulingRepository schedulingRepository;

    @Mock
    CustomerRepository customerRepository;

    @Test
    void createSchedulingSuccess() {
        when(customerRepository.findById(uuidReceiver)).thenReturn(java.util.Optional.of(receiverPersisted));
        Mockito.when(schedulingRepository.save(scheduling)).thenReturn(this.schedulingPersisted);

        final var schedulingResult = schedulingBusinessImpl.create(scheduling);

        Assertions.assertNotNull(schedulingResult.getUuid());
        Assertions.assertEquals(scheduling.getSend(), schedulingResult.getSend());
        Assertions.assertEquals(scheduling.getMessage(), schedulingResult.getMessage());

        Assertions.assertNotNull(schedulingResult.getReceiver());
        Assertions.assertEquals(scheduling.getReceiver().getUuid(), schedulingResult.getReceiver().getUuid());

        Assertions.assertNotNull(schedulingResult.getSchedulingEmail());
        Assertions.assertNotNull(schedulingResult.getSchedulingEmail().getStatus());
        Assertions.assertEquals(1, schedulingResult.getSchedulingEmail().getStatus().getId());

        Assertions.assertNotNull(schedulingResult.getSchedulingSms());
        Assertions.assertNotNull(schedulingResult.getSchedulingSms().getStatus());
        Assertions.assertEquals(1, schedulingResult.getSchedulingSms().getStatus().getId());

        Assertions.assertNotNull(schedulingResult.getSchedulingPush());
        Assertions.assertNotNull(schedulingResult.getSchedulingPush().getStatus());
        Assertions.assertEquals(1, schedulingResult.getSchedulingPush().getStatus().getId());

        Assertions.assertNotNull(schedulingResult.getSchedulingWhatsapp());
        Assertions.assertNotNull(schedulingResult.getSchedulingWhatsapp().getStatus());
        Assertions.assertEquals(1, schedulingResult.getSchedulingWhatsapp().getStatus().getId());
    }

    @Test
    void findByUuid() {
        when(schedulingRepository.findById(uuidScheduling)).thenReturn(Optional.ofNullable(schedulingPersisted));

        final var schedulingResult = schedulingBusinessImpl.findByUuid(uuidScheduling);

        Assertions.assertNotNull(schedulingResult);
        Assertions.assertEquals(uuidScheduling, schedulingResult.getUuid());
        Assertions.assertNotEquals(UUID.randomUUID(), schedulingResult.getUuid());
    }

    @Test
    public void findByUuidThrowNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            when(schedulingRepository.findById(uuidScheduling)).thenThrow(NotFoundException.class);
            schedulingBusinessImpl.findByUuid(uuidScheduling);
        });
    }

    @Test
    void deleteByUuidSuccess() {
        schedulingBusinessImpl.deleteByUuid(schedulingPersisted.getUuid());
        verify(schedulingRepository).deleteById(schedulingPersisted.getUuid());
    }

//    @Test
//    void deleteByUuidThrowEmptyResultDataAccessException() {
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//            when(schedulingRepository.deleteById(uuidScheduling)).thenThrow(EmptyResultDataAccessException.class);
//            schedulingBusinessImpl.deleteByUuid(uuidScheduling);
//        });
//    }

    @Test
    void validateReceiverSuccess() {
        when(customerRepository.findById(scheduling.getReceiver().getUuid())).thenReturn(Optional.ofNullable(receiverPersisted));
        var customer = customerRepository.findById(uuidReceiver);
        schedulingBusinessImpl.validateReceiver(scheduling);

        Assertions.assertNotNull(customer);
        Assertions.assertEquals(uuidReceiver, customer.get().getUuid());
        Assertions.assertNotNull(scheduling.getReceiver());
        Assertions.assertEquals(customer.get().getUuid(), scheduling.getReceiver().getUuid());
        Assertions.assertEquals(customer.get().getName(), scheduling.getReceiver().getName());
        Assertions.assertEquals(customer.get().getEmail(), scheduling.getReceiver().getEmail());
        Assertions.assertEquals(customer.get().getPhone(), scheduling.getReceiver().getPhone());
        Assertions.assertEquals(customer.get().getPush(), scheduling.getReceiver().getPush());
        Assertions.assertEquals(customer.get().isWhatsapp(), scheduling.getReceiver().isWhatsapp());
    }

    @Test
    void validateReceiverThrowNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            when(customerRepository.findById(scheduling.getReceiver().getUuid())).thenThrow(NotFoundException.class);
            schedulingBusinessImpl.validateReceiver(scheduling);
        });
    }

    @Test
    void validateSendSuccess() {
        schedulingBusinessImpl.validateSend(send);
        Assertions.assertNotNull(send);
        Assertions.assertFalse(send.isBefore(LocalDateTime.now()));
    }

    @Test
    void validadeSendThrowSendDateInvalid() {
        Assertions.assertThrows(SendDateInvalid.class, () -> {
            schedulingBusinessImpl.validateSend(LocalDateTime.now().minusMonths(1));
        });
    }

    @Test
    void validateExistAnySchedulingThrowReceiverNotContainValidMeansCommunication() {
        Assertions.assertThrows(ReceiverNotContainValidMeansCommunication.class, () -> {
            schedulingBusinessImpl.validateExistAnyScheduling(scheduling);
        });
    }

    @Test
    void scheduleEmailSuccess() {
        schedulingBusinessImpl.scheduleEmail(schedulingWithReceiver);

        Assertions.assertNotNull(schedulingWithReceiver.getSchedulingEmail());
        Assertions.assertNotNull(schedulingWithReceiver.getSchedulingEmail().getStatus());
        Assertions.assertEquals(1, schedulingWithReceiver.getSchedulingEmail().getStatus().getId());
    }

    @Test
    void scheduleEmailIsNotValid() {
        schedulingBusinessImpl.scheduleEmail(scheduling);
        Assertions.assertNull(scheduling.getSchedulingEmail());
    }

    @Test
    void scheduleSmsSuccess() {
        schedulingBusinessImpl.scheduleSms(schedulingWithReceiver);

        Assertions.assertNotNull(schedulingWithReceiver.getSchedulingSms());
        Assertions.assertNotNull(schedulingWithReceiver.getSchedulingSms().getStatus());
        Assertions.assertEquals(1, schedulingWithReceiver.getSchedulingSms().getStatus().getId());
    }

    @Test
    void scheduleSmsIsNotValid() {
        schedulingBusinessImpl.scheduleSms(scheduling);
        Assertions.assertNull(scheduling.getSchedulingSms());
    }

    @Test
    void schedulePushSuccess() {
        schedulingBusinessImpl.schedulePush(schedulingWithReceiver);

        Assertions.assertNotNull(schedulingWithReceiver.getSchedulingPush());
        Assertions.assertNotNull(schedulingWithReceiver.getSchedulingPush().getStatus());
        Assertions.assertEquals(1, schedulingWithReceiver.getSchedulingPush().getStatus().getId());
    }

    @Test
    void schedulePushIsNotValid() {
        schedulingBusinessImpl.schedulePush(scheduling);
        Assertions.assertNull(scheduling.getSchedulingPush());
    }

    @Test
    void scheduleWhatsappSuccess() {
        schedulingBusinessImpl.scheduleWhatsapp(schedulingWithReceiver);

        Assertions.assertNotNull(schedulingWithReceiver.getSchedulingWhatsapp());
        Assertions.assertNotNull(schedulingWithReceiver.getSchedulingWhatsapp().getStatus());
        Assertions.assertEquals(1, schedulingWithReceiver.getSchedulingWhatsapp().getStatus().getId());
    }

    @Test
    void scheduleWhatsappIsNotValid() {
        schedulingBusinessImpl.scheduleWhatsapp(scheduling);
        Assertions.assertNull(scheduling.getSchedulingWhatsapp());
    }
}