package com.example.kinoman.Source;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ivanka on 01.11.15.
 */
class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "myDB";
    static final int VERSION = 1;

    final String LOF_TAG = "myLogs";

    public static final String TABLE_NAME_MOVIE = "movie";
    public static final String TM_TITLE = "title_movie";
    public static final String TM_YEAR_RELEASE = "year_release_movie";
    //public static final String TM_GENRE = "genre_movie";
    //public static final String TM_COUNTRY = "country_movie";
    public static final String TM_DIRECTOR = "director_movie";
    //public static final String TM_ACTORS = "actors_movie";
    public static final String TM_DESCRIPTION = "description_movie";
    public static final String TM_ASSESSMENT = "assessment_movie";
    public static final String TM_IMAGE = "image_movie";
    public static final String TM_FLAG = "flag_movie";


    private static final String CREATE_TABLE_MOVIE = "create table " + TABLE_NAME_MOVIE + " ("
            + "_id integer primary key autoincrement,"
            + TM_TITLE + " text,"
            + TM_YEAR_RELEASE + " text,"
           // + TM_GENRE + " integer,"
            //+ TM_COUNTRY + " integer,"
            + TM_DIRECTOR + " integer,"
           // + TM_ACTORS + " integer,"
            + TM_DESCRIPTION + " text,"
            + TM_ASSESSMENT + " integer,"
            + TM_FLAG + " boolean,"
            + TM_IMAGE + " text);";

   // public static final String ID_MOVIE = "id";



    //TABLE_GENRE
    public static final String TABLE_NAME_GENRE = "table_name_genre";
    public static final String TG_NAME_GENRE = "tg_name_genre";
    public static final String TG_ID_GENRE = "_id";

    private static final String CREATE_TABLE_GENRE = "create table " + TABLE_NAME_GENRE + " ("
            + TG_ID_GENRE + " integer primary key autoincrement,"
            + TG_NAME_GENRE + " text);";


    //TABLE_LINK_MOVIE_GENRE
    public static final String TABLE_NAME_LINK_MOVIE_GENRE = "table_name_link_movie_genre";
    public static final String TLMG_ID_GENRE = "tlmg_id_genre";
    public static final String TLMG_ID_MOVIE = "tlmg_id_movie";

    private static final String CREATE_TABLE_LINK_MOVIE_GENRE = "create table " + TABLE_NAME_LINK_MOVIE_GENRE + " ("
            + TLMG_ID_MOVIE + " integer,"
            + TLMG_ID_GENRE + " integer);";


    //TABLE_LINK_MOVIE_COUNTRY
    public static final String TABLE_NAME_LINK_MOVIE_COUNTRY = "table_name_link_movie_country ";
    public static final String TLMC_ID_COUNTRY = "tlmg_id_country";
    public static final String TLMC_ID_MOVIE = "tlmc_id_movie";

    private static final String CREATE_TABLE_LINK_MOVIE_COUNTRY = "create table " + TABLE_NAME_LINK_MOVIE_COUNTRY + " ("
            + TLMC_ID_MOVIE + " integer,"
            + TLMC_ID_COUNTRY + " integer);";


    //TABLE_LINK_MOVIE_ACTOR
    public static final String TABLE_NAME_LINK_MOVIE_ACTORS = "table_name_link_movie_actors ";
    public static final String TLMA_ID_ACTOR = "tlma_id_actor";
    public static final String TLMA_ID_MOVIE = "tlma_id_movie";

    private static final String CREATE_TABLE_LINK_MOVIE_ACTORS = "create table " + TABLE_NAME_LINK_MOVIE_ACTORS + " ("
            + TLMA_ID_MOVIE + " integer,"
            + TLMA_ID_ACTOR + " integer);";


    //TABLE_COUNTRY
    public static final String TABLE_NAME_COUNTRY = "table_name_country";
    public static final String TC_NAME_COUNTRY = "tc_name_country";
    public static final String TC_ID_COUNTRY = "_id";

    private static final String CREATE_TABLE_COUNTRY = "create table " + TABLE_NAME_COUNTRY + " ("
            + TC_ID_COUNTRY + " integer primary key autoincrement,"
            + TC_NAME_COUNTRY + " text);";


    //TABLE_DIRECTOR
    public static final String TABLE_NAME_DIRECTOR = "table_name_director";
    public static final String TD_NAME_DIRECTOR = "td_name_director";
    public static final String TD_ID_DIRECTOR = "_id";
    //public static final String TD_ID_DIRECTOR = "td_id_director";

    private static final String CREATE_TABLE_DIRECTOR = "create table " + TABLE_NAME_DIRECTOR + " ("
            + TD_ID_DIRECTOR + " integer primary key autoincrement,"
            + TD_NAME_DIRECTOR + " text);";


    //TABLE_ACTORS
    public static final String TABLE_NAME_ACTORS = "table_name_actors";
    public static final String TA_NAME_ACTORS = "ta_name_actors";
    public static final String TA_ID_ACTORS = "_id";

    private static final String CREATE_TABLE_ACTORS = "create table " + TABLE_NAME_ACTORS + " ("
            + TA_ID_ACTORS + " integer primary key autoincrement,"
            + TA_NAME_ACTORS + " text);";


    DatasForDB datasForDB;

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, DB_NAME, null, VERSION);
        datasForDB = new DatasForDB();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOF_TAG, "--- onCreate database ---");
        datasForDB = new DatasForDB();
        db.execSQL(CREATE_TABLE_MOVIE);
        db.execSQL(CREATE_TABLE_GENRE);
        db.execSQL(CREATE_TABLE_COUNTRY);
        db.execSQL(CREATE_TABLE_DIRECTOR);
        db.execSQL(CREATE_TABLE_ACTORS);
        db.execSQL(CREATE_TABLE_LINK_MOVIE_GENRE);
        db.execSQL(CREATE_TABLE_LINK_MOVIE_COUNTRY);
        db.execSQL(CREATE_TABLE_LINK_MOVIE_ACTORS);
    }


    public void deleteTables(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GENRE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COUNTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DIRECTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LINK_MOVIE_COUNTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LINK_MOVIE_ACTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LINK_MOVIE_GENRE);
        Log.d(LOF_TAG, "--- delete database ---");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

