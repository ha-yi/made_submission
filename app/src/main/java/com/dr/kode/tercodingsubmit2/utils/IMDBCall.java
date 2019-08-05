package com.dr.kode.tercodingsubmit2.utils;

import android.content.Context;

import com.dr.kode.tercodingsubmit2.model.themoviedb.TheMovieResponse;

public interface IMDBCall {
    void getListOf(Context context, String type, int page, IMDBApiCall.CallHandlerListOf<TheMovieResponse> handler);
}
