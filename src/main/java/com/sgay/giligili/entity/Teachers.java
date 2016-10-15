package com.sgay.giligili.entity;

public class Teachers {
    private Long id;

    private String name;

    private Integer moviesnum;

    private Long likesnum;

    private String img;

    private Long onclicknum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMoviesnum() {
        return moviesnum;
    }

    public void setMoviesnum(Integer moviesnum) {
        this.moviesnum = moviesnum;
    }

    public Long getLikesnum() {
        return likesnum;
    }

    public void setLikesnum(Long likesnum) {
        this.likesnum = likesnum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Long getOnclicknum() {
        return onclicknum;
    }

    public void setOnclicknum(Long onclicknum) {
        this.onclicknum = onclicknum;
    }

}