package com.example.kinoman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;


    MovieDataBase mdb;
    Movie mov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        otherMovie();

      /*  MovieDataBase mdb = new MovieDataBase(this);
        Movie mov = mdb.selectRandMovie();

        txt_title_movie = (TextView) findViewById(R.id.txt_title_movie);
        txt_title_movie.setText(mov.getM_title());

        txt_year = (TextView) findViewById(R.id.txt_year);
        txt_year.setText("Год выпуска: " + mov.getM_year());

        txt_genre = (TextView) findViewById(R.id.txt_genre);
        txt_genre.setText("Жанр: " + mov.getM_genre());

        txt_director = (TextView) findViewById(R.id.txt_director);
        txt_director.setText("Режиссер: " + mov.getM_director());

        txt_description = (TextView) findViewById(R.id.txt_description);
        txt_description.setText("Описание: " + mov.getM_description());

        txt_countries = (TextView) findViewById(R.id.txt_country);
        txt_countries.setText("Страна: " + mov.getM_countries());

        txt_actors = (TextView) findViewById(R.id.txt_actors);
        txt_actors.setText("Актеры: " + mov.getM_actors());

        //txt_assessment = (TextView)findViewById(R.id.txt_assessment);
        //txt_assessment.setText("Оценка: " + mov.getM_assessment());

        switch (mov.getM_assessment()) {
            case 1:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star));
                layout3.setBackground(getResources().getDrawable(R.drawable.star));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                break;
            case 2:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                break;
            case 3:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                break;
            case 4:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star2));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                break;
            case 5:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star2));
                layout5.setBackground(getResources().getDrawable(R.drawable.star2));
                break;
        }

        //int in = Integer.valueOf(mov.getM_assessment().getText().toString());

        btn_other = (Button) findViewById(R.id.btn_other);
        btn_other.setOnClickListener(this);

        int idImg = SelectActivity.this.getResources().getIdentifier(mov.getM_img(), "drawable", getPackageName());
        img = (ImageView) findViewById(R.id.img);
        img.setImageDrawable(getResources().getDrawable(idImg));

        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);
        layout4 = (LinearLayout) findViewById(R.id.layout4);
        layout5 = (LinearLayout) findViewById(R.id.layout5);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);*/
    }

    protected void otherMovie() {

        mdb = new MovieDataBase(this);
        mov = mdb.selectRandMovie();

        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        txt_title_movie = (TextView) findViewById(R.id.txt_title_movie);
        txt_title_movie.setText(mov.getM_title());

        txt_year = (TextView) findViewById(R.id.txt_year);
        txt_year.setText("Год выпуска: " + mov.getM_year());

        txt_genre = (TextView) findViewById(R.id.txt_genre);
        txt_genre.setText("Жанр: " + mov.getM_genre());

        txt_director = (TextView) findViewById(R.id.txt_director);
        txt_director.setText("Режиссер: " + mov.getM_director());

        txt_description = (TextView) findViewById(R.id.txt_description);
        txt_description.setText("Описание: " + mov.getM_description());

        txt_countries = (TextView) findViewById(R.id.txt_country);
        txt_countries.setText("Страна: " + mov.getM_countries());

        txt_actors = (TextView) findViewById(R.id.txt_actors);
        txt_actors.setText("Актеры: " + mov.getM_actors());

        // txt_assessment = (TextView)findViewById(R.id.txt_assessment);
        //  txt_assessment.setText("Оценка: " + mov.getM_assessment());

        switch (mov.getM_assessment()) {
            case 1:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star));
                layout3.setBackground(getResources().getDrawable(R.drawable.star));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                break;
            case 2:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                break;
            case 3:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                break;
            case 4:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star2));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                break;
            case 5:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star2));
                layout5.setBackground(getResources().getDrawable(R.drawable.star2));
                break;
        }

        btn_other = (Button) findViewById(R.id.btn_other);
        btn_other.setOnClickListener(this);

        int idImg = SelectActivity.this.getResources().getIdentifier(mov.getM_img(), "drawable", getPackageName());
        img = (ImageView) findViewById(R.id.img);
        img.setImageDrawable(getResources().getDrawable(idImg));

        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);
        layout4 = (LinearLayout) findViewById(R.id.layout4);
        layout5 = (LinearLayout) findViewById(R.id.layout5);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        MovieDataBase database = new MovieDataBase(this);

        switch (v.getId()) {

            case R.id.btn_other:
                otherMovie();
                break;
            case R.id.layout1:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star));
                layout3.setBackground(getResources().getDrawable(R.drawable.star));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                database.setAssessment(mov.getM_Id(), 1);
                //mdb.setAssessment(mov.getM_Id(), 1);
                break;
            case R.id.layout2:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                database.setAssessment(mov.getM_Id(), 2);
                // mdb.setAssessment(mov.getM_Id(), 2);
                break;
            case R.id.layout3:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                database.setAssessment(mov.getM_Id(), 3);
                //mdb.setAssessment(mov.getM_Id(), 3);
                break;
            case R.id.layout4:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star2));
                layout5.setBackground(getResources().getDrawable(R.drawable.star));
                database.setAssessment(mov.getM_Id(), 4);
                //mdb.setAssessment(mov.getM_Id(), 4);
                break;
            case R.id.layout5:
                layout1.setBackground(getResources().getDrawable(R.drawable.star2));
                layout2.setBackground(getResources().getDrawable(R.drawable.star2));
                layout3.setBackground(getResources().getDrawable(R.drawable.star2));
                layout4.setBackground(getResources().getDrawable(R.drawable.star2));
                layout5.setBackground(getResources().getDrawable(R.drawable.star2));
                database.setAssessment(mov.getM_Id(), 5);
                //mdb.setAssessment(mov.getM_Id(), 5);
                break;

        }
    }
}
