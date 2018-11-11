package com.varma.hemanshu.bookstore;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.varma.hemanshu.bookstore.data.BookContract.BookEntry;
import com.varma.hemanshu.bookstore.data.BookDbHelper;

public class BookEditorActivity extends AppCompatActivity {

    EditText mProductName;
    EditText mPrice;
    EditText mQuantity;
    EditText mSupplierName;
    EditText mSupplierPhone;

    //String for Log
    private static final String LOG_TAG = BookEditorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_editor);

        mProductName = findViewById(R.id.book_name_et);
        mPrice = findViewById(R.id.price_et);
        mQuantity = findViewById(R.id.quantity_et);
        mSupplierName = findViewById(R.id.supplier_name_et);
        mSupplierPhone = findViewById(R.id.supplier_phone_et);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book_editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                insertData();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to insert data taken from EditText by User
     */
    private void insertData() {
        String productName = mProductName.getText().toString().trim();
        String priceString = mPrice.getText().toString().trim();
        int priceInt = Integer.parseInt(priceString);
        String quantity = mQuantity.getText().toString().trim();
        int quantityInt = Integer.parseInt(quantity);
        String supplierName = mSupplierName.getText().toString().trim();
        String supplierPhone = mSupplierPhone.getText().toString().trim();

        //Content Values for inserting into db
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_BOOK_PRODUCT_NAME, productName);
        values.put(BookEntry.COLUMN_BOOK_PRICE, priceInt);
        values.put(BookEntry.COLUMN_BOOK_QUANTITY, quantityInt);
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_NAME, supplierName);
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NO, supplierPhone);

        BookDbHelper mDbHelper = new BookDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Returns -1 if Data isn't inserted into Db
        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, getString(R.string.add_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.add_success) + newRowId, Toast.LENGTH_SHORT).show();
        }
        Log.i(LOG_TAG, getString(R.string.insert_data_success));
    }
}
