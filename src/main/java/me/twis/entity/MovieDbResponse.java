package me.twis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by kalizsolt on 10/01/16.
 */
public class MovieDbResponse {
    private int page;

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("total_pages")
    private int totalPages;

    private List<MovieDbResponseResult> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieDbResponseResult> getResults() {
        return results;
    }

    public void setResults(List<MovieDbResponseResult> results) {
        this.results = results;
    }
}