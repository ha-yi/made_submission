package com.dr.kode.movielib.themoviedb;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FavoritesItem extends RealmObject {
    @PrimaryKey
    private long id;
    private String type;
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
