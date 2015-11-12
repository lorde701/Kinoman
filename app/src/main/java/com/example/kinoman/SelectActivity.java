package com.example.kinoman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        MovieDataBase mdb = new MovieDataBase(this);


        Movie mov = mdb.selectRandMovie();

        txt_title_movie = (TextView)findViewById(R.id.txt_title_movie);
        txt_title_movie.setText(mov.getM_title());

        txt_year = (TextView)findViewById(R.id.txt_year);
        txt_year.setText("Год выпуска: " + mov.getM_year());

        txt_genre = (TextView)findViewById(R.id.txt_genre);
        txt_genre.setText("Жанр: " + mov.getM_genre());

        txt_director = (TextView)findViewById(R.id.txt_director);
        txt_director.setText("Режиссер: " + mov.getM_director());

        txt_description = (TextView)findViewById(R.id.txt_description);
        txt_description.setText("Описание: " + mov.getM_description());

        txt_countries = (TextView)findViewById(R.id.txt_country);
        txt_countries.setText("Страна: " + mov.getM_countries());

        txt_actors = (TextView)findViewById(R.id.txt_actors);
        txt_actors.setText("Актеры: " + mov.getM_actors());

        txt_assessment = (TextView)findViewById(R.id.txt_assessment);
        txt_assessment.setText("Оценка: " + mov.getM_assessment());

        btn_other = (Button)findViewById(R.id.btn_other);
        btn_other.setOnClickListener(this);

        int idImg = SelectActivity.this.getResources().getIdentifier(mov.getM_img(), "drawable", getPackageName());
        img = (ImageView)findViewById(R.id.img);
        img.setImageDrawable(getResources().getDrawable(idImg));

    }

    protected void otherMovie() {
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        MovieDataBase mdb = new MovieDataBase(this);


        Movie mov = mdb.selectRandMovie();

        txt_title_movie = (TextView)findViewById(R.id.txt_title_movie);
        txt_title_movie.setText(mov.getM_title());

        txt_year = (TextView)findViewById(R.id.txt_year);
        txt_year.setText("Год выпуска: " + mov.getM_year());

        txt_genre = (TextView)findViewById(R.id.txt_genre);
        txt_genre.setText("Жанр: " + mov.getM_genre());

        txt_director = (TextView)findViewById(R.id.txt_director);
        txt_director.setText("Режиссер: " + mov.getM_director());

        txt_description = (TextView)findViewById(R.id.txt_description);
        txt_description.setText("Описание: " + mov.getM_description());

        txt_countries = (TextView)findViewById(R.id.txt_country);
        txt_countries.setText("Страна: " + mov.getM_countries());

        txt_actors = (TextView)findViewById(R.id.txt_actors);
        txt_actors.setText("Актеры: " + mov.getM_actors());

        txt_assessment = (TextView)findViewById(R.id.txt_assessment);
        txt_assessment.setText("Оценка: " + mov.getM_assessment());

        btn_other = (Button)findViewById(R.id.btn_other);
        btn_other.setOnClickListener(this);

        int idImg = SelectActivity.this.getResources().getIdentifier(mov.getM_img(), "drawable", getPackageName());
        img = (ImageView)findViewById(R.id.img);
        img.setImageDrawable(getResources().getDrawable(idImg));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select, menu);
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
        switch (v.getId()) {

            case R.id.btn_other:
                otherMovie();
                //Intent intent_select = new Intent(SelectActivity.this, SelectActivity.class);
                //startActivity(intent_select);
                break;
        }
    }
}
