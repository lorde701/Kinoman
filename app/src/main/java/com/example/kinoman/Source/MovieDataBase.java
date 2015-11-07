package com.example.kinoman.Source;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanka on 02.11.15.
 */
public class MovieDataBase {

    private final String LOG_TAG_DB = "mainList";

    private final Context context;
    private DBHelper dbh;
    private SQLiteDatabase db;

    public MovieDataBase(Context context) {

        this.context = context;
    }

    public void open() {
        dbh = new DBHelper(context);
        db = dbh.getWritableDatabase();
    }

    public void close() {
        if (dbh != null ) {
            dbh.close();
        }
    }

    //public void onUpdataDB() {
    //    this.open();
    //    //dbh.onUpdata(db);
    //    this.close();
    //}

    public List<Movie> getListMovies() {
        this.open();
        List<Movie> list = new ArrayList<>();

        Cursor c;

        c = db.query(dbh.TABLE_NAME_MOVIE, null, null, null, null, null, null);

        logCursor(c);

        int ColIndexMovie = c.getColumnIndex(dbh.TM_ID_MOVIE);
        int ColIndexTitle = c.getColumnIndex(dbh.TM_TITLE_MOVIE);
        int ColIndexYear = c.getColumnIndex(dbh.TM_YEAR_RELEASE_MOVIE);


        int intIdColIndex = c.getColumnIndex(dbh.INT_ID_COLUMN_TNSO);
        int imgColIndex = c.getColumnIndex(dbh.IMG_COLUMN_TNSO);


        if(c.moveToFirst()) {
            do {
                Movie temp = new Movie(c.getString());
                list.add(temp);
            }
            while (c.moveToNext());
        }

        c.close();
        this.close();
        return list;

    }



    private void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
               String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString( c.getColumnIndex(cn)) +  "; ");
                    }
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                } while (c.moveToNext());
            }
        } else
            ;
            //!!!!!!!!!!!!!!!!!!!

    }


}
