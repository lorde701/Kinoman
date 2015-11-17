package com.example.kinoman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kinoman.Source.MovieDataBase;
import com.example.kinoman.Source.MovieForSearch;

import java.util.List;

public class SearchMoviesActivity extends AppCompatActivity implements View.OnClickListener {


    private MovieDataBase dataBase;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);

        dataBase = new MovieDataBase(this);

        Intent intent = getIntent();
        String nameMovie = intent.getStringExtra("titleMovie");

        List<MovieForSearch> list = dataBase.getListMovie(nameMovie);

        linearLayout = (LinearLayout) findViewById(R.id.linear);
        LayoutInflater inflater = this.getLayoutInflater();




        if (list.isEmpty()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Оп-па-па")
                    .setMessage("Фильма с таким названием в базе данных нет")
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

        for (MovieForSearch movieForSearch : list) {
            View item = inflater.inflate(R.layout.for_search_movie, linearLayout, false);

            ImageView img = (ImageView) item.findViewById(R.id.img);
            int id_img = SearchMoviesActivity.this.getResources().getIdentifier(movieForSearch.getImg() + "small", "drawable", getPackageName());
            img.setImageDrawable(getResources().getDrawable(id_img));

            TextView title = (TextView) item.findViewById(R.id.txt_title);
            title.setText(movieForSearch.getTitleMovie());



            Log.d("qwerty", "Оценка: " + movieForSearch.getWatchedOrNot());


            if(movieForSearch.getWatchedOrNot() != 6) {
                ImageView assessment = (ImageView)findViewById(R.id.img_assessment);
                int id_img_assessment = SearchMoviesActivity.this.getResources().getIdentifier("s" + movieForSearch.getWatchedOrNot(), "drawable", getPackageName());
                assessment.setImageDrawable(getResources().getDrawable(id_img_assessment));
            }

            /*switch (movieForSearch.getWatchedOrNot()) {
                case 5:
                    id_img_assessment = SearchMoviesActivity.this.getResources().getIdentifier("s5", "drawable", getPackageName());
                    assessment.setImageDrawable(getResources().getDrawable(id_img_assessment));
                    break;
                case 1:
                    id_img_assessment = SearchMoviesActivity.this.getResources().getIdentifier("s1", "drawable", getPackageName());
                    assessment.setImageDrawable(getResources().getDrawable(id_img_assessment));
                    break;
                case 0:
                    id_img_assessment = SearchMoviesActivity.this.getResources().getIdentifier("eye1", "drawable", getPackageName());
                    assessment.setImageDrawable(getResources().getDrawable(id_img_assessment));
                    break;
                case 2:
                    id_img_assessment = SearchMoviesActivity.this.getResources().getIdentifier("s2", "drawable", getPackageName());
                    assessment.setImageDrawable(getResources().getDrawable(id_img_assessment));
                    break;
                case 3:
                    id_img_assessment = SearchMoviesActivity.this.getResources().getIdentifier("s3", "drawable", getPackageName());
                    assessment.setImageDrawable(getResources().getDrawable(id_img_assessment));
                    break;
                case 4:
                  /*  id_img_assessment = SearchMoviesActivity.this.getResources().getIdentifier("s4", "drawable", getPackageName());
                   assessment.setImageDrawable(getResources().getDrawable(id_img_assessment));
                    break;
            }*/

            // item.setOnClickListener(this);
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setId(movieForSearch.getIdMovie());
            item.setOnClickListener(this);

            linearLayout.addView(item);
            //linearLayout.setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        Log.d("qwerty", "SearchMovieActivity idMovie: " + id);
        Intent intent = new Intent(this, SelectActivity.class);
        intent.putExtra("flag", "SearchMoviesActivity");
        Log.d("qwerty", "intent.putExtra(\"flag\", \"SearchMoviesActivity\");");
        intent.putExtra("idMovie", id);
        startActivity(intent);
    }
}
