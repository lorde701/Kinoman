package com.example.kinoman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.kinoman.Source.Movie;
import com.example.kinoman.Source.MovieDataBase;
import com.example.kinoman.Source.DBHelper;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_title_movie;
    TextView txt_year;
    TextView txt_genre;
    TextView txt_countries;
    TextView txt_director;
    TextView txt_actors;
    TextView txt_description;
    TextView txt_assessment;
    ImageView img;

    Button btn_other;
    Button btn_willWatch;

    LinearLayout layout0;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;

    String genre;

    ScrollView scroll;


    MovieDataBase mdb;
    Movie mov;
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        //flag = getIntent().getStringExtra("flag");

        btn_other = (Button) findViewById(R.id.btn_other);
        btn_other.setOnClickListener(this);

        btn_willWatch = (Button) findViewById(R.id.btn_willWatch);
        btn_willWatch.setOnClickListener(this);

        txt_title_movie = (TextView) findViewById(R.id.txt_title_movie);
        txt_year = (TextView) findViewById(R.id.txt_year);
        txt_genre = (TextView) findViewById(R.id.txt_genre);
        txt_director = (TextView) findViewById(R.id.txt_director);
        txt_description = (TextView) findViewById(R.id.txt_description);
        txt_countries = (TextView) findViewById(R.id.txt_country);
        txt_actors = (TextView) findViewById(R.id.txt_actors);
        txt_assessment = (TextView)findViewById(R.id.txt_assessment);
        img = (ImageView) findViewById(R.id.img);

        scroll = (ScrollView)findViewById(R.id.scrollView);

        layout0 = (LinearLayout) findViewById(R.id.layout0);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);
        layout4 = (LinearLayout) findViewById(R.id.layout4);
        layout5 = (LinearLayout) findViewById(R.id.layout5);

        layout0.setOnClickListener(this);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);

        otherMovie();
    }

    protected void otherMovie() {

        mdb = new MovieDataBase(this);
        flag = getIntent().getStringExtra("flag");
        genre = getIntent().getStringExtra("genre");
        int idMovie = getIntent().getIntExtra("idMovie", -10);

        switch (flag) {
            case "SelectGenreActivity":
                if (genre == null) {
                    mov = mdb.selectRandMovie();
                } else {
                    mov = mdb.selectRandMovie(genre);
                    Log.d("qwerty", "Поиск по жанру");
                }
                break;
            case "SearchMoviesActivity":
                //mov = mdb.getInfoMovieForId(idMovie);
                //Log.d("qwerty", "mov = mdb.getInfoMovieForId(idMovie);");
                //Log.d("qwerty", "idMovie: " + idMovie);
               // break;
            case "WhatchedActivity":
                mov = mdb.getInfoMovieForId(idMovie);
                btn_other.setVisibility(View.GONE);
                btn_willWatch.setVisibility(View.GONE);
                break;
        }

        Log.d("qwerty", "IdMovie: " + mov.getM_Id());
        Log.d("qwerty", "GenreMovie: " + mov.getM_genre());
        Log.d("qwerty", "ImgMovie: " + mov.getM_img());

        if (mov.getM_Id() != -10) {


            txt_title_movie.setText(mov.getM_title());
            txt_year.setText("Год выпуска: " + mov.getM_year());
            //txt_year.setSele(0, 11);
            //String tempString = getString(R.string.tempGanre) ;
            txt_genre.setText("Жанр: " + mov.getM_genre());
            txt_director.setText("Режиссер: " + mov.getM_director());
            txt_description.setText("Описание: " + mov.getM_description());
            txt_countries.setText("Страна: " + mov.getM_countries());
            txt_actors.setText("Актеры: " + mov.getM_actors());
            txt_assessment.setText("Ваша оценка");

            Log.d("qwerty", "Id movie: " + mov.getM_Id());
            Log.d("qwerty", "Название рисунка " + mov.getM_img());

            int idImg = SelectActivity.this.getResources().getIdentifier(mov.getM_img(), "drawable", getPackageName());
            //Log.d("qwerty", "idImg: " + idImg);
            img.setImageDrawable(getResources().getDrawable(idImg));

            //Log.d("qwerty", "\nTitleMovie: " + txt_title_movie.getText());
            //Log.d("qwerty", "CountryMovie: " + txt_countries.getText());
            //Log.d("qwerty", "ImgMovie: " + mov.getM_img());
            //Log.d("qwerty", "CountryMovie: " + txt_countries.getText());


           /* layout0.setBackgroundResource(R.drawable.s0);
            layout1.setBackgroundResource(R.drawable.star);
            layout2.setBackgroundResource(R.drawable.star2);
            layout3.setBackgroundResource(R.drawable.star);*/



            switch (mov.getM_assessment()) {
                case 0:
                    layout0.setBackgroundResource(R.drawable.s0);
                    layout1.setBackgroundResource(R.drawable.star);
                    layout2.setBackgroundResource(R.drawable.star);
                    layout3.setBackgroundResource(R.drawable.star);
                    layout4.setBackgroundResource(R.drawable.star);
                    layout5.setBackgroundResource(R.drawable.star);

                case 1:
                    layout0.setBackgroundResource(R.drawable.s0);
                    layout1.setBackgroundResource(R.drawable.star2);
                    layout2.setBackgroundResource(R.drawable.star);
                    layout3.setBackgroundResource(R.drawable.star);
                    layout4.setBackgroundResource(R.drawable.star);
                    layout5.setBackgroundResource(R.drawable.star);
                    break;
                case 2:
                    layout0.setBackgroundResource(R.drawable.s0);
                    layout1.setBackgroundResource(R.drawable.star2);
                    layout2.setBackgroundResource(R.drawable.star2);
                    layout3.setBackgroundResource(R.drawable.star);
                    layout4.setBackgroundResource(R.drawable.star);
                    layout5.setBackgroundResource(R.drawable.star);
                    break;
                case 3:
                    layout0.setBackgroundResource(R.drawable.s0);
                    layout1.setBackgroundResource(R.drawable.star2);
                    layout2.setBackgroundResource(R.drawable.star2);
                    layout3.setBackgroundResource(R.drawable.star2);
                    layout4.setBackgroundResource(R.drawable.star);
                    layout5.setBackgroundResource(R.drawable.star);
                    break;
                case 4:
                    layout0.setBackgroundResource(R.drawable.s0);
                    layout1.setBackgroundResource(R.drawable.star2);
                    layout2.setBackgroundResource(R.drawable.star2);
                    layout3.setBackgroundResource(R.drawable.star2);
                    layout4.setBackgroundResource(R.drawable.star2);
                    layout5.setBackgroundResource(R.drawable.star);
                    break;
                case 5:
                    layout0.setBackgroundResource(R.drawable.s0);
                    layout1.setBackgroundResource(R.drawable.star2);
                    layout2.setBackgroundResource(R.drawable.star2);
                    layout3.setBackgroundResource(R.drawable.star2);
                    layout4.setBackgroundResource(R.drawable.star2);
                    layout5.setBackgroundResource(R.drawable.star2);
                    break;
                default:
                    layout0.setBackgroundResource(R.drawable.eye1);
                    layout1.setBackgroundResource(R.drawable.star);
                    layout2.setBackgroundResource(R.drawable.star);
                    layout3.setBackgroundResource(R.drawable.star);
                    layout4.setBackgroundResource(R.drawable.star);
                    layout5.setBackgroundResource(R.drawable.star);
            }

            scroll.scrollTo(0,0);

        } else {


            Log.d("qwerty", "Прошел по ветви false(нет фильма с заданным жанром)");

            btn_other.setVisibility(View.INVISIBLE);
            btn_willWatch.setVisibility(View.INVISIBLE);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Оп-па-па")
                            .setMessage("Непросмотренных фильмов данного жанра нет")
                                    //.setIcon(R.drawable.ic_android_cat)
                            .setCancelable(false)
                            .setNegativeButton("Ок",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                            finish();
                                        }
                                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }


    @Override
    public void onClick(View v) {

        MovieDataBase database = new MovieDataBase(this);

        switch (v.getId()) {

            case R.id.btn_other:
                otherMovie();
                break;
            case R.id.layout0:
                layout0.setBackgroundResource(R.drawable.s0);
                layout1.setBackgroundResource(R.drawable.star);
                layout2.setBackgroundResource(R.drawable.star);
                layout3.setBackgroundResource(R.drawable.star);
                layout4.setBackgroundResource(R.drawable.star);
                layout5.setBackgroundResource(R.drawable.star);
                database.setAssessment(mov.getM_Id(), 0);
                break;
            case R.id.layout1:
                layout0.setBackgroundResource(R.drawable.s0);
                layout1.setBackgroundResource(R.drawable.star2);
                layout2.setBackgroundResource(R.drawable.star);
                layout3.setBackgroundResource(R.drawable.star);
                layout4.setBackgroundResource(R.drawable.star);
                layout5.setBackgroundResource(R.drawable.star);
                database.setAssessment(mov.getM_Id(), 1);
                //mdb.setAssessment(mov.getM_Id(), 1);
                break;
            case R.id.layout2:
                layout0.setBackgroundResource(R.drawable.s0);
                layout1.setBackgroundResource(R.drawable.star2);
                layout2.setBackgroundResource(R.drawable.star2);
                layout3.setBackgroundResource(R.drawable.star);
                layout4.setBackgroundResource(R.drawable.star);
                layout5.setBackgroundResource(R.drawable.star);
                database.setAssessment(mov.getM_Id(), 2);
                // mdb.setAssessment(mov.getM_Id(), 2);
                break;
            case R.id.layout3:
                layout0.setBackgroundResource(R.drawable.s0);
                layout1.setBackgroundResource(R.drawable.star2);
                layout2.setBackgroundResource(R.drawable.star2);
                layout3.setBackgroundResource(R.drawable.star2);
                layout4.setBackgroundResource(R.drawable.star);
                layout5.setBackgroundResource(R.drawable.star);
                database.setAssessment(mov.getM_Id(), 3);
                //mdb.setAssessment(mov.getM_Id(), 3);
                break;
            case R.id.layout4:
                layout0.setBackgroundResource(R.drawable.s0);
                layout1.setBackgroundResource(R.drawable.star2);
                layout2.setBackgroundResource(R.drawable.star2);
                layout3.setBackgroundResource(R.drawable.star2);
                layout4.setBackgroundResource(R.drawable.star2);
                layout5.setBackgroundResource(R.drawable.star);
                database.setAssessment(mov.getM_Id(), 4);
                //mdb.setAssessment(mov.getM_Id(), 4);
                break;
            case R.id.layout5:
                layout0.setBackgroundResource(R.drawable.s0);
                layout1.setBackgroundResource(R.drawable.star2);
                layout2.setBackgroundResource(R.drawable.star2);
                layout3.setBackgroundResource(R.drawable.star2);
                layout4.setBackgroundResource(R.drawable.star2);
                layout5.setBackgroundResource(R.drawable.star2);
                database.setAssessment(mov.getM_Id(), 5);
                //mdb.setAssessment(mov.getM_Id(), 5);
                break;
            case R.id.btn_willWatch:
                database.setAssessment(mov.getM_Id(), 0);
        }
    }
}