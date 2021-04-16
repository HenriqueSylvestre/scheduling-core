package com.magalutest.schedulingcore.business;

import com.magalutest.schedulingcore.exception.NotFoundException;
import com.magalutest.schedulingcore.model.Customer;
import com.magalutest.schedulingcore.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerBusinessImplTest {

    final UUID uuidCustomer = UUID.randomUUID();
    final String phoneCustomer = "5516991778455";
    final Customer customerPersisted = Customer.builder()
            .uuid(uuidCustomer)
            .name("João da Silva")
            .email("joaosilva@gmail.com")
            .phone(phoneCustomer)
            .push("cP9ZoomxpYgetvIhAp6AhYECKsj1")
            .whatsapp(true)
            .build();

    final Customer customer = Customer.builder()
            .name("João da Silva")
            .email("joaosilva@gmail.com")
            .phone("5516991778455")
            .push("cP9ZoomxpYgetvIhAp6AhYECKsj1")
            .whatsapp(true)
            .build();

    @InjectMocks
    private CustomerBusinessImpl customerBusinessImp;

    @Mock
    CustomerRepository customerRepository;

    @Test
    void createSuccess() {
        Mockito.when(customerRepository.save(customer)).thenReturn(customerPersisted);

        final var customerResult = customerBusinessImp.create(customer);

        Assertions.assertNotNull(customerResult.getUuid());
        Assertions.assertEquals(customer.getName(), customerResult.getName());
        Assertions.assertEquals(customer.getEmail(), customerResult.getEmail());
        Assertions.assertEquals(customer.getPhone(), customerResult.getPhone());
        Assertions.assertEquals(customer.getPush(), customerResult.getPush());
        Assertions.assertEquals(customer.isWhatsapp(), customerResult.isWhatsapp());
    }



    @Test
    void findByUuidSuccess() {
        when(customerRepository.findById(uuidCustomer)).thenReturn(Optional.ofNullable(customerPersisted));

        final var customerResult = customerBusinessImp.findByUuid(uuidCustomer);

        Assertions.assertNotNull(customerResult);
        Assertions.assertEquals(uuidCustomer, customerResult.getUuid());
        Assertions.assertNotEquals(UUID.randomUUID(), customerResult.getUuid());
    }

    @Test
    public void findByUuidThrowNotFoundException() {
        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(NotFoundException.class, () -> {
            when(customerRepository.findById(uuid)).thenThrow(NotFoundException.class);
            customerBusinessImp.findByUuid(uuid);
        });
    }

    @Test
    void findByPhoneSuccess() {
        when(customerRepository.findByPhone(phoneCustomer)).thenReturn(customerPersisted);

        final var customerResult = customerBusinessImp.findByPhone(phoneCustomer);

        Assertions.assertNotNull(customerResult);
        Assertions.assertEquals(uuidCustomer, customerResult.getUuid());
        Assertions.assertNotEquals(UUID.randomUUID(), customerResult.getUuid());
        Assertions.assertEquals(phoneCustomer, customerResult.getPhone());
    }

    @Test
    public void findByPhoneThrowNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            when(customerRepository.findByPhone(phoneCustomer)).thenThrow(NotFoundException.class);
            customerBusinessImp.findByPhone(phoneCustomer);
        });
    }

    @Test
    void deleteByUuidSuccess() {
        customerBusinessImp.deleteByUuid(customerPersisted.getUuid());
        verify(customerRepository).deleteById(customerPersisted.getUuid());
    }

    @Test
    void validadeEmailSuccess() {
        var customer = Customer.builder()
                .name("José")
                .email("")
                .build();
        customerBusinessImp.validadeEmail(customer);
        Assertions.assertNull(customer.getEmail());
    }

    @Test
    void validadePhone() {
        var customer = Customer.builder()
                .name("José")
                .phone("")
                .build();
        customerBusinessImp.validadePhone(customer);
        Assertions.assertNull(customer.getPhone());
    }

    @Test
    void validadePush() {
        var customer = Customer.builder()
                .name("José")
                .push("")
                .build();
        customerBusinessImp.validadePush(customer);
        Assertions.assertNull(customer.getPush());
    }

    @Test
    void validadeWhatsapp() {
        var customer = Customer.builder()
                .name("José")
                .phone("")
                .whatsapp(true)
                .build();
        customerBusinessImp.validadeWhatsapp(customer);
        Assertions.assertFalse(customer.isWhatsapp());
    }
}