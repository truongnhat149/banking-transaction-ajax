package lpnt.cg.repository;

import lpnt.cg.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "SELECT c FROM Customer  c where c.isSuspended = false")
    Page<Customer> findAllBySuspendedIsFalse(Pageable pageable);

    @Query(value = "SELECT c FROM Customer  c where c.isSuspended = false")
    Iterable<Customer> findAllBySuspendedIsFalse();
}
