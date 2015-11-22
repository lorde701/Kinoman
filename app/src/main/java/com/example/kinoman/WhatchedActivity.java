package com.example.kinoman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kinoman.Source.MovieDataBase;
import com.example.kinoman.Source.ListMovie;

import java.util.List;

public class WhatchedActivity extends AppCompatActivity implements View.OnClickListener{

    private MovieDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatched);

        dataBase = new MovieDataBase(this);

        List<ListMovie> list = dataBase.getWatchedMovie();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        LayoutInflater inflater = this.getLayoutInflater();


        if (list.isEmpty()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Оп-па-па")
                    .setMessage("Просмотренных фильмов нет")
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

        for (ListMovie listMovie : list) {
            View item = inflater.inflate(R.layout.for_search_movie, linearLayout, false);

            ImageView img = (ImageView) item.findViewById(R.id.img);
            int id_img = WhatchedActivity.this.getResources().getIdentifier(listMovie.getImg() + "small", "drawable", getPackageName());
            img.setImageDrawable(getResources().getDrawable(id_img));

            TextView title = (TextView) item.findViewById(R.id.txt_title);
            title.setText(listMovie.getTitleMovie());
            Log.d("qwerty", "Название фильма in WatchedActivity: " + title.getText());

            if(listMovie.getWatchedOrNot() != 6) {
                ImageView assessment = (ImageView) item.findViewById(R.id.img_assessment);
                int id_img_assessment = WhatchedActivity.this.getResources().getIdentifier("s" + listMovie.getWatchedOrNot(), "drawable", getPackageName());
                assessment.setImageDrawable(getResources().getDrawable(id_img_assessment));
            }

            // item.setOnClickListener(this);
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setId(listMovie.getIdMovie());
            //int temp = WhatchedActivity.this.getResources().getIdentifier("blue", "drawable", getPackageName());
            //item.setBackgroundColor(12);
            //item.setBackground();
            item.setOnClickListener(this);

            linearLayout.addView(item);
        }
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.d("qwerty", "SearchMovieActivity idMovie: " + id);
        Intent intent = new Intent(this, SelectActivity.class);
        intent.putExtra("flag", "WhatchedActivity");
        Log.d("qwerty", "intent.putExtra(\"flag\", \"SearchMoviesActivity\");");
        intent.putExtra("idMovie", id);
        startActivity(intent);
    }
}
