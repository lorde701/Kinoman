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

    //public static final String ID = "id";

    public static final String TM_ID_MOVIE = "ID_movie";
    public static final String TABLE_NAME_MOVIE = "movie";
    public static final String TM_TITLE_MOVIE = "title_movie";
    public static final String TM_YEAR_RELEASE_MOVIE = "year_release_movie";
    public static final String TM_GENRE_MOVIE = "genre_movie";
    public static final String TM_COUNTRY_MOVIE = "country_movie";
    public static final String TM_DIRECTOR_MOVIE = "director_movie";
    public static final String TM_ACTORS_MOVIE = "actors_movie";
    public static final String TM_DESCRIPTION_MOVIE = "description_movie";
    public static final String TM_ASSESSMENT_MOVIE = "assessment_movie";
    public static final String TM_IMAGE_MOVIE = "image_movie";
    public static final String TM_FLAG_MOVIE = "flag_movie";


    private static final String CREATE_TABLE_MOVIE = "create table " + TABLE_NAME_MOVIE + " ("
            + TM_ID_MOVIE + " integer,"
            + TM_TITLE_MOVIE + " text,"
            + TM_YEAR_RELEASE_MOVIE + " integer,"
            + TM_GENRE_MOVIE + " integer,"        //!!!!!!!!!!!!!! int
            + TM_COUNTRY_MOVIE + " integer,"
            + TM_DIRECTOR_MOVIE + " integer,"
            + TM_ACTORS_MOVIE + " integer,"
            + TM_DESCRIPTION_MOVIE + " text,"
            + TM_ASSESSMENT_MOVIE + " integer,"
            + TM_FLAG_MOVIE + " boolean,"
            + TM_IMAGE_MOVIE + " text);";

    public static final String ID_MOVIE = "ID_movie";

    public static final String TABLE_NAME_GENRE = "table_name_genre";
    public static final String NAME_GENRE = "name_genre";
    public static final String ID_GENRE = "ID_genre";

    private static final String CREATE_TABLE_GENRE = "create table " + TABLE_NAME_GENRE + " ("
            + ID_GENRE + " integer,"
            + NAME_GENRE + " text);";

    public static final String TABLE_NAME_LINK_MOVIE_GENRE = "table_name_link_movie_genre";

    //public static final String ID_GENRE = "ID_genre";

    private static final String CREATE_TABLE_LINK_MOVIE_GENRE = "create table " + TABLE_NAME_LINK_MOVIE_GENRE + " ("
            + ID_MOVIE + " integer,"
            + ID_GENRE + " integer);";


    public static final String TABLE_NAME_LINK_MOVIE_COUNTRY = "table_name_link_movie_country ";
    public static final String ID_COUNTRY = "ID_country";

    private static final String CREATE_TABLE_LINK_MOVIE_COUNTRY = "create table " + TABLE_NAME_LINK_MOVIE_COUNTRY + " ("
            + ID_MOVIE + " integer,"
            + ID_COUNTRY + " integer);";


    public static final String TABLE_NAME_LINK_MOVIE_ACTORS = "table_name_link_movie_actors ";
    public static final String ID_ACTORS = "ID_actors";

    private static final String CREATE_TABLE_LINK_MOVIE_ACTORS = "create table " + TABLE_NAME_LINK_MOVIE_ACTORS + " ("
            + ID_MOVIE + " integer,"
            + ID_ACTORS + " integer);";


    public static final String TABLE_NAME_COUNTRY = "table_name_country";
    public static final String NAME_COUNTRY = "name_country";

    private static final String CREATE_TABLE_COUNTRY = "create table " + TABLE_NAME_COUNTRY + " ("
            + ID_COUNTRY + " integer,"
            + NAME_COUNTRY + " text);";

    public static final String TABLE_NAME_DIRECTOR = "table_name_director";
    public static final String NAME_DIRECTOR = "name_director";
    public static final String ID_DIRECTOR = "ID_director";

    private static final String CREATE_TABLE_DIRECTOR = "create table " + TABLE_NAME_DIRECTOR + " ("
            + ID_DIRECTOR + " integer,"
            + NAME_DIRECTOR + " text);";

    public static final String TABLE_NAME_ACTORS = "table_name_actors";
    public static final String NAME_ACTORS = "name_actors";

    private static final String CREATE_TABLE_ACTORS = "create table " + TABLE_NAME_ACTORS + " ("
            + ID_ACTORS + " integer,"
            + NAME_ACTORS + " text);";


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
        onCreateTableMovie(db);
        onCreateTableGenre(db);
        onCreateTableCountry(db);
        onCreateTableDirector(db);
        onCreateTableActor(db);
        onCreateTableLinkMovieGenre(db);
        onCreateTableLinkMovieCountry(db);
        onCreateTableLinkMovieActor(db);

    }

    protected void onCreateTableMovie(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_MOVIE);

        String[] MOVIES = datasForDB.title_movies;
        int[] ID_MOVIE_FILE = datasForDB.ID_movie;
        int[] YEAR = datasForDB.year;
        //int[] GENRE = datasForDB.genre;
        //int[] COUNTRY = datasForDB.country;
        int[] DIRECTOR = datasForDB.director;
        //int[] ACTORS = datasForDB.actors;
        String[] DESCRIPTION = datasForDB.description;
        int[] ASSESSMENT = datasForDB.assessment;
        String[] IMAGE = datasForDB.img;
        boolean[] FLAG = datasForDB.flag;

        for (int i = 0; i < MOVIES.length; ++i) {
            cv.clear();
            cv.put(TM_TITLE_MOVIE, MOVIES[i]);
            cv.put(ID_MOVIE, ID_MOVIE_FILE[i]);
            cv.put(TM_YEAR_RELEASE_MOVIE, YEAR[i]);
            //cv.put(GENRE_MOVIE, GENRE[i]);
            //cv.put(COUNTRY_MOVIE, COUNTRY[i]);
            cv.put(TM_DIRECTOR_MOVIE, DIRECTOR[i]);
            //cv.put(ACTORS_MOVIE, ACTORS[i]);
            cv.put(TM_DESCRIPTION_MOVIE, DESCRIPTION[i]);
            cv.put(TM_ASSESSMENT_MOVIE, 0);
            cv.put(TM_IMAGE_MOVIE, IMAGE[i]);
            cv.put(TM_FLAG_MOVIE, FLAG[i]);
            db.insert(TABLE_NAME_MOVIE, null, cv);

        }
    }

    public void onCreateTableGenre(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_GENRE);

        String[] NAME_GENRE_GENRE = datasForDB.name_genre;
        int[] ID_GENRE_GENRE = datasForDB.id_genre;

        for (int i = 0; i < NAME_GENRE_GENRE.length; ++i) {
            cv.clear();
            cv.put(NAME_GENRE, NAME_GENRE_GENRE[i]);
            cv.put(ID_GENRE, ID_GENRE_GENRE[i]);
            db.insert(TABLE_NAME_GENRE, null, cv);
        }

    }

    public void onCreateTableCountry(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_COUNTRY);

        String[] NAME_COUNTRY_COUNTRY = datasForDB.name_country;
        int[] ID_COUNTRY_COUNTRY = datasForDB.id_country;

        for (int i = 0; i < NAME_COUNTRY_COUNTRY.length; i++) {
            cv.clear();
            cv.put(NAME_COUNTRY, NAME_COUNTRY_COUNTRY[i]);
            cv.put(ID_COUNTRY, ID_COUNTRY_COUNTRY[i]);
            db.insert(TABLE_NAME_COUNTRY, null, cv);
        }
    }

    public void onCreateTableDirector(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_DIRECTOR);

        String[] NAME_DIRECTOR_DIRECTOR = datasForDB.name_director;
        int[] ID_DIRECTOR_DIRECTOR = datasForDB.id_director;

        for (int i = 0; i < NAME_DIRECTOR_DIRECTOR.length; i++) {
            cv.clear();
            cv.put(NAME_DIRECTOR, NAME_DIRECTOR_DIRECTOR[i]);
            cv.put(ID_DIRECTOR, ID_DIRECTOR_DIRECTOR[i]);
            db.insert(TABLE_NAME_DIRECTOR, null, cv);
        }
    }

    public void onCreateTableActor(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_ACTORS);

        String[] NAME_ACTOR_ACTOR = datasForDB.name_actor;
        int[] ID_ACTOR_ACTOR = datasForDB.id_actor;

        for (int i = 0; i < NAME_ACTOR_ACTOR.length; i++) {
            cv.clear();
            cv.put(NAME_ACTORS, NAME_ACTOR_ACTOR[i]);
            cv.put(ID_ACTORS, ID_ACTOR_ACTOR[i]);
            db.insert(TABLE_NAME_ACTORS, null, cv);
        }
    }


    public void onCreateTableLinkMovieGenre(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_LINK_MOVIE_GENRE);

        int[] GENRE_ID_MOVIE = datasForDB.genre_id_movie;
        int[] GENRE_ID_GENRE = datasForDB.genre_id_genre;

        for (int i = 0; i < GENRE_ID_GENRE.length; i++) {
            cv.clear();
            cv.put(ID_GENRE, GENRE_ID_GENRE[i]);
            cv.put(ID_MOVIE, GENRE_ID_MOVIE[i]);
            db.insert(TABLE_NAME_LINK_MOVIE_GENRE, null, cv);
        }
    }

    public void onCreateTableLinkMovieCountry(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_LINK_MOVIE_COUNTRY);


        int[] COUNTRY_ID_MOVIE = datasForDB.country_id_movie;
        int[] COUNTRY_ID_COUNTRY = datasForDB.country_id_country;

        for (int i = 0; i < COUNTRY_ID_COUNTRY.length; i++) {
            cv.clear();
            cv.put(ID_COUNTRY, COUNTRY_ID_COUNTRY[i]);
            cv.put(ID_MOVIE, COUNTRY_ID_MOVIE[i]);
            db.insert(CREATE_TABLE_LINK_MOVIE_COUNTRY, null, cv);
        }

    }

    public void onCreateTableLinkMovieActor(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_LINK_MOVIE_ACTORS);

        int[] ACTOR_ID_MOVIE = datasForDB.actor_id_movie;
        int[] ACTOR_ID_ACTOR = datasForDB.actor_id_actor;

        for (int i = 0; i < ACTOR_ID_ACTOR.length; i++) {
            cv.clear();
            cv.put(ID_MOVIE, ACTOR_ID_MOVIE[i]);
            cv.put(ID_MOVIE, ACTOR_ID_ACTOR[i]);
            db.insert(CREATE_TABLE_LINK_MOVIE_ACTORS, null, cv);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

