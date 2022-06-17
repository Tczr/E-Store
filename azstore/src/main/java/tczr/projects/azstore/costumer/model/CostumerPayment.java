package tczr.projects.azstore.costumer.model;

import tczr.projects.azstore.payment.model.Payment;

import java.time.LocalDate;
import java.util.Objects;

public class CostumerPayment  extends Payment {

    private Integer user_id;
    private String account_no;

    private LocalDate expiry;


    public CostumerPayment(String account_no, LocalDate expiry) {
        this.account_no = account_no;
        this.expiry = expiry;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostumerPayment that = (CostumerPayment) o;
        return Objects.equals(user_id, that.user_id)
                && Objects.equals(account_no, that.account_no)
                && Objects.equals(expiry, that.expiry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, account_no, expiry);
    }

    @Override
    public String toString() {
        return "CostumerPayment{" +
                "user_id=" + user_id +
                ", account_no='" + account_no + '\'' +
                ", expiry=" + expiry +
                '}';
    }
}
