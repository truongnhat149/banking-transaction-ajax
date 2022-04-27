package com.cg.model.dto;

import com.cg.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDTO {

    private Long id;

    private Customer sender;

    private Customer recipient;

    private BigDecimal transferAmount;

    private int fees;


    private BigDecimal feesAmount;

    private BigDecimal transactionAmount;
}
