package com.cg.controller.rest;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Withdraw;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.DepositDTO;
import com.cg.model.dto.WithdrawDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.IDepositService;
import com.cg.service.withdraw.IWithdrawService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;


@RestController
@RequestMapping("/api/transactions")
public class TransactionRestController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;

    @Autowired
    private IWithdrawService withdrawService;

    @Autowired
    private AppUtils appUtils;

    @PostMapping("/deposit/{id}")
    public ResponseEntity<?> doDeposit(@PathVariable Long id,
                                       @Validated @RequestBody DepositDTO depositDTO,
                                       BindingResult bindingResult) {
        Deposit deposit = new Deposit();

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {

            BigDecimal currentBalance = customer.get().getBalance();
            BigDecimal transactionAmount = depositDTO.getTransactionAmount();

            customer.get().setBalance(currentBalance.add(transactionAmount));
//            customer.get().setBalance(customer.get().getBalance(depositDTO.getTransactionAmount()));
            customerService.save(customer.get());

            deposit.setCustomer(customer.get());
            deposit.setTransactionAmount(transactionAmount);
            depositService.save(deposit);

            Optional<CustomerDTO> customerDTO = customerService.findCustomerDTOById(id);

            return new ResponseEntity<>(customerDTO.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<?> doWithdraw(@PathVariable Long  id,
                                        @RequestBody WithdrawDTO withdrawDTO,
                                        BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);

        }

        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            BigDecimal currentBalance = customer.get().getBalance();
            BigDecimal transactionAmount = withdrawDTO.getTransactionAmount();

            customer.get().setBalance(currentBalance.subtract(transactionAmount));

            customerService.save(customer.get());

            Withdraw withdraw = new Withdraw();

            withdraw.setCustomer(customer.get());
            withdraw.setTransactionAmount(transactionAmount);

            withdrawService.save(withdraw);

            Optional<CustomerDTO> customerDTO = customerService.findCustomerDTOById(id);

            return new ResponseEntity<>(customerDTO.get(), HttpStatus.OK);

        }

        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }
}
