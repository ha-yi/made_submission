package com.dr.kode.tercodingsubmit2.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dr.kode.tercodingsubmit2.model.themoviedb.TheMovieResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import androidx.annotation.Nullable;

public class IMDBApiCall implements IMDBCall {
    private final static String API_KEY = "0a19ad7e93395237a072b1f56464251e";
    private final static String ANIME_GENRES = "16";
    private final static String TYPE_MOVIE = "movie";
    private final static String TYPE_TV = "tv";
    private final static String PARAMS = "?api_key=" + API_KEY + "&language=en-US&with_genres=" + ANIME_GENRES;
    private final static String animeEP = "https://api.themoviedb.org/3/discover/";

    @Override
    public void getListOf(Context context, String type, int page, CallHandlerListOf<TheMovieResponse> handler) {
        String endPoint = animeEP + type + PARAMS + "&page=" + page;

        RequestQueue vol = Volley.newRequestQueue(context);
        StringRequest jor = new StringRequest(endPoint, data -> {
            Log.e("RESPONSE", data);
            Type lanimeType = new TypeToken<TheMovieResponse>() {}.getType();;
            handler.onData(new Gson().fromJson(data, lanimeType), data);
        }, (error) -> {
            Log.e("ERROR", "" + error.getMessage());
            handler.onData(null, error.getMessage());
        });
        vol.add(jor);
    }

    public void getlistOf(Context context, String type, int page, CallHandlerListOf<TheMovieResponse> handler) {
        getListOf(context, type, page,handler);
    }

    public interface CallHandlerListOf<T> {
        void onData(@Nullable T data, String message);
    }
}
