package com.example.usuario.lambstore.repository.mappers.impl;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.models.TransactionItem;
import com.example.usuario.lambstore.repository.mappers.Mapper;

import java.util.Date;

/**
 * TransactionItem's Mapper implementation.
 */

public class TransactionItemMapper implements Mapper<TransactionItem> {

    public static final String ID = "ID";
    public static final String NUMBER = "NUMBER";
    public static final String PRICE_TRANSACTION = "PRICE_TRANSACTION";
    public static final String TRANSACTION_ID = "TRANSACTION_ID";
    public static final String DATE = "DATE";

    @Override
    public ContentValues getContentValues(TransactionItem item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,item.getId());
        contentValues.put(NUMBER,item.getNumber());
        contentValues.put(PRICE_TRANSACTION,item.getPriceTransaction());
        contentValues.put(TRANSACTION_ID,item.getTransactionId());
        contentValues.put(DATE,item.getDate().getTime());
        return contentValues;
    }

    @Override
    public TransactionItem getItem(ContentValues contentValues) {
        Integer id = (Integer) contentValues.get(ID);
        Integer number = (Integer) contentValues.get(NUMBER);
        Integer priceTransaction= (Integer) contentValues.get(PRICE_TRANSACTION);
        Integer transactionId= (Integer) contentValues.get(TRANSACTION_ID);
        Date date= new Date((long)contentValues.get(DATE));
        return new TransactionItem(id,null,number,priceTransaction,transactionId,date);
    }

    @Override
    public TransactionItem getItem(Cursor cursor) {
        Integer idColumn = cursor.getColumnIndex(ID);
        Integer numberColumn = cursor.getColumnIndex(NUMBER);
        Integer priceTransactionColumn = cursor.getColumnIndex(PRICE_TRANSACTION);
        Integer transactionIdColumn = cursor.getColumnIndex(TRANSACTION_ID);
        Integer dateColumn = cursor.getColumnIndex(DATE);
        Integer id = cursor.getInt(idColumn);
        Integer number = cursor.getInt(numberColumn);
        Integer priceTransaction= cursor.getInt(priceTransactionColumn);
        Integer transactionId= cursor.getInt(transactionIdColumn);
        Date date= new Date(cursor.getInt(dateColumn));
        TransactionItem item = new TransactionItem(id,null,number,priceTransaction,transactionId,date);
        return item;
    }

}
