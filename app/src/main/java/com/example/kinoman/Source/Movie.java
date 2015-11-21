package com.example.kinoman.Source;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;
import java.util.Random;

/**
 * Created by ivanka on 09.11.15.
 */
public class Movie {
    private int m_Id;
    private String m_title;
    private String m_year;
    private String m_genre;
    private String m_countries;
    private String m_director;
    private String m_actors;
    private String m_description;
    private int m_assessment;
    private String m_img;

    public int getM_Id() {
        return m_Id;
    }

    public void setM_Id(int m_Id) {
        this.m_Id = m_Id;
    }

    public String getM_title() {
        return m_title;
    }

    public void setM_title(String m_title) {
        this.m_title = m_title;
    }

    public String getM_year() {
        return m_year;
    }

    public void setM_year(String m_year) {
        this.m_year = m_year;
    }

    public String getM_genre() {
        return m_genre;
    }

    public void setM_genre(String m_genre) {
        this.m_genre = m_genre;
    }

    public String getM_countries() {
        return m_countries;
    }

    public void setM_countries(String m_countries) {
        this.m_countries = m_countries;
    }

    public String getM_director() {
        return m_director;
    }

    public void setM_director(String m_director) {
        this.m_director = m_director;
    }

    public String getM_actors() {
        return m_actors;
    }

    public void setM_actors(String m_actors) {
        this.m_actors = m_actors;
    }

    public String getM_description() {
        return m_description;
    }

    public void setM_description(String m_description) {
        this.m_description = m_description;
    }

    public int getM_assessment() {
        return m_assessment;
    }

    public void setM_assessment(int m_assessment) {
        this.m_assessment = m_assessment;
    }

    public String getM_img() {
        return m_img;
    }

    public void setM_img(String m_img) {
        this.m_img = m_img;
    }

}