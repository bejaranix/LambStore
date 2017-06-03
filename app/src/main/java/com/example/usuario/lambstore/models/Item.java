package com.example.usuario.lambstore.models;

/**
 * The representation of a Item (product)
 */
public class Item implements IdModel{

    /**
     * Id of item
     */
    private Integer id;

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

    public Item(Integer id, String name, Integer price, String ean) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ean = ean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public boolean equals(Object obj) {
        if(obj instanceof Item){
            Item item = (Item)obj;
            if(item.getId().equals(this.getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}
