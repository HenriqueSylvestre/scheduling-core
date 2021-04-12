package com.magalutest.schedulingcore.repository;

import com.magalutest.schedulingcore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
