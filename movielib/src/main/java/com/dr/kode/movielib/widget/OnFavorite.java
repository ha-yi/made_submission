package com.dr.kode.movielib.widget;

import com.dr.kode.movielib.themoviedb.Tontonan;

public interface OnFavorite {
    void onChanges();
    void add(Tontonan item, String type, String msg);
    void remove(int id, String msg);
    boolean isFavorite(int id);
}
