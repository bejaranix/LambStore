package com.example.usuario.lambstore.models;

import java.util.Date;
import java.util.List;

/**
 * The representation of a ticket of a purchasing.
 */
public class Purchase implements IdModel{

    /**
     * The date when the ticket was generated
     */
    private Date date;

    /**
     * The transaction id
     */
    private Long id;

    /**
     * The items in the purchasing.
     */
    private List<TransactionItem> items;

    public Purchase(Date date, Long id, List<TransactionItem> items) {
        this.date = date;
        this.id = id;
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public List<TransactionItem> getItems() {
        return items;
    }

    public void setItems(List<TransactionItem> items) {
        this.items = items;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "date=" + date +
                ", id=" + id +
                ", items=" + items +
                '}';
    }
}
