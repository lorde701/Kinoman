package com.example.kinoman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    //TextView txt_assessment;             
    Button btn_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        MovieDataBase mdb = new MovieDataBase(this);
        DBHelper dbh = new DBHelper(this);

        txt_title_movie = (TextView)findViewById(R.id.txt_title_movie);
        txt_title_movie.setText(mdb.selectTitleMovie());

        txt_year = (TextView)findViewById(R.id.txt_year);
        txt_year.setText("Год выпуска: " + mdb.selectYear());

        txt_genre = (TextView)findViewById(R.id.txt_genre);
        txt_genre.setText("Жанр: " + mdb.selectGenre());

        txt_director = (TextView)findViewById(R.id.txt_director);
        txt_director.setText("Режиссер: " + mdb.selectDirector());

        txt_description = (TextView)findViewById(R.id.txt_description);
        txt_description.setText("Описание: " + mdb.selectDescription());

        btn_other = (Button)findViewById(R.id.btn_other);
        btn_other.setOnClickListener(this);

        txt_countries = (TextView)findViewById(R.id.txt_country);
        txt_countries.setText("Страна: " + mdb.selectCountry());

        txt_actors = (TextView)findViewById(R.id.txt_actors);
        txt_actors.setText("Актеры: " + mdb.selectActors());

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
                Intent intent_select = new Intent(SelectActivity.this, SelectActivity.class);
                startActivity(intent_select);
                break;
        }
    }
}
