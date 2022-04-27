package com.cg.service.transfer;

import com.cg.model.Customer;
import com.cg.model.Transfer;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.repository.CustomerRepository;
import com.cg.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransferServiceImpl implements ITransferService{

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Transfer> findAll() {
        return null;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transfer getById(Long id) {
        return null;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save(transfer);
    }


    @Override
    public void remove(Long id) {

    }

    @Override
    public void save(TransferDTO transferDTO) {
        transferRepository.save(transferDTO);
    }


    @Override
    public void doTransfer(Optional<CustomerDTO> sender, Optional<CustomerDTO> recipient, TransferDTO transfer) {
        customerRepository.save(sender);

        customerRepository.save(recipient);

        transferRepository.save(transfer);
    }
}
