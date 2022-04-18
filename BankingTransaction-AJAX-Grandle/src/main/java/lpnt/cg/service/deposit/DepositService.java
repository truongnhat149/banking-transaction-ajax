package lpnt.cg.service.deposit;

import lpnt.cg.model.Deposit;
import lpnt.cg.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService implements IDepositService{

    @Autowired
   private IDepositRepository depositRepository;

    @Override
    public Iterable<Deposit> findAll() {
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
        Deposit deposit = findById(id).get();
        deposit.setSuspended(true);
        depositRepository.save(deposit);
    }
}
