package com.dr.kode.tercodingsubmit2;

import android.app.Application;

import io.realm.Realm;


public class TercodingApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
