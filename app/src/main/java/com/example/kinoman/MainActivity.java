package com.example.kinoman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_about;
    Button btn_select;
    Button btn_search;
    Button btn_watched;
    Button btn_add;
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_about = (Button)findViewById(R.id.btn_about);
        btn_about.setOnClickListener(this);

        btn_select = (Button)findViewById(R.id.btn_select);
        btn_select.setOnClickListener(this);

        btn_search = (Button)findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        btn_watched = (Button)findViewById(R.id.btn_watched);
        btn_watched.setOnClickListener(this);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        // создаем объект для данных
        //ContentValues cv = new ContentValues();
        Intent intent;

        switch (v.getId()) {
            case R.id.btn_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_search:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            /*case R.id.btn_select:
                Intent intent_select = new Intent(MainActivity.this, SelectActivity.class);
                startActivity(intent_select);

                break;*/



            case R.id.btn_watched:
                intent = new Intent(MainActivity.this, WhatchedActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_add:
                intent =new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_select:
                intent = new Intent(this, SelectGenreActivity.class);
                startActivity(intent);
                break;
        }
    }

}
