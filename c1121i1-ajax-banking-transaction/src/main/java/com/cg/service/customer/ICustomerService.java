package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    List<CustomerDTO> findAllCustomersIsActive();

    List<Customer> findCustomersByDeletedIsFalse();

    Optional<CustomerDTO> findCustomerDTOById(Long id);

    void save(Optional<CustomerDTO> customerDTO);
}
