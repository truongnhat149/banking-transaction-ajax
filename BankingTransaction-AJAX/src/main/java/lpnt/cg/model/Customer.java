package lpnt.cg.model;

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
    @Pattern(regexp = "((?=^.{0,100}$)D^[a-zA-Z-]+\\s[a-zA-Z-]+$)", message = "Name format not true, Ex example : Le Phung nhat truong")
    @Size(min = 1, max = 100, message = "Full name description within 100 characters ! ")
    private String fullName;

    @Pattern(regexp = "(^[a-z][a-z0-9_\\.]{3,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,7}){1,7}$)", message = "Mail not true, Ex: totalcodegym@codegym.com ")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "(^$|[0][0-9]{9,10}$)", message = "Formatter not true, phone number is have 10-11 character ! ")
    private String phone;

    @Size(min = 1, max = 255, message = "Address description within 255 characters ! ")
    private String address;

    @Digits(integer = 12, fraction = 0)
    private BigDecimal balance = BigDecimal.valueOf(0);

    private boolean isSuspended = false;


    @JsonIgnore
    @OneToMany(targetEntity = Deposit.class, mappedBy = "customer")
    private List<Deposit> deposits;

    @JsonIgnore
    @OneToMany(targetEntity = Withdraw.class, mappedBy = "customer")

    private List<Withdraw> withdraws;

    @JsonIgnore
    @OneToMany(targetEntity = Transfer.class, mappedBy = "sender")
    private List<Transfer> senders;

    @JsonIgnore
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Long getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Long create_by) {
        this.create_by = create_by;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Long getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Long update_by) {
        this.update_by = update_by;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Withdraw> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(List<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }

    public List<Transfer> getSenders() {
        return senders;
    }

    public void setSenders(List<Transfer> senders) {
        this.senders = senders;
    }

    public List<Transfer> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Transfer> recipients) {
        this.recipients = recipients;
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
                ", isSuspended=" + isSuspended +
                ", deposits=" + deposits +
                ", withdraws=" + withdraws +
                ", senders=" + senders +
                ", recipients=" + recipients +
                '}';
    }
}
