package com.example.usuario.lambstore.models;

import java.util.Date;

/**
 * Represents the Item when is used to be part of a Purchase
 */

public class TransactionItem implements IdModel{

    /**
     * The id of the TransactionItem
     */
    private Integer id;

    /**
     * The item that represents.
     */
    private Item item;

    /**
     * The number of items in the transaction
     */
    private Integer number;

    /**
     * The price of the item when transaction was done.
     */
    private Integer priceTransaction;

    /**
     * The transaction id
     */
    private Integer transactionId;

    /**
     * Date of the transaction.
     */
    private Date date;

    public TransactionItem(Integer id, Item item, Integer number, Integer priceTransaction, Integer transactionId, Date date) {
        this.id = id;
        this.item = item;
        this.number = number;
        this.priceTransaction = priceTransaction;
        this.transactionId = transactionId;
        this.date = date;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPriceTransaction() {
        return priceTransaction;
    }

    public void setPriceTransaction(Integer priceTransaction) {
        this.priceTransaction = priceTransaction;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TransactionItem){
            TransactionItem item = (TransactionItem)obj;
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
