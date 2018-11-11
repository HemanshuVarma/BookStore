package com.varma.hemanshu.bookstore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.varma.hemanshu.bookstore.data.BookContract.BookEntry;

/**
 * Method for SQL connection
 */
public class BookDbHelper extends SQLiteOpenHelper {

    //Database name and Version Strings used for SQLite db
    private static final String DATABASE_NAME = "books.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_BOOK_ENTRY =
            "CREATE TABLE " + BookEntry.TABLE_NAME + "("
                    + BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BookEntry.COLUMN_BOOK_PRODUCT_NAME + " TEXT NOT NULL, "
                    + BookEntry.COLUMN_BOOK_PRICE + " INTEGER NOT NULL, "
                    + BookEntry.COLUMN_BOOK_QUANTITY + " INTEGER NOT NULL DEFAULT 1, "
                    + BookEntry.COLUMN_BOOK_SUPPLIER_NAME + " TEXT NOT NULL, "
                    + BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NO + " TEXT);";

    //Constructor of DbHelper Method
    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates an db if not present,
        // Else returns the reference of it.
        db.execSQL(SQL_CREATE_BOOK_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do Nothing as of Now.
    }
}
