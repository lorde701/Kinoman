package com.example.kinoman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.kinoman.Source.MovieDataBase;
import com.example.kinoman.Source.DBHelper;

public class SelectActivity extends AppCompatActivity {

    TextView txt_title_movie;
    TextView txt_year;
    TextView txt_genre;
    //TextView txt_countries;
    TextView txt_director;
    //TextView txt_actors;
    TextView txt_description;
    //TextView txt_assessment;             

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
        //txt_genre.setText("Жанр: " + mdb.selectInfoMovie());

        txt_director = (TextView)findViewById(R.id.txt_director);
        txt_director.setText("Режиссер: " + mdb.selectDirector());

        txt_description = (TextView)findViewById(R.id.txt_description);
        txt_description.setText("Описание: " + mdb.selectDescription());

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
}
