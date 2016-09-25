package com.sgay.giligili.entity;

public class TorrentDetail {
    private Long id;

    private String torrentname;

    private String torrentsize;

    private String torrentuploadtime;

    private String torrentmagnet;

    private String keyword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTorrentname() {
        return torrentname;
    }

    public void setTorrentname(String torrentname) {
        this.torrentname = torrentname == null ? null : torrentname.trim();
    }

    public String getTorrentsize() {
        return torrentsize;
    }

    public void setTorrentsize(String torrentsize) {
        this.torrentsize = torrentsize == null ? null : torrentsize.trim();
    }

    public String getTorrentuploadtime() {
        return torrentuploadtime;
    }

    public void setTorrentuploadtime(String torrentuploadtime) {
        this.torrentuploadtime = torrentuploadtime == null ? null : torrentuploadtime.trim();
    }

    public String getTorrentmagnet() {
        return torrentmagnet;
    }

    public void setTorrentmagnet(String torrentmagnet) {
        this.torrentmagnet = torrentmagnet == null ? null : torrentmagnet.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }
}