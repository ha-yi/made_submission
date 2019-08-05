package com.dr.kode.tercodingsubmit2.utils;

import android.content.Context;
import android.util.Log;

import com.dr.kode.movielib.themoviedb.FavoritesItem;
import com.dr.kode.movielib.themoviedb.Tontonan;
import com.dr.kode.movielib.themoviedb.TheMovieResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class IMDBDBCall implements IMDBCall {
    private static final String KEY_FAV = "favorites";


    @Override
    public void getListOf(Context context, String type, int page, IMDBApiCall.CallHandlerListOf<TheMovieResponse> handler) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<FavoritesItem> persons = realm.where(FavoritesItem.class)
                .contains("type", type)
                .sort("id")
                .findAll();
        List<Tontonan> data = new ArrayList<>();

        for (FavoritesItem fi : persons) {
            data.add(new Gson().fromJson(fi.getData(), Tontonan.class));
        }
        TheMovieResponse res = new TheMovieResponse();
        res.setResults(data);
        handler.onData(res, "success");
    }

    public static void add(Tontonan data, String type) {
        Log.e("DATA", "saving " + data.getId() + " NAME: " + data.getName());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FavoritesItem item = new FavoritesItem();
        item.setId(data.getId());
        item.setData(new Gson().toJson(data));
        item.setType(type);
        realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
    }

    public static boolean isFavorite(long id) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<FavoritesItem> persons = realm.where(FavoritesItem.class)
                .equalTo("id", id)
                .findAll();
        return !persons.isEmpty();
    }

    public static void remove(long id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FavoritesItem item = realm.where(FavoritesItem.class)
                .equalTo("id", id).findFirst();

        if (item != null) {
            item.deleteFromRealm();
        }
        realm.commitTransaction();
    }
}
