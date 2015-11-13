package com.example.kinoman;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.kinoman.Source.Genre;
import com.example.kinoman.Source.MovieDataBase;

import java.util.List;

public class SelectGenreActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_genre);

        MovieDataBase database = new MovieDataBase(this);

        List<Genre> list = database.getListGenre();

        LinearLayout lin = (LinearLayout)findViewById(R.id.linear);
        LayoutInflater Inflater = this.getLayoutInflater();

        for (Genre genre : list) {
            View item = Inflater.inflate(R.layout.for_select_movie, lin, false);

            RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

            RadioButton radioButton = (RadioButton)findViewById(R.id.radioButton);
            radioButton.setText(genre.getName());
//            radioButton.setText("GH");
            radioGroup.addView(radioButton);

            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            lin.addView(item);
        }

    }

    //RadioGroup  radiogroup = (RadioGroup )findViewByld(R.id.radioGroup);
    //RadioButton newRadioButton = new RadioButton(this);
    //newRadioButton.setText("Рыжий");
    //radiogroup.addView(newRadioButton);

    @Override
    public void onClick(View v) {

    }
}
