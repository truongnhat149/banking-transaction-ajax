package lpnt.cg.service.withdraw;

import lpnt.cg.model.Withdraw;
import lpnt.cg.repository.IWithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawService implements IWithdrawService{

    @Autowired
    private IWithdrawRepository withdrawRepository;

    @Override
    public Iterable<Withdraw> findAll() {
        return withdrawRepository.findAll();
    }

    @Override
    public Optional<Withdraw> findById(Long id) {
        return withdrawRepository.findById(id);
    }

    @Override
    public Withdraw save(Withdraw withdraw) {
        return withdrawRepository.save(withdraw);
    }

    @Override
    public void remove(Long id) {
        Withdraw withdraw = findById(id).get();
        withdraw.setSuspended(true);
        save(withdraw);
    }
}
