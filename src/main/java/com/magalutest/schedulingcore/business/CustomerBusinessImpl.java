package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.model.Customer;
import com.magalutest.schedulingcore.repository.CustomerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerBusinessImpl implements CustomerBusiness {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerBusinessImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        validadeEmail(customer);
        validadePhone(customer);
        validadePush(customer);
        validadeWhatsapp(customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByUuid(@NonNull UUID uuid) {
        return customerRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException());
    }

    @Override
    public Customer findByPhone(@NonNull String phone) {
        return Optional.ofNullable(customerRepository.findByPhone(phone))
                .orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        customerRepository.deleteById(uuid);
    }

    protected void validadeEmail(Customer customer) {
        if(customer.getEmail() != null && customer.getEmail().isEmpty()) {
            customer.setEmail(null);
        }
    }

    protected void validadePhone(Customer customer) {
        if(customer.getPhone() != null && customer.getPhone().isEmpty()) {
            customer.setPhone(null);
        }
    }

    protected void validadePush(Customer customer) {
        if(customer.getPush() != null && customer.getPush().isEmpty()) {
            customer.setPush(null);
        }
    }

    protected void validadeWhatsapp(Customer customer) {
        if(customer.getPhone() != null && customer.getPhone().isEmpty()) {
            customer.setWhatsapp(false);
        }
    }
}
