package com.example.usuario.lambstore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Manages DatabaseHelper as a Singleton.
 */

public class DatabaseAdapter {

    /**
     * The DatabaseHelper.
     */
    private static DatabaseHelper mDbHelper;
    private static SQLiteDatabase mDb;

    /**
     * Creates the DatabaseHelper once.
     * @param {@link Context} context, the application's context.
     * @return boolean, returns if the database was created.
     */
    public static boolean openDB(Context context){
        if(mDbHelper != null)
            mDbHelper.close();
        mDbHelper = new DatabaseHelper(context);

        try {
            mDb = mDbHelper.getWritableDatabase();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets the DatabaseHelper, if it doesn't exists, creates a new one.
     * @param {@link Context} context, the application's context.
     * @return {@link DatabaseHelper}, the DatabaseHelper.
     */
    public static SQLiteDatabase getDB(Context context){
        if(mDb == null)
            openDB(context);
        return mDb;
    }


}
