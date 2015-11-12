package com.example.kinoman.Source;

import android.media.Image;

/**
 * Created by ivanka on 12.11.15.
 */
public class MovieForSearch {
    private int idMovie;
    private String  titleMovie;
    private String img;
    private int watchedOrNot;

    public MovieForSearch(int IdMovie, String TitleMovie, String Img, int WatchedOrNot) {
        this.idMovie = IdMovie;
        this.titleMovie = TitleMovie;
        this.img = Img;
        this.watchedOrNot = WatchedOrNot;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getWatchedOrNot() {
        return watchedOrNot;
    }

    public void setWatchedOrNot(int watchedOrNot) {
        this.watchedOrNot = watchedOrNot;
    }
}
