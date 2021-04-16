package com.magalutest.schedulingcore.business;


import com.magalutest.schedulingcore.model.Customer;

import java.util.UUID;

public interface CustomerBusiness {

    Customer create(Customer customer);

    Customer findByUuid(UUID uuid);

    Customer findByPhone(String phone);

    void deleteByUuid(UUID uuid);
}
