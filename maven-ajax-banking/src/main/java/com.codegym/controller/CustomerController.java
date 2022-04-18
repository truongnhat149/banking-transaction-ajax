package com.codegym.controller;


import com.codegym.model.Customer;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers")
    private ModelAndView showAllCustomers(){
        Iterable<Customer> customers = customerService.findAllNoDelete();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
