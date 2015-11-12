package com.example.kinoman;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kinoman.ClFrDwn.FilmObjectForDownload;
import com.example.kinoman.ClFrDwn.HtmlHelper;
import com.example.kinoman.Source.MovieDataBase;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnDownload;
    Button btnUpdata;
    Button btnDel;

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
            "https://my-hit.org/film/2155/"     //12 добейся успеха
    };

    List<FilmObjectForDownload> filmObjectForDownloads;

    private ProgressDialog pd;

    MovieDataBase movieDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnDownload = (Button) findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(this);

        btnUpdata = (Button) findViewById(R.id.btn_updata);
        btnUpdata.setOnClickListener(this);

        btnDel = (Button) findViewById(R.id.btn_del);
        btnDel.setOnClickListener(this);

        filmObjectForDownloads = new ArrayList<>();

        movieDataBase = new MovieDataBase(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_download:
                pd = ProgressDialog.show(AddActivity.this, "Working...", "Request to server", true, false);
                new ParseSite().execute();
                break;
            case R.id.btn_updata:
                movieDataBase.addDatas(filmObjectForDownloads);
                movieDataBase.checkMoves();
                Log.d("filmObject", "Ожидаемое количество фильмов: " + Integer.toString(siteLinks.length));
                Log.d("filmObject", "Скачанное количество фильмов: " + Integer.toString(filmObjectForDownloads.size()));
                Log.d("workWithDataDase", "Ожидаемое количество фильмов: " + Integer.toString(siteLinks.length));
                Log.d("workWithDataDase", "Скачанное количество фильмов: " + Integer.toString(filmObjectForDownloads.size()));
                break;
            case R.id.btn_del:
                movieDataBase.DelDatas();
                break;
        }
    }

    private class ParseSite extends AsyncTask<String, Void, List<String>> {
        //Фоновая операция
        //protected List<String> doInBackground(String... arg) {
        protected List<String> doInBackground(String... arg) {
            try
            {
                for(int i = 0; i < siteLinks.length; ++i) {
                    HtmlHelper hh = new HtmlHelper(new URL(siteLinks[i]));
                    filmObjectForDownloads.add(hh.getFilmFromSite());
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        //Событие по окончанию парсинга
        protected void onPostExecute(List<String> output) {
            //Убираем диалог загрузки
            pd.dismiss();
/*
            LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
            LayoutInflater ltInflater = getLayoutInflater();

            linLayout.removeAllViews();

            int i = 0;

            for(FilmObjectForDownload filmObjectForDownload : filmObjectForDownloads) {

                View item = ltInflater.inflate(R.layout.item_film, linLayout, false);
                TextView tvTitle = (TextView) item.findViewById(R.id.tvTitle);
                tvTitle.setText(filmObjectForDownload.getTitle());

                TextView tvYear = (TextView) item.findViewById(R.id.tvYear);
                tvYear.setText(filmObjectForDownload.getYear());

                TextView tvGanres = (TextView) item.findViewById(R.id.tvGanres);
                tvGanres.setText(filmObjectForDownload.getStringGanres());

                TextView tvCountrys = (TextView) item.findViewById(R.id.tvCountrys);
                tvCountrys.setText(filmObjectForDownload.getStringCountry());

                TextView tvDirector = (TextView) item.findViewById(R.id.tvDirector);
                tvDirector.setText(filmObjectForDownload.getDirector());

                TextView tvActors = (TextView) item.findViewById(R.id.tvActors);
                tvActors.setText(filmObjectForDownload.getStringActors());

                TextView tvDescription = (TextView) item.findViewById(R.id.tvDescription);
                tvDescription.setText(filmObjectForDownload.getDescription());

                if(i % 2 == 0) {
                    item.setBackgroundColor(Color.rgb(122, 205, 155));
                } else {
                    item.setBackgroundColor(Color.rgb(124, 122, 205));
                }

                ++i;

                linLayout.addView(item);
            }
            */
        }

    }

}
