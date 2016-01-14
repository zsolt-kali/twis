package me.twis.controller;

import me.twis.entity.Episode;
import me.twis.entity.TvShowSearchResult;
import me.twis.entity.Validation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kalizsolt on 10/01/16.
 */
@Controller
public class TvShowController {
    private static final String MOVIE_DB_HOST = "https://api.themoviedb.org/3";
    private static final String MOVIE_DB_SEARCH_TV = "search/tv";
    private static final String MOVIE_DB_SEARCH_EPISODE = "tv";

    private static final String PATH_PARAM_QUERY = "query=";
    private static final String PATH_PARAM_API_KEY = "api_key=";

    @Value("${themoviedb.apikey}")
    private String movieDbApiKey;

    @RequestMapping("/search/tv-show/title/{title}")
    @ResponseBody
    public TvShowSearchResult searchTvShowByTitle(@PathVariable String title) {
        RestTemplate restTemplate = new RestTemplate();
        TvShowSearchResult result = restTemplate.getForObject(MOVIE_DB_HOST + "/" + MOVIE_DB_SEARCH_TV + "?" +
                PATH_PARAM_QUERY + title + "&" + PATH_PARAM_API_KEY + movieDbApiKey, TvShowSearchResult.class);
        return result;
    }

    @RequestMapping("/validate/tv-show/id/{id}/season/{season}/episode/{episode}")
    @ResponseBody
    private Validation validateEpisode(@PathVariable int id, @PathVariable int season, @PathVariable int episode) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.getForObject(MOVIE_DB_HOST + "/" + MOVIE_DB_SEARCH_EPISODE + "/" + id + "/season/" +
                    season + "/episode/" + episode + "?" + PATH_PARAM_API_KEY + movieDbApiKey, Episode.class);
        } catch (HttpClientErrorException ex) {
            return new Validation(false);
        }

        return new Validation(true);
    }
}