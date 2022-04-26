package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WithdrawDTO {
    private Long id;
    private Long customerId;

    @NotNull(message = "The transaction amount is required")
    @DecimalMin(value = "50", message = "Transaction Amount must be greater than or equal to 50")
    @DecimalMax(value = "10000000000", message = "Transaction Amount must be less than or equal to 10.000.000.000")
    private BigDecimal transactionAmount;
}
