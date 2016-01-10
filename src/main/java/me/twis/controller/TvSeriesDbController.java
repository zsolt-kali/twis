package me.twis.controller;

import me.twis.entity.MovieDbResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kalizsolt on 10/01/16.
 */
@Controller
public class TvSeriesDbController {
    public static final String MOVIE_DB_HOST = "https://api.themoviedb.org/3/search/tv?query=";

    public static final String MOVIE_DB_QUERY_KEY = "&api_key=";

    @Value("${theMovieDb.apiKey}")
    private String movieDbApiKey;

    @RequestMapping("/search/{title}")
    @ResponseBody
    public MovieDbResponse searchTvShowByTitle(@PathVariable String title) {
        RestTemplate restTemplate = new RestTemplate();
        MovieDbResponse result = restTemplate.getForObject(MOVIE_DB_HOST +
                title + MOVIE_DB_QUERY_KEY + movieDbApiKey, MovieDbResponse.class);

        return result;
    }
}