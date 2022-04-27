package com.cg.controller.rest;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Withdraw;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.DepositDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.model.dto.WithdrawDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.IDepositService;
import com.cg.service.transfer.ITransferService;
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
    private ITransferService transferService;

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
    public ResponseEntity<?> doWithdraw(@PathVariable Long id,
                                        @Validated @RequestBody WithdrawDTO withdrawDTO,
                                        BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);

        }

        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            BigDecimal currentBalance = customer.get().getBalance();
            BigDecimal transactionAmount = withdrawDTO.getTransactionAmount();

            customer.get().setBalance(currentBalance.subtract(transactionAmount));

            if (currentBalance.compareTo(transactionAmount) >= 0) {
                customerService.save(customer.get());

                Withdraw withdraw = new Withdraw();

                withdraw.setCustomer(customer.get());
                withdraw.setTransactionAmount(transactionAmount);

                withdrawService.save(withdraw);

                Optional<CustomerDTO> customerDTO = customerService.findCustomerDTOById(id);

                return new ResponseEntity<>(customerDTO.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Transaction Amount must be smaller current balance", HttpStatus.BAD_REQUEST);
            }


        }

        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/transfer/{id}")
    public ResponseEntity<?> doTransfer(@PathVariable Long id,
                                        @Validated @RequestBody TransferDTO transferDTO,
                                        BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<CustomerDTO> sender = customerService.findCustomerDTOById(id);

        Optional<CustomerDTO> recipients;
//        Optional<Customer> sender = customerService.findById(id);
//
//        Optional<Customer> recipients;
        if (sender.isPresent()) {

            recipients = customerService.findCustomerDTOById(id);

            if (!transferDTO.getRecipient().getId().equals(sender.get().getId())) {
                Optional<CustomerDTO> recipientOptional = customerService.findCustomerDTOById(transferDTO.getRecipient().getId());

                if (recipientOptional.isPresent()) {

                    BigDecimal senderBalance = sender.get().getBalance();
                    BigDecimal recipientBalance = recipientOptional.get().getBalance();
                    BigDecimal transferAmount = transferDTO.getTransferAmount();
                    long fees = 10;
                    BigDecimal feesAmount = transferAmount.divide(BigDecimal.valueOf(fees));
                    BigDecimal transactionAmount = transferAmount.add(feesAmount);

                    int isMoney1000 = transferAmount.compareTo(BigDecimal.valueOf(1000));
                    if (isMoney1000 < 0) {
                        return new ResponseEntity<>("transfer amount must be smaller 1000", HttpStatus.BAD_REQUEST);
                    }

                    int isMoney10000000000L = transactionAmount.compareTo(BigDecimal.valueOf(10000000000L));
                    if (isMoney10000000000L > 0) {
                        return new ResponseEntity<>("transfer amount must be bigger 10000000000L", HttpStatus.BAD_REQUEST);
                    }

                    int isLimit = senderBalance.compareTo(transactionAmount);
                    if (isLimit < 0) {
                        return new ResponseEntity<>("the amount transfer must be less than ", HttpStatus.BAD_REQUEST);
                    }

                    sender.get().setBalance(senderBalance.subtract(transactionAmount));
                    recipientOptional.get().setBalance(recipientBalance.add(transactionAmount));
                    transferDTO.setFees((int) fees);
                    transferDTO.setFeesAmount(feesAmount);
                    transferDTO.setTransferAmount(transactionAmount);

                    try {
                        transferService.doTransfer(sender, recipientOptional, transferDTO);

                        Optional<CustomerDTO> customerDTO = customerService.findCustomerDTOById(id);
//                        Optional<Customer> customer = customerService.findById(id);

                        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ResponseEntity<>("Transaction error!", HttpStatus.OK);
                    }
                }
            }
        } return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }
}
