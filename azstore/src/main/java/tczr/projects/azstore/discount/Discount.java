package tczr.projects.azstore.discount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Discount {
    private Integer discount_id;
    private String discount_name, discount_description;
    private Float discount_percent;
    private boolean discount_activity;
    private LocalDateTime createdAt, modifiedAt,finishedAt;

    public Discount(){}
    public Discount(String discount_name, Float discount_percent, LocalDateTime createdAt)
    {
        this.discount_name=discount_name;
        this.discount_percent=discount_percent;
        this.createdAt=createdAt;
    }

    public Discount(String discount_name, String discount_description, Float discount_percent, boolean discount_activity, LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime finishedAt) {
        this.discount_name = discount_name;
        this.discount_description = discount_description;
        this.discount_percent = discount_percent;
        this.discount_activity = discount_activity;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.finishedAt = finishedAt;
    }

    public Integer getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(Integer discount_id) {
        this.discount_id = discount_id;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public String getDiscount_description() {
        return discount_description;
    }

    public void setDiscount_description(String discount_description) {
        this.discount_description = discount_description;
    }

    public Float getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(Float discount_percent) {
        this.discount_percent = discount_percent;
    }

    public boolean isDiscount_activity() {
        return discount_activity;
    }

    public void setDiscount_activity(boolean discount_activity) {
        this.discount_activity = discount_activity;
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

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Float.compare(discount.discount_percent, discount_percent) == 0 && discount_activity == discount.discount_activity && Objects.equals(discount_id, discount.discount_id) && Objects.equals(discount_name, discount.discount_name) && Objects.equals(discount_description, discount.discount_description) && Objects.equals(createdAt, discount.createdAt) && Objects.equals(modifiedAt, discount.modifiedAt) && Objects.equals(finishedAt, discount.finishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount_id, discount_name, discount_description, discount_percent, discount_activity, createdAt, modifiedAt, finishedAt);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discount_id=" + discount_id +
                ", discount_name='" + discount_name + '\'' +
                ", discount_description='" + discount_description + '\'' +
                ", discount_percent=" + discount_percent +
                ", discount_activity=" + discount_activity +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", finishedAt=" + finishedAt +
                '}';
    }
}
