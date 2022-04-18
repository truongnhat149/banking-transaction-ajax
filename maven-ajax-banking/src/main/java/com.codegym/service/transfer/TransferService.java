package com.codegym.service.transfer;

import com.codegym.model.Customer;
import com.codegym.model.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TransferService implements ITransferService{
    @Override
    public List<Transfer> findAll() {
        return null;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transfer save(Transfer transfer) {
     return null ;
    }

    @Override
    public void remove(Long id) {
        Transfer transfer = findById(id).get();
        transfer.setDeleted(true);
        save(transfer);
    }
}
