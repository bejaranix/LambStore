package com.example.usuario.lambstore.models;

/**
 * Created by usuario on 30/05/17.
 */

public class Item {

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
}
