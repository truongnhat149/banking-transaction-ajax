package lpnt.cg.api;

import lpnt.cg.model.Customer;
import lpnt.cg.service.customer.ICustomerService;
import lpnt.cg.service.deposit.IDepositService;
import lpnt.cg.service.withdraw.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;

    @Autowired
    private IWithdrawService withdrawService;

    @GetMapping
    public ResponseEntity<List<?>> allCustomer() {
        List<Customer> customers = (List<Customer>) customerService.findAllBySuspendedIsFalse();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerOptional.get();
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }


    @PutMapping()
    public ResponseEntity<Customer> updateCustomerByFull(@RequestBody Customer customer) {
        Long id = customer.getId();
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
    }

//    @PutMapping("/deposit/{id}")
//    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id) {
//
//    }

//    @PostMapping
//    public ResponseEntity<?> createCustomer(@RequestBody Customer customer, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) return AppUtils.mapErrorToResponse(bindingResult);
//        return null;
//
//        try {
//            return ResponseEntity<Customer>(customerService.save(customer), HttpStatus.CREATED);
//
//        } catch (Exception e) {
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if(!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }
}
