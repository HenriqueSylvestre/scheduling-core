package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.exception.ReceiverNotContainValidMeansCommunication;
import com.magalutest.schedulingcore.exception.SendDateInvalid;
import com.magalutest.schedulingcore.model.*;
import com.magalutest.schedulingcore.repository.CustomerRepository;
import com.magalutest.schedulingcore.repository.SchedulingRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SchedulingBusinessImpl implements SchedulingBusiness{

    private final SchedulingRepository schedulingRepository;
    private final CustomerRepository customerRepository;

    public SchedulingBusinessImpl(final SchedulingRepository schedulingRepository, final CustomerRepository customerRepository) {
        this.schedulingRepository = schedulingRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Scheduling create(Scheduling scheduling) {
        validateSend(scheduling.getSend());
        if(scheduling.getReceiver() != null) {
            validateReceiver(scheduling);
            scheduleEmail(scheduling);
            scheduleSms(scheduling);
            schedulePush(scheduling);
            scheduleWhatsapp(scheduling);
            validateExistAnyScheduling(scheduling);
        }

        return schedulingRepository.save(scheduling);
    }



    @Override
    public Scheduling findByUuid(@NonNull final UUID uuid) {
        return schedulingRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteByUuid(@NonNull final UUID uuid) {
        schedulingRepository.deleteById(uuid);
    }

    private void validateReceiver(Scheduling scheduling) {
        var customer = customerRepository.findById(scheduling.getReceiver().getUuid())
                .orElseThrow(() -> new NotFoundException());
        scheduling.setReceiver(customer);
    }

    private void validateSend(final LocalDateTime send) {
        if(send != null) {
            if(send.isBefore(LocalDateTime.now())) {
                throw new SendDateInvalid();
            }
        }
    }

    private void validateExistAnyScheduling(Scheduling scheduling) {
        if(!(scheduling.getSchedulingEmail() != null || scheduling.getSchedulingEmail() != null
                || scheduling.getSchedulingEmail() != null || scheduling.getSchedulingEmail() != null)) {
            throw new ReceiverNotContainValidMeansCommunication();
        }
    }

    private void scheduleEmail(Scheduling scheduling) {
        if(scheduling.getReceiver().getEmail() != null && !scheduling.getReceiver().getEmail().isEmpty()) {
            scheduling.setSchedulingEmail(SchedulingEmail.builder()
                    .status(Status.builder()
                            .id(1)
                            .build())
                    .build());
        }
    }

    private void scheduleSms(Scheduling scheduling) {
        if(scheduling.getReceiver().getPhone() != null && !scheduling.getReceiver().getPhone().isEmpty()) {
            scheduling.setSchedulingSms(SchedulingSms.builder()
                    .status(Status.builder()
                            .id(1)
                            .build())
                    .build());

        }
    }

    private void schedulePush(Scheduling scheduling) {
        if(scheduling.getReceiver().getPush() != null && !scheduling.getReceiver().getPush().isEmpty()) {
            scheduling.setSchedulingPush(SchedulingPush.builder()
                    .status(Status.builder()
                            .id(1)
                            .build())
                    .build());
        }
    }

    private void scheduleWhatsapp(Scheduling scheduling) {
        if(scheduling.getReceiver().isWhatsapp() && scheduling.getReceiver().getPhone() != null && !scheduling.getReceiver().getPhone().isEmpty()) {
            scheduling.setSchedulingWhatsapp(SchedulingWhatsapp.builder()
                    .status(Status.builder()
                            .id(1)
                            .build())
                    .build());
        }
    }
}
