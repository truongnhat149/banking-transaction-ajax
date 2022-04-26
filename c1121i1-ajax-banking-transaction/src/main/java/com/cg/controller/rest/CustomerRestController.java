package com.cg.controller.rest;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<?>> getAll() {
        List<CustomerDTO> customers = customerService.findAllCustomersIsActive();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<CustomerDTO> customerDTO = customerService.findCustomerDTOById(id);

        if (customerDTO.isPresent()) {
            return new ResponseEntity<>(customerDTO.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {

        customer.setId(0L);
        customer.setBalance(BigDecimal.valueOf(0));
        customer = customerService.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> doSuspended(@PathVariable Long id,@RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(id);

        customer.setDeleted(true);
        customer = customerService.save(customer);
        if (customerOptional.isPresent()){
            return new ResponseEntity<>(customer, HttpStatus.OK );
        }

        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }
}
