package tczr.projects.azstore.costumer.model;


import org.apache.tomcat.jni.Address;
import tczr.projects.azstore.shared.Account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Costumer extends Account {
    private CostumerType type;
    private List<Address> userAddresses;
    private List<CostumerPayment> userPayments;

    public Costumer(){
        super();
        this.setCreatedAt(LocalDateTime.now());

    }
    public Costumer(String email, String userName, String password) {
       super(email, userName, password);
       this.setCreatedAt(LocalDateTime.now());
    }

    public Costumer(String email, String userName, String phone, String password, String fullname) {
        super(email, userName, phone, password, fullname);
        this.setCreatedAt(LocalDateTime.now());
    }

    public Costumer(String email, String userName, String password, List<Address> userAddresses, List<CostumerPayment> userPayments) {
        super(email, userName, password);
        this.setCreatedAt(LocalDateTime.now());
        this.userAddresses=userAddresses;
        this.userPayments=userPayments;
    }

    public CostumerType getType() {
        return type;
    }

    public void setType(CostumerType type) {
        this.type = type;
    }

    public List<Address> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<Address> userAddresses) {
        this.userAddresses = userAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costumer costumer = (Costumer) o;
        return super.equals(o) && type == costumer.type
                && Objects.equals(userAddresses, costumer.userAddresses)
                && Objects.equals(userPayments, costumer.userPayments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, userAddresses, userPayments);
    }

    public List<CostumerPayment> getUserPayments() {
        return userPayments;
    }

    public void setUserPayments(List<CostumerPayment> userPayments) {
        this.userPayments = userPayments;
    }

    @Override
    public String toString() {
        return super.toString()+ "Costumer{" +
                "type=" + type +
                ", userAddresses=" + userAddresses +
                ", userPayments=" + userPayments +
                '}';
    }
}
