package com.example.kinoman;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.kinoman.ClFrDwn.FilmObjectForDownload;
import com.example.kinoman.ClFrDwn.HtmlHelper;
import com.example.kinoman.Source.MovieDataBase;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_about;
    Button btn_select;
    Button btn_search;
    Button btn_watched;
    Button btn_add;
    Button btn_update;

    final String LOG_TAG = "myLogs";

    private ProgressDialog pd;

    MovieDataBase movieDataBase;

    List<FilmObjectForDownload> filmObjectForDownloads;

    String[] siteLinks = {
            "https://my-hit.org/film/1741/",    //1 розыгрыш
            "https://my-hit.org/film/108/",     //2 гордость и предубеждение
            "https://my-hit.org/film/322283/",  //3 Человек-муравей
            "https://my-hit.org/film/398576/",  //4Багровый пик
            "https://my-hit.org/film/415223/",  //5Свободное падение
            "https://my-hit.org/film/415226/",  //6Перезагрузка
            "https://my-hit.org/film/239317/",  //7Игра в имитацию
            "https://my-hit.org/film/414894/",  //8Последний охотник на ведьм
            "https://my-hit.org/film/3822/",    //9Всегда говори «ДА»
            "https://my-hit.org/film/2628/",    //10
            "https://my-hit.org/film/346390/",  //qwe11
            "https://my-hit.org/film/2155/"   //12 добейся успеха
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_about = (Button)findViewById(R.id.btn_about);
        btn_about.setOnClickListener(this);

        btn_select = (Button)findViewById(R.id.btn_select);
        btn_select.setOnClickListener(this);

        btn_search = (Button)findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        btn_watched = (Button)findViewById(R.id.btn_watched);
        btn_watched.setOnClickListener(this);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        btn_update = (Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);

        filmObjectForDownloads = new ArrayList<>();

        movieDataBase = new MovieDataBase(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        // создаем объект для данных
        //ContentValues cv = new ContentValues();
        Intent intent;

        switch (v.getId()) {
            case R.id.btn_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_search:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_watched:
                intent = new Intent(MainActivity.this, WhatchedActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_add:
                intent =new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_select:
                intent = new Intent(this, SelectGenreActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_update:
                pd = ProgressDialog.show(this, "Working...", "Request to server", true, false);
                new ParseSite().execute();
                break;
        }
    }

    private class ParseSite extends AsyncTask<String, Void, List<String>> {
        //Фоновая операция
        //protected List<String> doInBackground(String... arg) {
        protected List<String> doInBackground(String... arg) {
            try {
                for (int i = 0; i < siteLinks.length; ++i) {
                    HtmlHelper hh = new HtmlHelper(new URL(siteLinks[i]));
                    filmObjectForDownloads.add(hh.getFilmFromSite());
                }
                movieDataBase.addDatas(filmObjectForDownloads);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        //Событие по окончанию парсинга
        protected void onPostExecute(List<String> output) {
            //Убираем диалог загрузки
            pd.dismiss();
        }
    }

}
