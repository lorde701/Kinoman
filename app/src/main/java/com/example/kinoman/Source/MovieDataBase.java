package com.example.kinoman.Source;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kinoman.ClFrDwn.FilmObjectForDownload;
import com.example.kinoman.SearchActivity;

import java.util.ArrayList;
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

 /*   public void onUpdataDB() {
        this.open();
        this.close();
  }  */

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
            cv.put(dbh.TM_ASSESSMENT, 6);
            cv.put(dbh.TM_FLAG, 0);
            cv.put(dbh.TM_IMAGE, MT_img[i]);
            cv.put(dbh.TM_DIRECTOR, addDirector(film.getDirector()));
            id_movie = (int) db.insert(dbh.TABLE_NAME_MOVIE, null, cv);
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

        /*c = db.query(dbh.TABLE_NAME_MOVIE, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_DIRECTOR, null, null, null, null, null, null);
        logCursor(c);


        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_COUNTRY, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_ACTORS, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_ACTORS, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_GENRE, null, null, null, null, null, null);
        logCursor(c);

        c = db.query(dbh.TABLE_NAME_LINK_MOVIE_GENRE, null, null, null, null, null, null);
        logCursor(c);*/

        close();
    }

    public int selectIdMovie(String genre) {
        int rand_ID = -10;
        int i = 0;
        int[] arrayIdMovie = new int[100];

        open();
        Cursor c, cursor;

        String idG = "select " + dbh.TG_ID_GENRE + " from " + dbh.TABLE_NAME_GENRE + " where " + dbh.TG_NAME_GENRE + " = \"" + genre + "\";";
        c = db.rawQuery(idG, new String[]{});
        //Log.d(LOG_TAG_DB, "SQL: " + c);

        // logCursor(c);
        c.moveToFirst();
        int idGenre = c.getInt(c.getColumnIndex(dbh.TG_ID_GENRE));
        //Log.d("qwerty", "IdGenre in selectIdMovie in MovieDataBase: " + idGenre);

        String idM = "select " + dbh.TLMG_ID_MOVIE + " from " + dbh.TABLE_NAME_LINK_MOVIE_GENRE + " where " + dbh.TLMG_ID_GENRE + " = " + idGenre + ";";
        c = db.rawQuery(idM, new String[]{});

        c.moveToFirst();
        do {
            int idMovie = c.getInt(c.getColumnIndex(dbh.TLMG_ID_MOVIE));
            Log.d("qwerty", "IdMovie(не учитывая оценку) in selectIdMovie in MovieDataBase: " + idGenre);
            String selectIdM = "select " + dbh.TM_ID + " from " + dbh.TABLE_NAME_MOVIE + " where " + dbh.TM_ASSESSMENT + " = 6 and " +
                    dbh.TM_ID + " = " + idMovie + ";";
            cursor = db.rawQuery(selectIdM, new String[]{});
            Log.d("qwerty", "selectIdM in selectIdMovie in MovieDataBase: " + selectIdM);

            if (cursor.moveToFirst()) {
                int idMovieToSelect = cursor.getInt(cursor.getColumnIndex(dbh.TM_ID));
                arrayIdMovie[i++] = idMovieToSelect;
            }

        } while (c.moveToNext());

        if (i > 0) {
            Random rand = new Random();
            int index = rand.nextInt(i);
            Log.d(LOG_TAG_DB, "Index: " + index);
            Log.d(LOG_TAG_DB, " ");
            rand_ID = arrayIdMovie[index];
        }

        close();
        return rand_ID;
    }

    public int selectIdMovie() {
        open();
        Cursor c;
        int rand_num = -10;
        int i = 0;
        int[] array = new int[35];

        String selectCount = "select count(" + dbh.TM_ID + ") from " + dbh.TABLE_NAME_MOVIE + " where " + dbh.TM_ASSESSMENT + " = 6 ;";
        c = db.rawQuery(selectCount, new String[]{});
        if (c.moveToFirst()) {

            int count = c.getCount();
            Log.d("qwerty", "Количество фильмов: " + count);

            String select = "select " + dbh.TM_ID + " from " + dbh.TABLE_NAME_MOVIE + " where " + dbh.TM_ASSESSMENT + " = 6 ;";
            c = db.rawQuery(select, new String[]{});

            //logCursor(c);
            if (c.moveToFirst()) {
                do {
                    array[i] = c.getInt(c.getColumnIndex(dbh.TM_ID));
               /* Log.d(LOG_TAG_DB, " ");
                Log.d(LOG_TAG_DB, "Индекс фильма заданного жанра: " + c.getInt(c.getColumnIndex(dbh.TM_ID)));
                Log.d(LOG_TAG_DB, "Индекс в массиве: " + i);
                Log.d(LOG_TAG_DB, "Элемент массива array[i]: " + array[i]);
                Log.d(LOG_TAG_DB, " ");*/
                    i++;
                } while (c.moveToNext());

                Random rand = new Random();
                int index = rand.nextInt(i);
                Log.d(LOG_TAG_DB, "Index: " + index);
                Log.d(LOG_TAG_DB, " ");
                rand_num = array[index];
            }
        }
        close();
        return rand_num;
    }

    /*  public int selectIdMovie(String genre) {
          open();
          Cursor c;

          String idGenre = "select " + dbh.TG_ID_GENRE + " from " + dbh.TABLE_NAME_GENRE + " where " + dbh.TG_NAME_GENRE + " = " + genre + ";";
          c = db.rawQuery(idGenre, new String[]{});
          //Log.d(LOG_TAG_DB, "SQL: " + c);

          // logCursor(c);
          c.moveToFirst();
          int[] arrayIdMovie = new int[35];
          int i = 0;

          int idG = c.getInt(c.getColumnIndex(dbh.TG_ID_GENRE));

          String arrayId = "select " + dbh.TLMG_ID_MOVIE + " from " + dbh.TABLE_NAME_LINK_MOVIE_GENRE + " where " + dbh.TLMG_ID_GENRE + " = " + idG + ";";
          c = db.rawQuery(arrayId, new String[]{});

          //logCursor(c);

          c.moveToFirst();
          do {
              arrayIdMovie[i++] = c.getInt(c.getColumnIndex(dbh.TLMG_ID_MOVIE));
          } while (c.moveToNext());


          Random rand = new Random();
          int rand_num = arrayIdMovie[rand.nextInt(i) - 1];

          close();
          return rand_num;
      }
  */
    public Movie selectInfoMovie(int rand_num) {
        Movie movie = new Movie();
        Cursor c;

        DBHelper dbh = new DBHelper(context);
        db = dbh.getWritableDatabase();

        movie.setM_Id(rand_num);

        String select = dbh.TM_ID + " = \"" + rand_num + "\"";
        c = db.query(dbh.TABLE_NAME_MOVIE, null, select, null, null, null, null);

        if (c.moveToFirst()) {
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
        db.close();
        return movie;
    }

    public String selectGenre(int rand_num) {
        open();
        String str = "";
        Cursor c;
        Cursor cursor;

        String whatSelect = dbh.TLMG_ID_MOVIE + " = \"" + rand_num + "\"";

        String selectCount = "select * from " + dbh.TABLE_NAME_LINK_MOVIE_GENRE + " where " + whatSelect + ";";
        c = db.rawQuery(selectCount, new String[]{});
        //Log.d("workWithDataDase", "cursor c: " + c);


        //logCursor(c);
        //Log.d("workWithDataDase", "log: " + c.moveToFirst());
        c.moveToFirst();
        Boolean flag = false;

        do {
            int count = c.getInt(c.getColumnIndex(dbh.TLMG_ID_GENRE));
            //Log.d("workWithDataDase", "ID жанра: " + count);

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

                //Log.d("workWithDataDase", "Название: " + idG);
            }
        } while (c.moveToNext());
        close();

        return str;
    }

    public String selectCountry(int rand_num) {
        open();
        String str = "";
        Cursor c;
        Cursor cursor;

        String whatSelect = dbh.TLMC_ID_MOVIE + " = \"" + rand_num + "\"";

        String selectCount = "select * from " + dbh.TABLE_NAME_LINK_MOVIE_COUNTRY + " where " + whatSelect + ";";
        c = db.rawQuery(selectCount, new String[]{});
        //Log.d("workWithDataDase", "cursor c: " + c);

        //logCursor(c);
        //Log.d("workWithDataDase", "log: " + c.moveToFirst());
        c.moveToFirst();
        Boolean flag = false;

        do {
            int count = c.getInt(c.getColumnIndex(dbh.TLMC_ID_COUNTRY));
            //Log.d("workWithDataDase", "ID жанра: " + count);

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

                //Log.d("workWithDataDase", "Название: " + idG);
            }
        } while (c.moveToNext());
        close();
        return str;
    }

    public String selectActors(int rand_num) {
        open();
        String str = "";
        Cursor c;
        Cursor cursor;

        String whatSelect = dbh.TLMA_ID_MOVIE + " = \"" + rand_num + "\"";

        String selectCount = "select * from " + dbh.TABLE_NAME_LINK_MOVIE_ACTORS + " where " + whatSelect + ";";
        c = db.rawQuery(selectCount, new String[]{});
        //Log.d("workWithDataDase", "cursor c: " + c);

        //logCursor(c);
        //Log.d("workWithDataDase", "log: " + c.moveToFirst());
        c.moveToFirst();
        Boolean flag = false;

        do {
            int count = c.getInt(c.getColumnIndex(dbh.TLMA_ID_ACTOR));
            //Log.d("workWithDataDase", "ID жанра: " + count);

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

                //
                //Log.d("workWithDataDase", "Название: " + idG);
            }
        } while (c.moveToNext());
        close();
        return str;
    }

    public String selectDirector(int rand_num) {
        String str = "";
        Cursor c;
        open();

        String whatSelect = dbh.TD_ID_DIRECTOR + " = \"" + rand_num + "\"";

        c = db.query(dbh.TABLE_NAME_DIRECTOR, null, whatSelect, null, null, null, null);
        if (c.moveToFirst()) {
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
        if (idMovie != -10) {
            movie = selectInfoMovie(idMovie);
            movie.setM_genre(selectGenre(idMovie));
            movie.setM_countries(selectCountry(idMovie));
            movie.setM_director(selectDirector(idMovie));
            movie.setM_actors(selectActors(idMovie));
            return movie;
        } else
            movie.setM_Id(-10);

        return movie;
    }

    public Movie selectRandMovie(String genre) {
        Movie movie;
        movie = new Movie();
        int idMovie = selectIdMovie(genre);
        if (idMovie != -10) {
            movie = selectInfoMovie(idMovie);
            movie.setM_genre(selectGenre(idMovie));
            movie.setM_countries(selectCountry(idMovie));
            movie.setM_director(selectDirector(idMovie));
            movie.setM_actors(selectActors(idMovie));
            return movie;
        } else
            movie.setM_Id(-10);

        Log.d("qwerty", "IdRandMovie in selectRandMovie in MovieDataBase: " + movie.getM_Id());
        return movie;
    }

    private int addDirector(String name_director) {
        int id;
        ContentValues cv = new ContentValues();
        Cursor c;

        String whatSelect = dbh.TD_NAME_DIRECTOR + " = \"" + name_director + "\"";

        c = db.query(dbh.TABLE_NAME_DIRECTOR, null, whatSelect, null, null, null, null);


        if (c.moveToFirst()) {
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


        if (c.moveToFirst()) {
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
        for (int i = 0; i < genres.size(); i++) {
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


        if (c.moveToFirst()) {
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
        for (int i = 0; i < countries.size(); i++) {
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


        if (c.moveToFirst()) {
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
        for (int i = 0; i < actors.size(); i++) {
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


    public List<MovieForSearch> getListMovie(String search) {

        List<MovieForSearch> list = new ArrayList<>();

        open();
        Cursor c;

        String select = "select " + dbh.TM_TITLE + ", " + dbh.TM_ID + ", " + dbh.TM_IMAGE + ", " + dbh.TM_ASSESSMENT + " from " + dbh.TABLE_NAME_MOVIE + " where " + dbh.TM_TITLE + " like \'%" + search + "%\' ;";
        //Log.d(LOG_TAG_DB, select);
        c = db.rawQuery(select, new String[]{});
        //c = db.query(dbh.TABLE_NAME_MOVIE, new String[] {dbh.TM_ID, dbh.TM_TITLE, dbh.TM_IMAGE, dbh.TM_ASSESSMENT}, dbh.TM_TITLE + " = ?", new String[] {"LIKE %" + search + "%", null, null})

        //logCursor(c);

        int index_id = c.getColumnIndex(dbh.TM_ID);
        int index_title = c.getColumnIndex(dbh.TM_TITLE);
        int index_img = c.getColumnIndex(dbh.TM_IMAGE);
        int index_assessment = c.getColumnIndex(dbh.TM_ASSESSMENT);

        if (c.moveToFirst()) {
            do {
                MovieForSearch temp = new MovieForSearch(c.getInt(index_id), c.getString(index_title), c.getString(index_img), c.getInt(index_assessment));
                list.add(temp);
            } while (c.moveToNext());
        }

        close();
        c.close();
        this.close();
        return list;
    }

    public void setAssessment(int IdMovie, int Assesment) {
        open();

        ContentValues cv = new ContentValues();
        //DatasForDB datasForDB = new DatasForDB();

        // String select = "updata " + dbh.TM_ID + ", " + dbh.TM_ASSESSMENT + " from " + dbh.TABLE_NAME_MOVIE + " where " + dbh.TM_ID + " = " + IdMovie + ";";
        //String temp = "update " + dbh.TABLE_NAME_MOVIE + " set " + dbh.TM_ASSESSMENT + " = " + Assesment + " where " + dbh.TM_ID + " = " + IdMovie + ";";
        //Log.d(LOG_TAG_DB, temp);
        //db.rawQuery(temp, new String[]{});

        cv.put(dbh.TM_ASSESSMENT, Assesment);

        db.update(dbh.TABLE_NAME_MOVIE, cv, dbh.TM_ID + " = ?", new String[]{String.valueOf(IdMovie)});

        Cursor c = db.rawQuery("select * from movie where _id = " + IdMovie + ";", new String[]{});

        //logCursor(c);

        int index = c.getColumnIndex(dbh.TM_ASSESSMENT);
        int id = 0;
        if (c.moveToFirst()) {
            id = c.getInt(index);
        }

        Log.d(LOG_TAG_DB, "Оценка: " + String.valueOf(id));

        close();
    }

    public List<Genre> getListGenre() {
        List<Genre> list = new ArrayList<>();

        open();
        Cursor c;

        String select = "select * from " + dbh.TABLE_NAME_GENRE + ";";
        c = db.rawQuery(select, new String[]{});

        //logCursor(c);


        int index_id = c.getColumnIndex(dbh.TG_ID_GENRE);
        int index_name = c.getColumnIndex(dbh.TG_NAME_GENRE);

        if (c.moveToFirst()) {
            do {
                Genre genre = new Genre(c.getInt(index_id), c.getString(index_name));
                list.add(genre);
            } while (c.moveToNext());
        }

        close();
        return list;
    }
    public List<MovieForSearch> WatchedMovie() {
        List<MovieForSearch> list = new ArrayList<>();

        open();
        Cursor c;

        String select = "select " + dbh.TM_TITLE + ", " + dbh.TM_ID + ", " + dbh.TM_IMAGE + ", " + dbh.TM_ASSESSMENT +
                " from " + dbh.TABLE_NAME_MOVIE + " where " + dbh.TM_ASSESSMENT + " <> 6 order by " + dbh.TM_ASSESSMENT + ";";
        //Log.d(LOG_TAG_DB, select);
        c = db.rawQuery(select, new String[]{});

        int index_id = c.getColumnIndex(dbh.TM_ID);
        int index_title = c.getColumnIndex(dbh.TM_TITLE);
        int index_img = c.getColumnIndex(dbh.TM_IMAGE);
        int index_assessment = c.getColumnIndex(dbh.TM_ASSESSMENT);

        if (c.moveToFirst()) {
            do {
                MovieForSearch temp = new MovieForSearch(c.getInt(index_id), c.getString(index_title), c.getString(index_img), c.getInt(index_assessment));
                list.add(temp);
            } while (c.moveToNext());
        }

        close();
        c.close();
        this.close();
        return list;
    }
}

