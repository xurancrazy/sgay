package com.sgay.giligili.entity;

import java.util.Date;

public class TorrentMap {
    private Integer id;

    private String keyword;

    private Date lastestQuery;

    private Long querycount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Date getLastestQuery() {
        return lastestQuery;
    }

    public void setLastestQuery(Date lastestQuery) {
        this.lastestQuery = lastestQuery;
    }

    public Long getQuerycount() {
        return querycount;
    }

    public void setQuerycount(Long querycount) {
        this.querycount = querycount;
    }
}