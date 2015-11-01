package com.example.kinoman.Source;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ivanka on 02.11.15.
 */
public class MovieDataBase {

    private final Context context;
    private DBHelper dbh;
    private SQLiteOpenHelper db;

    public MovieDataBase(Context context) {
        this.context = context;
    }

    public void open() {
        dbh = new DBHelper(context);
//        db = dbh.getWritableDatabase(); !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public void close() {
        if (dbh != null ) {
            dbh.close();
        }
    }

    //public void onUpdataDB() {
   //     this.open();
   //     dbh.onUpdata(db);
   //     this.close();
   // }


}
