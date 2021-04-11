package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.model.Scheduling;

import java.util.UUID;

public interface SchedulingBusiness {
    Scheduling create(Scheduling scheduling);

    Scheduling findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
