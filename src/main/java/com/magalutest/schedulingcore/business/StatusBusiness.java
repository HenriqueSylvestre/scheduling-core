package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.model.Status;

public interface StatusBusiness {

    Status create(Status status);

    Status findById(long id);

    void deleteById(long id);
}
