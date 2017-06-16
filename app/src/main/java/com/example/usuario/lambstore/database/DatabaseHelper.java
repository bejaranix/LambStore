package com.example.usuario.lambstore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Creates the SQLiteOpenHelper implementation.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LambStore.db";
    private static final Integer VER_1 = 3;
    private static final Integer DATABASE_VESION = VER_1;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VESION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Item (" +
                "    ID integer PRIMARY KEY AUTOINCREMENT," +
                "    NAME text NOT NULL," +
                "    PRICE integer," +
                "    EAN text," +
                "    IMAGE_URL text" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Item;");
        onCreate(db);
    }
}
