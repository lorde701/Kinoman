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

    public static final String TABLE_NAME = "movie";
    public static final String TITLE_MOVIE ="title_movie";
    public static final String YEAR_RELEASE_MOVIE = "year_release_movie";
    public static final String GENRE_MOVIE = "genre_movie";
    public static final String COUNTRY_MOVIE = "country_movie";
    public static final String DIRECTOR_MOVIE = "director_movie";
    public static final String ACTORS_MOVIE = "actors_movie";
    public static final String DESCRIPTION_MOVIE = "description_movie";
    public static final String ASSESSMENT_MOVIE = "assessment_movie";
    public static final String IMAGE_MOVIE = "image_movie";


    private static final String CREATE_TABLE_MOVIE = "create table " + TABLE_NAME +" ("
                                                     + "id integer primary key autoincrement,"
                                                     + TITLE_MOVIE + " text,"
                                                     + YEAR_RELEASE_MOVIE + " integer,"
                                                     + GENRE_MOVIE + " text,"        //!!!!!!!!!!!!!! int
                                                     + COUNTRY_MOVIE + " text,"
                                                     + DIRECTOR_MOVIE + " text,"
                                                     + ACTORS_MOVIE + " text,"
                                                     + DESCRIPTION_MOVIE + " text"
                                                     + ASSESSMENT_MOVIE + " integer"
                                                     + IMAGE_MOVIE + " text);" ;

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

    }

    private void onCreateTableMovie(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_MOVIE);

        String[] MOVIES = datasForDB.title_movies;
        int[] YEAR = datasForDB.year;
        String[] GENRE = datasForDB.genre;
        String[] COUNTRY = datasForDB.country;
        String[] DIRECTOR = datasForDB.director;
        String[] ACTORS = datasForDB.actors;
        String[] DESCRIPTION = datasForDB.description;
        int[] ASSESSMENT = datasForDB.assessment;
        String[] IMAGE = datasForDB.img;

        for (int i = 0; i < MOVIES.length; ++i ) {
            cv.clear();
            cv.put(TITLE_MOVIE, MOVIES[i]);
            cv.put(YEAR_RELEASE_MOVIE, YEAR[i]);
            cv.put(GENRE_MOVIE, GENRE[i]);
            cv.put(COUNTRY_MOVIE, COUNTRY[i]);
            cv.put(DIRECTOR_MOVIE, DIRECTOR[i]);
            cv.put(ACTORS_MOVIE, ACTORS[i]);
            cv.put(DESCRIPTION_MOVIE, DESCRIPTION[i]);
            cv.put(ASSESSMENT_MOVIE, 0);
            cv.put(IMAGE_MOVIE, IMAGE[i]);
            db.insert(TABLE_NAME, null, cv);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
