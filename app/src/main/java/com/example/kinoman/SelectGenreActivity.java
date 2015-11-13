package com.example.kinoman;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.kinoman.Source.Genre;
import com.example.kinoman.Source.MovieDataBase;

import java.util.List;

public class SelectGenreActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_do;

    RadioButton radioButton;
    MovieDataBase database = new MovieDataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_genre);

       // btn_do = (Button).findViewById(R.id.btn_do);




        List<Genre> list = database.getListGenre();

        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);

        //LinearLayout lin = (LinearLayout)findViewById(R.id.linear);
        LayoutInflater Inflater = this.getLayoutInflater();

        int i = 50;

        View item = Inflater.inflate(R.layout.for_select_movie, rg, false);

        //RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        //RadioButton radioButton = (RadioButton)findViewById(R.id.radioButton);
        radioButton = (RadioButton)item.findViewById(R.id.radioButton);

        radioButton.setText("Без жанра");

        radioButton.setId(i++);
        radioButton.setOnClickListener(this);

//            radioButton.setText("GH");
        //radioGroup.addView(radioButton);

        item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        rg.addView(item);

        for (Genre genre : list) {
            item = Inflater.inflate(R.layout.for_select_movie, rg, false);

            //RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

            //RadioButton radioButton = (RadioButton)findViewById(R.id.radioButton);
            radioButton = (RadioButton)item.findViewById(R.id.radioButton);

            radioButton.setText(genre.getName());

            radioButton.setId(i++);
//            radioButton.setText("GH");
            //radioGroup.addView(radioButton);

            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            rg.addView(item);
        }

    }

    //RadioGroup  radiogroup = (RadioGroup )findViewByld(R.id.radioGroup);
    //RadioButton newRadioButton = new RadioButton(this);
    //newRadioButton.setText("Рыжий");
    //radiogroup.addView(newRadioButton);

    @Override
    public void onClick(View v) {

        Intent intent = null;




        switch (v.getId()) {

            case R.id.btn_do:

                if(radioButton.isChecked()) {
                    intent = new Intent(this, SearchActivity.class);
                    startActivity(intent);
                }
/*
                switch (radioButton.getId()) {

                    case 50:
                        intent = new Intent(this, SearchActivity.class);

                }

                //intent = Intent(this, );

            case 50:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);*/
        }
       // startActivity(intent);

    }
}
