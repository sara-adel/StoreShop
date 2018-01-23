package com.sara.project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sara on 1/23/2018.
 */

public class Helper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "StoreShop";
    private static final int DATABASE_VERSION = 1;

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //table of user
    public static final String Table_users = "users";
    public static final String Column_id = "id";
    public static final String Column_email = "email";

    String CREATE_USER_TABLE = "CREATE TABLE " + Table_users + " ( " + Column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_email + " TEXT NOT NULL );" ;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_users);
        onCreate(db);
    }
}
