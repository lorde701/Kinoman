package com.example.kinoman.Source;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;
import java.util.Random;

/**
 * Created by ivanka on 09.11.15.
 */
public class Movie {
    private int m_Id;
    private String m_title;
    private String m_year;
    private String m_genre;
    private String m_countries;
    private String m_director;
    private String m_actors;
    private String m_description;
    private int m_assessment;
    private String m_img;
    private int m_flag;

    public int getM_Id() {
        return m_Id;
    }

    public void setM_Id(int m_Id) {
        this.m_Id = m_Id;
    }

    public String getM_title() {
        return m_title;
    }

    public void setM_title(String m_title) {
        this.m_title = m_title;
    }

    public String getM_year() {
        return m_year;
    }

    public void setM_year(String m_year) {
        this.m_year = m_year;
    }

    public String getM_genre() {
        return m_genre;
    }

    public void setM_genre(String m_genre) {
        this.m_genre = m_genre;
    }

    public String getM_countries() {
        return m_countries;
    }

    public void setM_countries(String m_countries) {
        this.m_countries = m_countries;
    }

    public String getM_director() {
        return m_director;
    }

    public void setM_director(String m_director) {
        this.m_director = m_director;
    }

    public String getM_actors() {
        return m_actors;
    }

    public void setM_actors(String m_actors) {
        this.m_actors = m_actors;
    }

    public String getM_description() {
        return m_description;
    }

    public void setM_description(String m_description) {
        this.m_description = m_description;
    }

    public int getM_assessment() {
        return m_assessment;
    }

    public void setM_assessment(int m_assessment) {
        this.m_assessment = m_assessment;
    }

    public String getM_img() {
        return m_img;
    }

    public void setM_img(String m_img) {
        this.m_img = m_img;
    }

    public int getM_flag() {
        return m_flag;
    }

    public void setM_flag(int m_flag) {
        this.m_flag = m_flag;
    }


    /*
    public String getTitle() { return m_title; }
    public String getYear() { return m_year; }
    public String getGenre() { return m_genre; }
    public String getCountries() { return m_countries; }
    public String getDirector() { return m_director; }
    public String getActors() { return m_actors; }
    public String getDescription() { return m_description; }
    public int getAssessment() { return m_assessment; }
    public String getImg() { return m_img; }
    public boolean getFlag() { return m_flag; }
*/
    /*
    Context context;
    private DBHelper dbh;
    private SQLiteDatabase db;
    private final String LOG_TAG_DB = "workWithMovie";


    public Movie(Context context) { this.context = context; }

    public void selectTitleMovie() {
        open();
        String str = "";
        Cursor c;

        String selectCount = "select count(*) from " + dbh.TABLE_NAME_MOVIE + ";";
        c = db.rawQuery(selectCount, new String[]{});

        //logCursor(c);
        c.moveToFirst();
        int count = c.getInt(c.getColumnIndex("count(*)"));

        Random rand = new Random();
        m_Id = rand.nextInt(count - 1) + 1;



        String select = dbh.TM_ID + " = \"" + m_Id + "\"";
        c = db.query(dbh.TABLE_NAME_MOVIE, null, select, null, null, null, null);

        if(c.moveToFirst()) {
            int id = c.getColumnIndex(dbh.TM_TITLE);
            str = c.getString(id);
        }
        close();
        Log.d(LOG_TAG_DB, str);
        m_title = str;
    }



    private void open() {
        dbh = new DBHelper(context);
        db = dbh.getWritableDatabase();
    }

    private void close() {
        if (dbh != null) { dbh.close(); }
    }
    */
}