package com.sara.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sara on 1/23/2018.
 */

public class DBController {

    Helper help;
    Context context;
    SQLiteDatabase data ;

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

    //insert data to tasks table
    public long insert_intoUsers(String email){
        openDB();
        ContentValues values = new ContentValues();
        values.put(Helper.Column_email , email);

        long result = data.insert(Helper.Table_users ,null , values);
        return result;
    }

}
