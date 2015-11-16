package com.example.kinoman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private final String LOG_CAT = "search";
    Button search_btn_search;
    EditText search_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_btn_search = (Button)findViewById(R.id.search_btn_search);
        search_txt = (EditText)findViewById(R.id.search_txt);
        search_btn_search.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn_search:
                Intent intent = new Intent(this, SearchMoviesActivity.class);
                intent.putExtra("titleMovie", search_txt.getText().toString());
                startActivity(intent);
                Log.d(LOG_CAT, "Кнопка нажата");
                break;
        }
    }
}
