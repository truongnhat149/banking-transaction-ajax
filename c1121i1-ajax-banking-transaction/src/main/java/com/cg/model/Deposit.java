package com.cg.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deposits")
public class Deposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Digits(integer = 12, fraction = 0)
    @Column(name = "transaction_amount", nullable= false)
    private BigDecimal transactionAmount;

    public Deposit(Customer customer, BigDecimal transactionAmount) {
        this.customer = customer;
        this.transactionAmount = transactionAmount;
    }

}
