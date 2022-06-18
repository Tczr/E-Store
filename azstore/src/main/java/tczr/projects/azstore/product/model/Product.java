package tczr.projects.azstore.product.model;

import tczr.projects.azstore.category.model.Category;
import tczr.projects.azstore.discount.Discount;
import tczr.projects.azstore.inventory.Inventory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Product {

    private Integer product_id;
    private String product_name, product_details;
    private boolean product_availability, product_unlimited;
    private float product_stock, product_weight;
    private List  images;
    private Discount discount;
    private Category category;
    private Inventory inventory;
    private BigDecimal price;
    private BigDecimal priceAfterDiscount;
    private LocalDateTime insertedAt,  modifiedAt;


    public Product() {}

    public Product(String product_name, String product_details, LocalDateTime insertedAt, Discount discount, Category category) {
        this.product_name = product_name;
        this.product_details = product_details;
        this.insertedAt = insertedAt;
        this.discount=discount;
        this.category = category;
    }

    public Product(String product_name, String product_details, LocalDateTime insertedAt, Discount discount, Category category, Inventory inventory) {
        this.product_name = product_name;
        this.product_details = product_details;
        this.insertedAt = insertedAt;
        this.discount=discount;
        this.category = category;
        this.inventory = inventory;
    }

    public Product(String product_name,
                   String product_details,
                   boolean product_availability,
                   boolean product_unlimited,
                   float product_stock,
                   float product_weight,
                   LocalDateTime insertedAt,
                   Discount discount,
                   Category category,
                   Inventory inventory) {
        this.product_name = product_name;
        this.product_details = product_details;
        this.product_availability = product_availability;
        this.product_unlimited = product_unlimited;
        this.product_stock = product_stock;
        this.product_weight = product_weight;
        this.insertedAt = insertedAt;
        this.discount=discount;
        this.category = category;
        this.inventory = inventory;
    }


    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {
        this.price = price;

        // set only if the PriceAfterDiscount hasn't been set from another method
        if(!discountedPrice()) {setPriceAfterDiscount();}

    }

    private void setPriceAfterDiscount(){
        if(hasDiscount() && hasPrice()){
            float discount_percent =discount.getDiscount_percent();
            if(discount_percent>1 && discount_percent<=100){
                discount_percent/=100;}

            BigDecimal amountToSub = price.multiply(new BigDecimal(discount_percent)).round(new MathContext(13));
            System.out.println(amountToSub);
            priceAfterDiscount=price.subtract(amountToSub).round(new MathContext(7, RoundingMode.CEILING));
            System.out.println(priceAfterDiscount);
            }
    }

    private boolean discountedPrice(){return priceAfterDiscount!=null;}

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;

        // set only if the PriceAfterDiscount hasn't been set from another method
        if(!discountedPrice()) {setPriceAfterDiscount();}
    }

    private boolean hasDiscount(){
        return discount!=null;
    }
    private boolean hasPrice(){ return price!=null;}

    public String getProduct_details() {
        return product_details;
    }

    public void setProduct_details(String product_details) {
        this.product_details = product_details;
    }

    public boolean getProduct_availability() {
        return product_availability;
    }

    public void setProduct_availability(boolean product_availability) {
        this.product_availability = product_availability;
    }

    public boolean getProduct_unlimited() {
        return product_unlimited;
    }

    public void setProduct_unlimited(boolean product_unlimited) {
        this.product_unlimited = product_unlimited;
    }

    public float getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(float product_stock) {
        this.product_stock = product_stock;
    }

    public float getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(float product_weight) {
        this.product_weight = product_weight;
    }

    public LocalDateTime getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(LocalDateTime insertedAt) {
        this.insertedAt = insertedAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory() {
        this.inventory = inventory;
    }

    public List getImages() {return  images;}

    public void setImages(List  images) {
        this. images =  images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_availability == product.product_availability
                && product_unlimited == product.product_unlimited
                && Float.compare(product.product_stock, product_stock) == 0
                && Float.compare(product.product_weight, product_weight) == 0
                && Objects.equals(product_id, product.product_id)
                && Objects.equals(product_name, product.product_name)
                && Objects.equals(product_details, product.product_details)
                && Objects.equals(insertedAt, product.insertedAt)
                && Objects.equals(modifiedAt, product.modifiedAt)
                && Objects.equals(category, product.category)
                && Objects.equals(inventory, product.inventory)
                && Objects.equals( images, product. images)
                && Objects.equals(price, product.price)
                && Objects.equals(priceAfterDiscount, product.priceAfterDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, product_name, product_details, product_availability,
                product_unlimited, product_stock, product_weight, insertedAt, modifiedAt,
                category, inventory,  images, price, priceAfterDiscount);
    }


    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_details='" + product_details + '\'' +
                ", product_availability=" + product_availability +
                ", product_unlimited=" + product_unlimited +
                ", product_stock=" + product_stock +
                ", product_weight=" + product_weight +
                ", images=" + images +
                ", discount=" + discount +
                ", category=" + category +
                ", inventory=" + inventory +
                ", price=" + price +
                ", priceAfterDiscount=" + priceAfterDiscount +
                ", insertedAt=" + insertedAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

}

