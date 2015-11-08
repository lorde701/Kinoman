package com.example.kinoman.Source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;

import com.example.kinoman.ClFrDwn.FilmObjectForDownload;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanka on 02.11.15.
 */
public class MovieDataBase {

    private final String LOG_TAG_DB = "workWithDataDase";

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
        if (dbh != null) {
            dbh.close();
        }
    }

    //public void onUpdataDB() {
    //    this.open();
    //    //dbh.onUpdata(db);
    //    this.close();
    //}

    public void addDatas(List<FilmObjectForDownload> films) {
        open();

        MyDelDatas();

        ContentValues cv = new ContentValues();

        DatasForDB datasForDB = new DatasForDB();

        String[] MT_img = datasForDB.TM_img;

        for (int i = 0; i < films.size(); ++i) {
            int id_movie;
            FilmObjectForDownload film = films.get(i);
            cv.put(dbh.TM_TITLE, film.getTitle());
            cv.put(dbh.TM_YEAR_RELEASE, film.getYear());
            //cv.put(dbh.TM_DIRECTOR, film.getDirector());
            cv.put(dbh.TM_DESCRIPTION, film.getDescription());
            cv.put(dbh.TM_ASSESSMENT, 11);
            cv.put(dbh.TM_FLAG, false);
            cv.put(dbh.TM_IMAGE, MT_img[i]);
            cv.put(dbh.TM_DIRECTOR, addDirector(film.getDirector()));
            id_movie = (int)db.insert(dbh.TABLE_NAME_MOVIE, null, cv);
            addLinkMovieGenre(id_movie, film.getGanres());
            addLinkMovieCountry(id_movie, film.getCountries());
            addLinkMovieActor(id_movie, film.getActors());
        }

        close();
    }

    public void DelDatas() {
        open();
        dbh.deleteTables(db);
        dbh.onCreate(db);
        close();
    }

    public void MyDelDatas() {
        dbh.deleteTables(db);
        dbh.onCreate(db);
    }

    public void checkMoves() {
        open();

        Cursor c;

        c = db.query(dbh.TABLE_NAME_MOVIE, null, null, null, null, null, null);
        logCursor(c);

        /*c = db.query(dbh.TABLE_NAME_DIRECTOR, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_GENRE, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_GENRE, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_COUNTRY, null, null, null, null, null, null);
        logCursor(c);*/

        c = db.query(dbh.TABLE_NAME_ACTORS, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_ACTORS, null, null, null, null, null, null);
        logCursor(c);


        close();
    }

    private int addDirector(String name_director) {
        int id;
        ContentValues cv = new ContentValues();
        Cursor c;

        String whatSelect = dbh.TD_NAME_DIRECTOR + " = \"" + name_director + "\"";

        c = db.query(dbh.TABLE_NAME_DIRECTOR, null, whatSelect, null, null, null, null);


        if(c.moveToFirst()) {
            int idIndex = c.getColumnIndex(dbh.TD_ID_DIRECTOR);
            id = c.getInt(idIndex);
        } else {
            cv.put(dbh.TD_NAME_DIRECTOR, name_director);
            id = (int) db.insert(dbh.TABLE_NAME_DIRECTOR, null, cv);
        }
        return id;
    }

    private int addGenre(String name_genre) {
        int id;
        ContentValues cv = new ContentValues();
        Cursor c;

        String whatSelect = dbh.TG_NAME_GENRE + " = \"" + name_genre + "\"";

        c = db.query(dbh.TABLE_NAME_GENRE, null, whatSelect, null, null, null, null);


        if(c.moveToFirst()) {
            int idIndex = c.getColumnIndex(dbh.TG_ID_GENRE);
            id = c.getInt(idIndex);
        } else {
            cv.put(dbh.TG_NAME_GENRE, name_genre);
            id = (int) db.insert(dbh.TABLE_NAME_GENRE, null, cv);
        }
        return id;
    }

    private void addLinkMovieGenre(int id_movie, List<String> genres) {

        ContentValues cv = new ContentValues();
        int id_genre;
        for(int i = 0; i < genres.size(); i++) {
            id_genre = addGenre(genres.get(i));
            cv.put(dbh.TLMG_ID_MOVIE, id_movie);
            cv.put(dbh.TLMG_ID_GENRE, id_genre);
            db.insert(dbh.TABLE_NAME_LINK_MOVIE_GENRE, null, cv);
        }
    }

    private int addCountry(String name_country) {
        int id;
        ContentValues cv = new ContentValues();
        Cursor c;

        String whatSelect = dbh.TC_NAME_COUNTRY + " = \"" + name_country + "\"";

        c = db.query(dbh.TABLE_NAME_COUNTRY, null, whatSelect, null, null, null, null);


        if(c.moveToFirst()) {
            int idIndex = c.getColumnIndex(dbh.TC_ID_COUNTRY);
            id = c.getInt(idIndex);
        } else {
            cv.put(dbh.TC_NAME_COUNTRY, name_country);
            id = (int) db.insert(dbh.TABLE_NAME_COUNTRY, null, cv);
        }
        return id;
    }

    private void addLinkMovieCountry(int id_movie, List<String> countries) {

        ContentValues cv = new ContentValues();
        int id_country;
        for(int i = 0; i < countries.size(); i++) {
            id_country = addCountry(countries.get(i));
            cv.put(dbh.TLMC_ID_MOVIE, id_movie);
            cv.put(dbh.TLMC_ID_COUNTRY, id_country);
            db.insert(dbh.TABLE_NAME_LINK_MOVIE_COUNTRY, null, cv);
        }
    }

    private int addActor(String name_actor) {
        int id;
        ContentValues cv = new ContentValues();
        Cursor c;

        String whatSelect = dbh.TA_NAME_ACTORS + " = \"" + name_actor + "\"";

        c = db.query(dbh.TABLE_NAME_ACTORS, null, whatSelect, null, null, null, null);


        if(c.moveToFirst()) {
            int idIndex = c.getColumnIndex(dbh.TA_ID_ACTORS);
            id = c.getInt(idIndex);
        } else {
            cv.put(dbh.TA_NAME_ACTORS, name_actor);
            id = (int) db.insert(dbh.TABLE_NAME_ACTORS, null, cv);
        }
        return id;
    }

    private void addLinkMovieActor(int id_movie, List<String> actors) {

        ContentValues cv = new ContentValues();
        int id_actor;
        for(int i = 0; i < actors.size(); i++) {
            id_actor = addActor(actors.get(i));
            cv.put(dbh.TLMA_ID_MOVIE, id_movie);
            cv.put(dbh.TLMA_ID_ACTOR, id_actor);
            db.insert(dbh.TABLE_NAME_LINK_MOVIE_ACTORS, null, cv);
        }
    }

    private void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    Log.d(LOG_TAG_DB, "-----ITEM FROM DATABASE-----");
                    //str = "";
                    for (String cn : c.getColumnNames()) {
                        Log.d(LOG_TAG_DB, cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                        //str = str.concat("\n" + cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    //Log.d(LOG_TAG_DB, str);
                    Log.d(LOG_TAG_DB, "----------------------------\n");
                } while (c.moveToNext());
            }
        } else
            Log.d(LOG_TAG_DB, "Cursor is null");
    }

}