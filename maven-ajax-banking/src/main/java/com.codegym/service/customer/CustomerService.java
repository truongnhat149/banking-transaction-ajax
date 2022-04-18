package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository ;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

//    @Override
//    public List<Customer> findAllCustomers() {
//        return  null;
////        return customerRepository.findAllCustomers();
//    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return  customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        Customer customer = findById(id).get();
        customer.setDelete(true);
        save(customer);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllNoDelete(Pageable pageable) {
        return customerRepository.findAllNoDelete(pageable);
    }

    @Override
    public Iterable<Customer> findAllNoDelete() {
        return customerRepository.findAllNoDelete();
    }
}
