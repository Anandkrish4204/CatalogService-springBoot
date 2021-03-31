package com.grocery.catalog;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotNull
    private String title;

    @NotNull
    private float price;

    @NotNull
    private String imageUrl;

    @NotNull
    private String desc;

    @NotNull
    private CATEGORY category;

    private Date createdAt;

    public Product() {
    }

    public static enum CATEGORY{
        FV,BVR,SNK,DAIRY
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    void setCreatedAt(){
        this.createdAt = new Date();
    }
}
