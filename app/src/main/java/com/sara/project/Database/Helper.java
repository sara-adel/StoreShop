package com.sara.project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sara on 1/23/2018.
 */

public class Helper extends SQLiteOpenHelper{

    //table of User
    public static final String Table_users = "users";
    public static final String Column_id = "id";
    public static final String Column_email = "email";
    //table of Store
    public static final String Table_store = "store";
    public static final String Column_id_store = "store_id";
    public static final String Column_storeName = "name";
    public static final String Column_location = "location";
    public static final String Column_user_id = "user_id";
    //table of Items
    public static final String Table_item = "item";
    public static final String Column_id_item = "item_id";
    public static final String Column_itemName = "item_name";
    public static final String Column_quantity = "quantity";
    public static final String Column_category = "category";
    public static final String Column_date = "date";
    public static final String Column_note = "note";
    public static final String Column_store_name = "store_name";
    private static final String DATABASE_NAME = "store_shop";
    private static final int DATABASE_VERSION = 2;
    String CREATE_USER_TABLE = "CREATE TABLE " + Table_users + " ( " + Column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_email + " TEXT NOT NULL );" ;
    String CREATE_STORE_TABLE = "CREATE TABLE " + Table_store + " ( " + Column_id_store + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_storeName + " TEXT NOT NULL, " +
            Column_location + " TEXT NOT NULL, " +
            Column_user_id + " INT, " +
            " FOREIGN KEY( " + Column_user_id + ") REFERENCES " + Table_users + " (id) " + " );";
    String CREATE_ITEM_TABLE = "CREATE TABLE " + Table_item + " ( " + Column_id_item + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_itemName + " TEXT NOT NULL, " +
            Column_quantity + "INT, " +
            Column_category + " TEXT NOT NULL, " +
            Column_date + " TEXT NOT NULL," +
            Column_note + " TEXT NOT NULL, " +
            Column_store_name + " TEXT NOT NULL, " +
            " FOREIGN KEY( " + Column_store_name + ") REFERENCES " + Table_store + " (name) " + " );";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_STORE_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_users);
        db.execSQL("DROP TABLE IF EXISTS " + Table_store);
        db.execSQL("DROP TABLE IF EXISTS " + Table_item);

        onCreate(db);
    }
}
