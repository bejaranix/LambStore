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

    public Item(String name, Integer price, String ean) {
        this.name = name;
        this.price = price;
        this.ean = ean;
    }

    public Item(Long id, String name, Integer price, String ean) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ean = ean;
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ean='" + ean + '\'' +
                '}';
    }
}
