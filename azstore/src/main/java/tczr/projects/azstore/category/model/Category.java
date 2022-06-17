package tczr.projects.azstore.category.model;

import java.time.LocalDateTime;
import java.util.List;

public class Category {
    private Integer category_id;
    private String category_name, category_description;
    private LocalDateTime cratedAt,modifiedAt;
    //join and search
    private List<Category> subCategories;
    Category(){}

    public Category(String category_name, String category_description, LocalDateTime cratedAt) {
        this.category_name = category_name;
        this.category_description = category_description;
        this.cratedAt = cratedAt;
    }

    public Category(String category_name,
                    String category_description,
                    LocalDateTime cratedAt,
                    LocalDateTime modifiedAt,
                    List<Category> subCategories) {
        this.category_name = category_name;
        this.category_description = category_description;
        this.cratedAt = cratedAt;
        this.modifiedAt = modifiedAt;
        this.subCategories = subCategories;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public LocalDateTime getCratedAt() {
        return cratedAt;
    }

    public void setCratedAt(LocalDateTime cratedAt) {
        this.cratedAt = cratedAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", category_description='" + category_description + '\'' +
                ", cratedAt=" + cratedAt +
                ", modifiedAt=" + modifiedAt +
                ", subCategories=" + subCategories +
                '}';
    }
}
