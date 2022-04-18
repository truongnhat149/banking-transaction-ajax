package com.codegym.service.deposit;

import com.codegym.model.Deposit;
import com.codegym.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepositService implements IDepositService{
    @Autowired
    private IDepositRepository depositRepository ;

    @Override
    public List<Deposit> findAll() {
        return depositRepository.findAll();
    }

    @Override
    public Optional<Deposit> findById(Long id) {
        return depositRepository.findById(id);
    }

    @Override
    public Deposit save(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public void remove(Long id) {
        Deposit deposit = depositRepository.getById(id) ;
        deposit.setDelete(true);
        depositRepository.save(deposit);
    }
}
