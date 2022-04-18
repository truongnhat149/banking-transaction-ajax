package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date created_at = new Date();

    private Long create_by;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date update_at = new Date();

    private Long update_by;

    @NotEmpty(message = "Name not empty")
    @Pattern(regexp = "(^([AÀẢÃÁẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÈÉẺẼẸÊỀẾỂỄỆFGHIÍÌỈĨỊJKLMNOÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢPQRSTUÙÚỦŨỤƯỪỨỬỮỰVWXYÝỲỶỸỴZ]+[aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*?[ ]?)+$)", message = "Name format not true, Ex example : Nguyễn Văn A ")
    @Size(min = 1, max = 45, message = "Full name description within 255 characters ! ")
    private String fullName;

    @Pattern(regexp = "(^[a-z][a-z0-9_\\.]{3,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,7}){1,7}$)", message = "Mail not true, Ex: codegymhue2021@codegym.com ")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "(^$|[0][0-9]{9,10}$)", message = "Formatter not true, phone number is have 10-11 character ! ")
    private String phone;

    @Size(min = 1, max = 255, message = "Address description within 255 characters ! ")
    private String address;

    @Digits(integer = 12, fraction = 0)
    private BigDecimal balance = BigDecimal.valueOf(0);

    private boolean isDelete = false;


    @JsonIgnore
//    @OneToMany(targetEntity = Deposit.class, mappedBy = "customer", fetch = FetchType.EAGER)
    @OneToMany(targetEntity = Deposit.class, mappedBy = "customer")
    private List<Deposit> deposits;

    @JsonIgnore
//    @OneToMany(targetEntity = Withdraw.class, mappedBy = "customer", fetch = FetchType.EAGER)
    @OneToMany(targetEntity = Withdraw.class, mappedBy = "customer")

    private List<Withdraw> withdraws;

    @JsonIgnore
//    @OneToMany(targetEntity = Transfer.class, mappedBy = "sender", fetch = FetchType.EAGER)
    @OneToMany(targetEntity = Transfer.class, mappedBy = "sender")
    private List<Transfer> senders;

    @JsonIgnore
//    @OneToMany(targetEntity = Transfer.class, mappedBy = "recipient", fetch = FetchType.EAGER)
    @OneToMany(targetEntity = Transfer.class, mappedBy = "recipient")
    private List<Transfer> recipients;

    public Customer(String fullName,
                    String email,
                    String phone,
                    String address) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", create_by=" + create_by +
                ", update_at=" + update_at +
                ", update_by=" + update_by +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                ", isDelete=" + isDelete +
                '}';
    }
}
