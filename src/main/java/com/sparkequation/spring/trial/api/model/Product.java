package com.sparkequation.spring.trial.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @java.beans.ConstructorProperties({"id", "name", "featured", "expirationDate", "itemsInStock", "receiptDate", "rating", "brand", "categories"})
    public Product(Integer id, @Length(max = 100) String name, Boolean featured, Date expirationDate, Integer itemsInStock, Date receiptDate, Double rating, @NotNull Brand brand, @NotNull Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.featured = featured;
        this.expirationDate = expirationDate;
        this.itemsInStock = itemsInStock;
        this.receiptDate = receiptDate;
        this.rating = rating;
        this.brand = brand;
        this.categories = categories;
    }

    public Product() {
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PRODUCT_SEQ")
    @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1,initialValue = 30)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "ISFEATURED")
    public Boolean isFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }


    @Column(name = "EXPIRATIONDATE")
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    @Column(name = "ITEMSINSTOCK")
    public Integer getItemsInStock() {
        return itemsInStock;
    }

    public void setItemsInStock(Integer itemsInStock) {
        this.itemsInStock = itemsInStock;
    }


    @Column(name = "RECEIPTDATE")
    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }


    @Column(name = "RATING")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @ManyToOne(targetEntity = Brand.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "BRANDID", foreignKey = @ForeignKey(name = "fk_brand"))
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "CATEGORY_PRODUCT",
            joinColumns = {
                @JoinColumn(name = "PRODUCTID", referencedColumnName = "ID",
                    foreignKey = @ForeignKey(name = "fk_r_product_category"))},
            inverseJoinColumns = {
                @JoinColumn(name = "CATEGORYID", referencedColumnName = "ID",
                    foreignKey = @ForeignKey(name = "fk_r_category_product"))
            })
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    private Integer id;
    @Length(max = 100)
    private String name;
    private Boolean featured;
    private Date expirationDate;
    private Integer itemsInStock;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date receiptDate;
    private Double rating;
    @NotNull
    private Brand brand;
    @NotNull
    private Set<Category> categories;

    public static class ProductBuilder {
        private Integer id;
        private @Length(max = 100) String name;
        private Boolean featured;
        private Date expirationDate;
        private Integer itemsInStock;
        private Date receiptDate;
        private Double rating;
        private @NotNull Brand brand;
        private @NotNull Set<Category> categories;

        ProductBuilder() {
        }

        public Product.ProductBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public Product.ProductBuilder name(@Length(max = 100) String name) {
            this.name = name;
            return this;
        }

        public Product.ProductBuilder featured(Boolean featured) {
            this.featured = featured;
            return this;
        }

        public Product.ProductBuilder expirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Product.ProductBuilder itemsInStock(Integer itemsInStock) {
            this.itemsInStock = itemsInStock;
            return this;
        }

        public Product.ProductBuilder receiptDate(Date receiptDate) {
            this.receiptDate = receiptDate;
            return this;
        }

        public Product.ProductBuilder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Product.ProductBuilder brand(@NotNull Brand brand) {
            this.brand = brand;
            return this;
        }

        public Product.ProductBuilder categories(@NotNull Set<Category> categories) {
            this.categories = categories;
            return this;
        }

        public Product build() {
            return new Product(id, name, featured, expirationDate, itemsInStock, receiptDate, rating, brand, categories);
        }

        public String toString() {
            return "Product.ProductBuilder(id=" + this.id + ", name=" + this.name + ", featured=" + this.featured + ", expirationDate=" + this.expirationDate + ", itemsInStock=" + this.itemsInStock + ", receiptDate=" + this.receiptDate + ", rating=" + this.rating + ", brand=" + this.brand + ", categories=" + this.categories + ")";
        }
    }
}
