package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "select c from Customer c where c.isDelete = false ")
    Page<Customer> findAllNoDelete(Pageable pageable) ;

    @Query(value = "select c from Customer c where c.isDelete = false ")
    Iterable<Customer> findAllNoDelete() ;

//    @Query("SELECT c FROM Customer c")
//    List<Customer> findAllCustomers();
}
