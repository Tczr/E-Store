package tczr.projects.azstore.order.model;

import tczr.projects.azstore.payment.model.Payment;

import java.math.BigDecimal;

public class OrderPayment extends Payment {

   private  BigDecimal amount;

    public OrderPayment() {}

    public OrderPayment(BigDecimal amount) {
        this.amount = amount;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return super.toString() + "PaymentDetails{" +
                "amount=" + amount +
                '}';
    }
}
