package com.sara.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sara on 1/23/2018.
 */

public class DBController {

    private Helper help;
    private Context context;
    private SQLiteDatabase data;

    public DBController(Context con) {
        this.context = con;
        help = new Helper(con);
    }

    public void openDB (){
        data = help.getWritableDatabase();
    }

    public void closeDB(){
        help.close();
    }

    //***************************************** operations into user table  *****************************************//

    //insert data to user table
    public long insert_intoUsers(String email){
        openDB();
        ContentValues values = new ContentValues();
        values.put(Helper.Column_email , email);

        long result = data.insert(Helper.Table_users ,null , values);
        return result;
    }

    //get email of user
    public String getEmailOfUser(String email) {
        openDB();
        String email_user = null;
        Cursor mCursor = null;
        try {
            mCursor = data.rawQuery("SELECT " + Helper.Column_email + " FROM " + Helper.Table_users + " WHERE "
                    + Helper.Column_email + " = '" + email + "'", null);
            if (mCursor != null && mCursor.moveToFirst()) {
                email_user = mCursor.getString(mCursor.getColumnIndex("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mCursor.close();
            closeDB();
        }
        return email_user;
    }

    //get id of users table
    public String getIdOfUser(String email) {
        openDB();
        int id = 0;
        String rec = null;

        Cursor mCursor = data.rawQuery("SELECT " + Helper.Column_id + " FROM " + Helper.Table_users + " WHERE "
                + Helper.Column_email + " = '" + email + "'", null);
        if (mCursor != null) {
            mCursor.moveToFirst();
            id = mCursor.getInt(0);
            rec = String.valueOf(id);
        }
        return rec;
    }

    //***************************************** operations into user table  *****************************************//

    //insert into store table
    public long insert_intoStore(String name, String location, int user_id) {
        openDB();
        ContentValues values = new ContentValues();
        values.put(Helper.Column_storeName, name);
        values.put(Helper.Column_location, location);
        values.put(Helper.Column_user_id, user_id);

        long result = data.insert(Helper.Table_store, null, values);
        return result;
    }

    //***************************************** operations into user table  *****************************************//

    //insert into item table
    public long insert_intoItems(String name, int quantity, String category, String date, String note, String store_name) {
        openDB();
        ContentValues values = new ContentValues();
        values.put(Helper.Column_storeName, name);
        values.put(Helper.Column_quantity, quantity);
        values.put(Helper.Column_category, category);
        values.put(Helper.Column_date, date);
        values.put(Helper.Column_note, note);
        values.put(Helper.Column_store_name, store_name);

        long result = data.insert(Helper.Table_store, null, values);
        return result;
    }


}
