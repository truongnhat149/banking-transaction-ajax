package com.cg.repository;

import com.cg.model.Customer;
import com.cg.model.Transfer;
import com.cg.model.dto.TransferDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    void save(TransferDTO transferDTO);

//    void save(Optional<Customer> sender, Optional<Customer> recipient, TransferDTO transfer);
}
