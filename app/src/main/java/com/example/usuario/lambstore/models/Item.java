package com.example.usuario.lambstore.models;

/**
 * The representation of a Item (product)
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

    /**
     * The number of items if apply
     */
    private Integer number;

    public Item(String name, Integer price, String ean) {
        this.name = name;
        this.price = price;
        this.ean = ean;
        number = null;
    }

    public Item(String name, Integer price, String ean, Integer number) {
        this.name = name;
        this.price = price;
        this.ean = ean;
        this.number = number;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
