package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.model.Scheduling;
import com.magalutest.schedulingcore.repository.SchedulingRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchedulingBusinessImpl implements SchedulingBusiness{

    private final SchedulingRepository schedulingRepository;

    public SchedulingBusinessImpl(final SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    @Override
    public Scheduling create(Scheduling scheduling) {
        // TODO ... implementar o agendamento antes de salvar
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
}
