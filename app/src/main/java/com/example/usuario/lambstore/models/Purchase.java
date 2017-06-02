package com.example.usuario.lambstore.models;

import java.util.Date;
import java.util.List;

/**
 * The representation of a ticket of a purchasing.
 */
public class Purchase {

    /**
     * The date when the ticket was generated
     */
    private Date date;

    /**
     * The items in the purchasing.
     */
    private List<Item> items;

    public Purchase(Date date, List<Item> items) {
        this.date = date;
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
