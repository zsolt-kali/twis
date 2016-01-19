package me.twis.controller;

import me.twis.entity.Episode;
import me.twis.entity.TvShowConfiguration;
import me.twis.entity.TvShowSearchResult;
import me.twis.entity.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

/**
 * Created by kalizsolt on 10/01/16.
 */
@RestController
public class TvShowController {
    private static final String MOVIE_DB_SEARCH_TV = "search/tv";
    private static final String MOVIE_DB_SEARCH_EPISODE = "tv";
    private static final String MOVIE_DB_CONFIGURATION = "configuration";
    private static final String PATH_PARAM_QUERY = "query=";
    private static final String PATH_PARAM_API_KEY = "api_key=";

    @Value("${themoviedb.url}")
    private String theMovieDbRootUrl;

    @Value("${themoviedb.apikey}")
    private String movieDbApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/search/tv-show")
    public TvShowSearchResult searchTvShowByTitle(@PathParam("title") String title) {
        TvShowSearchResult result = restTemplate.getForObject(getSearchTvShowByTitleUrl(title), TvShowSearchResult.class);
        result.updateTvShowWithPosterPathPrefix(getPosterUrl());

        return result;
    }

    private String getPosterUrl() {
        TvShowConfiguration configuration = restTemplate.getForObject(getConfigurationUrl(), TvShowConfiguration.class);

        return configuration.getImages().getBaseUrl() + configuration.getImages().getPosterSizes().get(0);
    }

    @RequestMapping("/validate/tv-show/id/{id}/season/{season}/episode/{episode}")
    private Validation validateEpisode(@PathVariable int id, @PathVariable int season, @PathVariable int episode) {
        try {
            restTemplate.getForObject(getRetrieveEpisodeInformationUrl(id, season, episode), Episode.class);
        } catch (HttpClientErrorException ex) {
            return new Validation(false);
        }

        return new Validation(true);
    }

    private String getSearchTvShowByTitleUrl(String title) {
        return theMovieDbRootUrl + "/" + MOVIE_DB_SEARCH_TV + "?" +
                PATH_PARAM_QUERY + title + "&" + PATH_PARAM_API_KEY + movieDbApiKey;
    }

    private String getConfigurationUrl() {
        return theMovieDbRootUrl + "/" + MOVIE_DB_CONFIGURATION + "?" + PATH_PARAM_API_KEY + movieDbApiKey;
    }

    private String getRetrieveEpisodeInformationUrl(@PathVariable int id, @PathVariable int season, @PathVariable int episode) {
        return theMovieDbRootUrl + "/" + MOVIE_DB_SEARCH_EPISODE + "/" + id + "/season/" +
                season + "/episode/" + episode + "?" + PATH_PARAM_API_KEY + movieDbApiKey;
    }
}