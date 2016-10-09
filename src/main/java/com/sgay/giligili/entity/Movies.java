package com.sgay.giligili.entity;

public class Movies {
    private Long id;

    private String fanhao;

    private String imghref;

    private String title;

    private String publishtime;

    private Long viewsnum;

    private Long likesnum;

    private String teacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFanhao() {
        return fanhao;
    }

    public void setFanhao(String fanhao) {
        this.fanhao = fanhao == null ? null : fanhao.trim();
    }

    public String getImghref() {
        return imghref;
    }

    public void setImghref(String imghref) {
        this.imghref = imghref == null ? null : imghref.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime == null ? null : publishtime.trim();
    }

    public Long getViewsnum() {
        return viewsnum;
    }

    public void setViewsnum(Long viewsnum) {
        this.viewsnum = viewsnum;
    }

    public Long getLikesnum() {
        return likesnum;
    }

    public void setLikesnum(Long likesnum) {
        this.likesnum = likesnum;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }
}