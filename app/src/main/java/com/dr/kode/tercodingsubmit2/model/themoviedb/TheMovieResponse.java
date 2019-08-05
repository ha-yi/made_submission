package com.dr.kode.tercodingsubmit2.model.themoviedb;

import java.io.Serializable;
import java.util.List;

import com.dr.kode.tercodingsubmit2.model.Tontonan;
import com.google.gson.annotations.SerializedName;


public class TheMovieResponse implements Serializable {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<Tontonan> results;

    @SerializedName("total_results")
    private int totalResults;

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setResults(List<Tontonan> results) {
        this.results = results;
    }

    public List<Tontonan> getResults() {
        return results;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }
}