package com.example.usuario.lambstore.repository.mappers;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Interface to associates object fields with persistence values. It is an interface between item and persistence.
 */

public interface Mapper<T> {

    /**
     * Converts a item in ContentValues.
     * @param T item, th item to convert.
     * @return {@link ContentValues}, the object to use in persistence.
     */
    ContentValues getContentValues(T item);

    /**
     * Given a ContentValues, converts to T.
     * @param {@Link ContentValues} contentValues, the contentValues.
     * @return T item, the item from persistence.
     */
    T getItem(ContentValues contentValues);
    /**
     * Given a Cursor of database, converts to T.
     * @param {@Link Cursor} cursor, the cursor of database
     * @return T item, the item from database.
     */
    T getItem(Cursor cursor);
}
