package com.example.kinoman.Source;

/**
 * Created by ivanka on 13.11.15.
 */
public class Genre {
    private int Id;
    private String name;


    public Genre(int Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
