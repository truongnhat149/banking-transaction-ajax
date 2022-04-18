package com.codegym.formatter;

import com.codegym.model.Customer;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CustomerFormatter implements Formatter<Customer> {

    private CustomerService customerService;

    @Autowired
    public CustomerFormatter(ICustomerService customerService) {
        this.customerService = (CustomerService) customerService;
    }

    @Override
    public Customer parse(String text, Locale locale) throws ParseException {
        Optional<Customer> customerOptional = customerService.findById(Long.parseLong(text));
        return customerOptional.orElse(null);
    }

    @Override
    public String print(Customer object, Locale locale) {
        return "[" + object.getId() + ", " + object.getFullName() + "]";
    }
}