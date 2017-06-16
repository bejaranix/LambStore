package com.example.usuario.lambstore.models;

import java.io.Serializable;

/**
 * The representation of a Item (product)
 */
public class Item implements IdModel, NameModel, Serializable{

    /**
     * Id of item
     */
    private Long id;

    /**
     * The item name
     */
    private String name;

    /**
     * The price of product * 100
     */
    private Integer price;

    /**
     * The barcode value
     */
    private String ean;

    /**
     * The photo captured of product.
     */
    private String imageURL;

    public Item(String name, Integer price, String ean, String imageURL) {
        this.name = name;
        this.price = price;
        this.ean = ean;
        this.imageURL=imageURL;
    }

    public Item(Long id, String name, Integer price, String ean, String imageURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ean = ean;
        this.imageURL=imageURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ean='" + ean + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
