package com.example.kinoman.Source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kinoman.ClFrDwn.FilmObjectForDownload;

import java.util.List;
import java.util.Random;

/**
 * Created by ivanka on 02.11.15.
 */
public class MovieDataBase {

    private final String LOG_TAG_DB = "workWithDataDase";

    private final Context context;
    private DBHelper dbh;
    private SQLiteDatabase db;
    //int rand_num;

    //int count_movies;

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
            cv.put(dbh.TM_FLAG, 0);
            cv.put(dbh.TM_IMAGE, MT_img[i]);
            cv.put(dbh.TM_DIRECTOR, addDirector(film.getDirector()));
            id_movie = (int)db.insert(dbh.TABLE_NAME_MOVIE, null, cv);
            addLinkMovieGenre(id_movie, film.getGanres());
            addLinkMovieCountry(id_movie, film.getCountries());
            addLinkMovieActor(id_movie, film.getActors());
            //count_movies = id_movie;
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





        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_COUNTRY, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_ACTORS, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_ACTORS, null, null, null, null, null, null);
        logCursor(c);*/

        c = db.query(dbh.TABLE_NAME_GENRE, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_GENRE, null, null, null, null, null, null);
        logCursor(c);

        close();
    }

    public int selectIdMovie() {
        open();
        Cursor c;

        String selectCount = "select count(*) from " + dbh.TABLE_NAME_MOVIE + ";";
        c = db.rawQuery(selectCount, new String[]{});

        logCursor(c);
        c.moveToFirst();
        int count = c.getInt(c.getColumnIndex("count(*)"));

        Random rand = new Random();
        int rand_num = rand.nextInt(count - 1) + 1;

        close();
        return rand_num;
    }

    public Movie selectInfoMovie(int rand_num ) {
        Movie movie = new Movie();
        open();
        Cursor c;

        movie.setM_Id(rand_num);

        String select = dbh.TM_ID + " = \"" + rand_num + "\"";
        c = db.query(dbh.TABLE_NAME_MOVIE, null, select, null, null, null, null);

        if(c.moveToFirst()) {
            int id = c.getColumnIndex(dbh.TM_TITLE);
             movie.setM_title(c.getString(id));
            id = c.getColumnIndex(dbh.TM_YEAR_RELEASE);
            movie.setM_year(c.getString(id));
            id = c.getColumnIndex(dbh.TM_DESCRIPTION);
            movie.setM_description(c.getString(id));
            id = c.getColumnIndex(dbh.TM_ASSESSMENT);
            movie.setM_assessment(c.getInt(id));
            id = c.getColumnIndex(dbh.TM_IMAGE);
            movie.setM_img(c.getString(id));
            id = c.getColumnIndex(dbh.TM_FLAG);
            movie.setM_flag(c.getInt(id));
        }
        close();
        return movie;
    }

    public String selectGenre(int rand_num ) {
        open();
        String str = "";
        Cursor c;
        Cursor cursor;

        String whatSelect = dbh.TLMG_ID_MOVIE + " = \"" + rand_num + "\"";

        String selectCount = "select * from " + dbh.TABLE_NAME_LINK_MOVIE_GENRE + " where " + whatSelect + ";";
        c = db.rawQuery(selectCount, new String[]{});
        Log.d("workWithDataDase", "cursor c: " + c);


        logCursor(c);
        Log.d("workWithDataDase", "log: " + c.moveToFirst());
        c.moveToFirst();
        Boolean flag = false;

        do {
            int count = c.getInt(c.getColumnIndex(dbh.TLMG_ID_GENRE));
            Log.d("workWithDataDase", "ID жанра: " + count);

            String whatSelect2 = dbh.TG_ID_GENRE + " = \"" + count + "\"";
            cursor = db.query(dbh.TABLE_NAME_GENRE, null, whatSelect2, null, null, null, null);
            //Log.d("workWithDataDase", "Название: " + cursor);

            if (cursor.moveToFirst()) {
                int idG = cursor.getColumnIndex(dbh.TG_NAME_GENRE);
                if (flag) {
                    str = str + ", " + cursor.getString(idG);
                } else {
                    str = cursor.getString(idG);
                    flag = true;
                }

                Log.d("workWithDataDase", "Название: " + idG);
            }
        } while (c.moveToNext());
        close();

        return str;
    }

    public String selectCountry( int rand_num ) {
        open();
        String str = "";
        Cursor c;
        Cursor cursor;

        String whatSelect = dbh.TLMC_ID_MOVIE + " = \"" + rand_num + "\"";

        String selectCount = "select * from " + dbh.TABLE_NAME_LINK_MOVIE_COUNTRY + " where " + whatSelect + ";";
        c = db.rawQuery(selectCount, new String[]{});
        Log.d("workWithDataDase", "cursor c: " + c);

        logCursor(c);
        Log.d("workWithDataDase", "log: " + c.moveToFirst());
        c.moveToFirst();
        Boolean flag = false;

        do {
            int count = c.getInt(c.getColumnIndex(dbh.TLMC_ID_COUNTRY));
            Log.d("workWithDataDase", "ID жанра: " + count);

            String whatSelect2 = dbh.TC_ID_COUNTRY + " = \"" + count + "\"";
            cursor = db.query(dbh.TABLE_NAME_COUNTRY, null, whatSelect2, null, null, null, null);
            //Log.d("workWithDataDase", "Название: " + cursor);

            if (cursor.moveToFirst()) {
                int idG = cursor.getColumnIndex(dbh.TC_NAME_COUNTRY);
                if (flag) {
                    str = str + ", " + cursor.getString(idG);
                } else {
                    str = cursor.getString(idG);
                    flag = true;
                }

                Log.d("workWithDataDase", "Название: " + idG);
            }
        } while (c.moveToNext());
        close();
        return str;
    }

    public String selectActors( int rand_num ) {
        open();
        String str = "";
        Cursor c;
        Cursor cursor;

        String whatSelect = dbh.TLMA_ID_MOVIE + " = \"" + rand_num + "\"";

        String selectCount = "select * from " + dbh.TABLE_NAME_LINK_MOVIE_ACTORS + " where " + whatSelect + ";";
        c = db.rawQuery(selectCount, new String[]{});
        Log.d("workWithDataDase", "cursor c: " + c);

        logCursor(c);
        Log.d("workWithDataDase", "log: " + c.moveToFirst());
        c.moveToFirst();
        Boolean flag = false;

        do {
            int count = c.getInt(c.getColumnIndex(dbh.TLMA_ID_ACTOR));
            Log.d("workWithDataDase", "ID жанра: " + count);

            String whatSelect2 = dbh.TA_ID_ACTORS + " = \"" + count + "\"";
            cursor = db.query(dbh.TABLE_NAME_ACTORS, null, whatSelect2, null, null, null, null);
            //Log.d("workWithDataDase", "Название: " + cursor);

            if (cursor.moveToFirst()) {
                int idG = cursor.getColumnIndex(dbh.TA_NAME_ACTORS);
                if (flag) {
                    str = str + ", " + cursor.getString(idG);
                } else {
                    str = cursor.getString(idG);
                    flag = true;
                }

                Log.d("workWithDataDase", "Название: " + idG);
            }
        } while (c.moveToNext());
        close();
        return str;
    }

    public String selectDirector( int rand_num ) {
        String str ="";
        Cursor c;
        open();

        String whatSelect = dbh.TD_ID_DIRECTOR + " = \"" + rand_num + "\"";

        c = db.query(dbh.TABLE_NAME_DIRECTOR, null, whatSelect, null, null, null, null);
        if(c.moveToFirst()) {
            int id = c.getColumnIndex(dbh.TD_NAME_DIRECTOR);
            str = c.getString(id);
        }
        close();
        return str;
    }

    public Movie selectRandMovie() {
        Movie movie;
        movie = new Movie();
        int idMovie = selectIdMovie();

        movie = selectInfoMovie(idMovie);
        movie.setM_genre(selectGenre(idMovie));
        movie.setM_countries(selectCountry(idMovie));
        movie.setM_director(selectDirector(idMovie));
        movie.setM_actors(selectActors(idMovie));

        //movie.setM_Id(idMovie);
        //movie.setM_title(selectTitleMovie(idMovie));
        //movie.setM_title(selectInfoMovie(dbh.TM_TITLE, idMovie));
        //movie.setM_year(selectInfoMovie(dbh.TM_YEAR_RELEASE, idMovie));
        //movie.setM_description(selectInfoMovie(dbh.TM_DESCRIPTION, idMovie));

        return movie;
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

 /*   public String selectTitleMovie(int rand_num) {
    open();
    String str = "";
    Cursor c;

    String select = dbh.TM_ID + " = \"" + rand_num + "\"";
    c = db.query(dbh.TABLE_NAME_MOVIE, null, select, null, null, null, null);

    if(c.moveToFirst()) {
        int id = c.getColumnIndex(dbh.TM_TITLE);
        str = c.getString(id);
    }
    close();
    return str;
}
    public  String selectYear() {
    String str = selectInfoMovie(dbh.TM_YEAR_RELEASE);
    return str;
}

    public String selectDescription() {
        String str = selectInfoMovie(dbh.TM_DESCRIPTION);
        return str;
    }
     */