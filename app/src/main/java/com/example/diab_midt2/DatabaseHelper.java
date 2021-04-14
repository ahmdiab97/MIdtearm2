package com.example.diab_midt2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "Name";
    public static final String COL3 = "email";
    public static final String COL4 = "phoneNumber";
    //public static final String COL4 = "Unused";

    /* Constructor */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY, " +
                " Name TEXT, email TEXT, phoneNumber TEXT)";

        db.execSQL(createTable);
    }

    /* Every time the dB is updated (or upgraded) */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /* Basic function to add data. REMEMBER: The fields
       here, must be in accordance with those in
       the onCreate method above.
    */
    public boolean addData(int id,String name, String email, String phoneNumber) {
        long result;

        Cursor cur=getSpecifiedResultByID(id);

        if(cur.getCount()==1)
            return false;
        else {


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, id);
            contentValues.put(COL2, name);
            contentValues.put(COL3, email);
            contentValues.put(COL4, phoneNumber);

            result = db.insert(TABLE_NAME, null, contentValues);
        }


        //if data are inserted incorrectly, it will return -1
        if(result == -1 ) {
            return false;
        } else {
            return true;
        }
    }

    /* Returns only one result */
    public Cursor structuredQuery(int ID) {
        SQLiteDatabase db = this.getReadableDatabase(); // No need to write
        Cursor cursor = db.query(
                TABLE_NAME, new String[]{COL1,
                        COL2, COL3,COL4}, COL1 + "=?",
                new String[]{String.valueOf(ID)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    //TODO: Students need to try to fix this
    public Cursor getSpecificResult(String productName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where name=? ",new String[]{productName});
        return data;
    }

    public Cursor getSpecifiedResultByID(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where ID=?  ",new String[]{String.valueOf(ID)});
        return data;

    }

    // Return everything inside a specific table
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public int dltRow(String productName){

        SQLiteDatabase db = this.getWritableDatabase();
        int delete=0;

        long result= DatabaseUtils.queryNumEntries(db,TABLE_NAME,"name=?",new String[]{productName});

        if(result>=1)
            delete=db.delete(TABLE_NAME,"name=?",new String[]{productName});

        return delete;

    }

    public void update(int id,int productQuantity){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,productQuantity);

        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{String.valueOf(id)});

    }


}
