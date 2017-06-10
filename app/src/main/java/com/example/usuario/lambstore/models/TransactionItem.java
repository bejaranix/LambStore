package com.example.usuario.lambstore.models;

import java.util.Date;

/**
 * Represents the Item when is used to be part of a Purchase
 */

public class TransactionItem implements IdModel, NameModel{

    /**
     * The id of the TransactionItem
     */
    private Long id;

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
    private Long transactionId;

    /**
     * Date of the transaction.
     */
    private Date date;

    public TransactionItem(Long id, Item item, Integer number, Integer priceTransaction, Long transactionId, Date date) {
        this.id = id;
        this.item = item;
        this.number = number;
        this.priceTransaction = priceTransaction;
        this.transactionId = transactionId;
        this.date = date;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionItem{" +
                "id=" + id +
                ", item=" + item +
                ", number=" + number +
                ", priceTransaction=" + priceTransaction +
                ", transactionId=" + transactionId +
                ", date=" + date +
                '}';
    }

    /**
     * Returns the name field.
     *
     * @return {@link String}name, the name field.
     */
    @Override
    public String getName() {
        return item.getName();
    }
}
