package tczr.projects.azstore.inventory;

import java.time.LocalDateTime;
import java.util.Objects;

public class Inventory {

    private Integer inventory_id;
    private String inventory_name;
    private Integer quantity;
    private LocalDateTime createdAt, modifiedAt, deletedAt;

    public  Inventory(){}
    public Inventory(String inventory_name, Integer quantity, LocalDateTime createdAt) {
        this.inventory_id = inventory_id;
        this.inventory_name = inventory_name;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public Integer getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getInventory_name() {
        return inventory_name;
    }

    public void setInventory_name(String inventory_name) {
        this.inventory_name = inventory_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        Inventory inventory = (Inventory) o;
        return Objects.equals(inventory_id, inventory.inventory_id)
                && Objects.equals(inventory_name, inventory.inventory_name)
                && Objects.equals(quantity, inventory.quantity)
                && Objects.equals(createdAt, inventory.createdAt)
                && Objects.equals(modifiedAt, inventory.modifiedAt)
                && Objects.equals(deletedAt, inventory.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory_id, inventory_name, quantity, createdAt, modifiedAt, deletedAt);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventory_id=" + inventory_id +
                ", inventory_name='" + inventory_name + '\'' +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
