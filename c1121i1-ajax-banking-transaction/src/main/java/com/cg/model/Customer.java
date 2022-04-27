package com.cg.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.")
    @Size(min = 5, max = 50, message = "The length of email must be between 5 and 50 characters.")
    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
    private String address;

    @Digits(integer = 12, fraction = 0)
    @Column(updatable = true)
    private BigDecimal balance;


    public Customer(Long id, String fullName, String email, String phone, String address, BigDecimal balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

    @OneToMany(targetEntity = Deposit.class, mappedBy = "customer")
    private Set<Deposit> deposits;

    @OneToMany(targetEntity = Withdraw.class, mappedBy = "customer")
    private Set<Withdraw> withdraws;

    @OneToMany(targetEntity = Transfer.class, mappedBy = "sender")
    private Set<Transfer> sender;

    @OneToMany(targetEntity = Transfer.class, mappedBy = "recipient")
    private Set<Transfer> recipient;

    public BigDecimal getBalance(BigDecimal transactionAmount) {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                ", deposits=" + deposits +
                ", withdraws=" + withdraws +
                ", sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }
}
