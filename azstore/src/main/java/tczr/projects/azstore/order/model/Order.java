package tczr.projects.azstore.order.model;

import tczr.projects.azstore.product.model.Product;
import tczr.projects.azstore.costumer.model.Costumer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {

    private Integer order_id;
    private Costumer costumer;

    private OrderPayment payment;

    private List<Product> productList;

    private LocalDateTime createdAt, modifiedAt;

    public Order(){}
    public Order(Costumer costumer, OrderPayment payment, List<Product> productList, LocalDateTime createdAt) {
        this.costumer = costumer;
        this.payment = payment;
        this.productList = productList;
        this.createdAt = createdAt;
    }


    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public OrderPayment getPayment() {
        return payment;
    }

    public void setPayment(OrderPayment payment) {
        this.payment = payment;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(order_id, order.order_id)
                && Objects.equals(costumer, order.costumer)
                && Objects.equals(payment, order.payment)
                && Objects.equals(productList, order.productList)
                && Objects.equals(createdAt, order.createdAt)
                && Objects.equals(modifiedAt, order.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, costumer, payment, productList, createdAt, modifiedAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", costumer=" + costumer +
                ", payment=" + payment +
                ", productList=" + productList +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
