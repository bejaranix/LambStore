package com.example.usuario.lambstore.repository.mappers.impl;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.usuario.lambstore.models.Item;
import com.example.usuario.lambstore.repository.mappers.Mapper;

/**
 * Item's Mapper implementation.
 */

public class ItemMapper implements Mapper<Item>{

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String PRICE = "PRICE";
    public static final String EAN = "EAN";
    public static final String TABLE_NAME = "Item";



    @Override
    public ContentValues getContentValues(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,item.getId());
        contentValues.put(NAME,item.getName());
        contentValues.put(PRICE,item.getPrice());
        contentValues.put(EAN,item.getEan());
        return contentValues;
    }

    @Override
    public Item getItem(ContentValues contentValues) {
        Long id = (Long) contentValues.get(ID);
        String name = (String) contentValues.get(NAME);
        Integer price = (Integer) contentValues.get(PRICE);
        String ean = (String) contentValues.get(EAN);
        Item item = new Item(name,price,ean);
        item.setId(id);
        return item;
    }

    @Override
    public Item getItem(Cursor cursor) {
        Integer idColumn = cursor.getColumnIndex(ID);
        Integer nameColumn = cursor.getColumnIndex(NAME);
        Integer priceColumn = cursor.getColumnIndex(PRICE);
        Integer eanColumn = cursor.getColumnIndex(EAN);
        Long id = cursor.getLong(idColumn);
        String name = cursor.getString(nameColumn);
        Integer price = cursor.getInt(priceColumn);
        String ean = cursor.getString(eanColumn);
        Item item = new Item(name,price,ean);
        item.setId(id);
        return item;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    /**
     * Gets the columns of the table.
     *
     * @return {@link String[]}, the columns.
     */
    @Override
    public String[] getColumnNames() {
        return new String[]{ID,NAME,PRICE,EAN};
    }
}
