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
    private Integer id;

    /**
     * The items in the purchasing.
     */
    private List<TransactionItem> items;

    public Purchase(Date date, Integer id, List<TransactionItem> items) {
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
    public Integer getId() {
        return null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Purchase){
            Purchase purchase = (Purchase)obj;
            if(purchase.getId().equals(this.getId())){
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
