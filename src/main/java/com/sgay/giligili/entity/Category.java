package com.sgay.giligili.entity;

import java.util.List;

/**
 * Created by xurancrazy on 2016/12/3.
 */
public class Category {
    private int id;
    private String name;
    private int movieNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }
}
