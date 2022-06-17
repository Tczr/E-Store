package tczr.projects.azstore.shoppingcart.model;

import tczr.projects.azstore.product.model.Product;
import tczr.projects.azstore.costumer.model.Costumer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {

    private Integer shopping_cart_id, quantity;
    private Costumer user;
    private List<Product> cart_products;
    private BigDecimal total;
    private LocalDateTime createdAt, modifiedAt, deletedAt;


    public ShoppingCart(Integer quantity, Costumer user, List<Product> cart_products, BigDecimal total, LocalDateTime createdAt) {
        this.quantity = quantity;
        this.user = user;
        this.cart_products = cart_products;
        this.total = total;
        this.createdAt = createdAt;
    }

    public ShoppingCart(Integer quantity, Costumer user, List<Product> cart_products, BigDecimal total, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.quantity = quantity;
        this.user = user;
        this.cart_products = cart_products;
        this.total = total;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Integer getShopping_cart_id() {
        return shopping_cart_id;
    }

    public void setShopping_cart_id(Integer shopping_cart_id) {
        this.shopping_cart_id = shopping_cart_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Costumer getUser() {
        return user;
    }

    public void setUser(Costumer user) {
        this.user = user;
    }

    public List<Product> getCart_products() {
        return cart_products;
    }

    public void setCart_products(List<Product> cart_products) {
        this.cart_products = cart_products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(shopping_cart_id, that.shopping_cart_id)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(user, that.user)
                && Objects.equals(cart_products, that.cart_products)
                && Objects.equals(total, that.total)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(modifiedAt, that.modifiedAt)
                && Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopping_cart_id, quantity, user, cart_products, total, createdAt, modifiedAt, deletedAt);
    }


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shopping_cart_id=" + shopping_cart_id +
                ", quantity=" + quantity +
                ", user=" + user +
                ", cart_products=" + cart_products +
                ", total=" + total +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
