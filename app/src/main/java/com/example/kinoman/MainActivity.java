package com.example.kinoman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_about;
    Button btn_select;
    Button btn_search;
    Button btn_watched;

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
        switch (v.getId()) {
            case R.id.btn_about:
                Intent intent_about = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent_about);
                break;
            case R.id.btn_search:
                Intent intent_search = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent_search);
                break;
            case R.id.btn_select:
                Intent intent_select = new Intent(MainActivity.this, SelectActivity.class);
                startActivity(intent_select);
                break;
            case R.id.btn_watched:
                Intent intent_watched = new Intent(MainActivity.this, WhatchedActivity.class);
                startActivity(intent_watched);
                break;

        }
    }
}
