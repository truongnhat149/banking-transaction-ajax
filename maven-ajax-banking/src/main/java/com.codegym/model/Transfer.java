package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date created_at = new Date();

    private Long create_by ;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Customer sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Customer recipient;

    @Digits(integer= 12,fraction= 0)
    private BigDecimal transfer_amount ;

    private long fees ;
    private long feesAmount;
    private BigDecimal transactionAmount;
    private boolean isDeleted = false ;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date update_at = new Date();

    private Long update_by ;


}
