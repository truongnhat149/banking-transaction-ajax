package lpnt.cg.service.customer;

import lpnt.cg.model.Customer;
import lpnt.cg.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        Customer customer = findById(id).get();
        customer.setSuspended(true);
        save(customer);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllBySuspendedIsFalse(Pageable pageable) {
        return customerRepository.findAllBySuspendedIsFalse(pageable);
    }

    @Override
    public Iterable<Customer> findAllBySuspendedIsFalse() {
        return customerRepository.findAllBySuspendedIsFalse();
    }
}
