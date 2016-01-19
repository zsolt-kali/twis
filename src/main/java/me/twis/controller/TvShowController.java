package me.twis.controller;

import me.twis.util.TheMovieDbUriBuilder;
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
    @Value("${themoviedb.url}")
    private String theMovieDbRootUrl;

    @Value("${themoviedb.apikey}")
    private String movieDbApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/search/tv-show")
    public TvShowSearchResult searchTvShowByTitle(@PathParam("title") String title) {
        TvShowSearchResult result = restTemplate.getForObject(TheMovieDbUriBuilder.getSearchTvShowByTitleUrl(title), TvShowSearchResult.class);
        result.updateTvShowWithPosterPathPrefix(getPosterUrl());

        return result;
    }

    @RequestMapping("/dummy")
    public String getDummy() {
        return "dummy string";
    }

    private String getPosterUrl() {
        TvShowConfiguration configuration = restTemplate.getForObject(TheMovieDbUriBuilder.getConfigurationUrl(), TvShowConfiguration.class);

        return configuration.getImages().getBaseUrl() + configuration.getImages().getPosterSizes().get(0);
    }

    @RequestMapping("/validate/tv-show/id/{id}/season/{season}/episode/{episode}")
    private Validation validateEpisode(@PathVariable int id, @PathVariable int season, @PathVariable int episode) {
        try {
            restTemplate.getForObject(TheMovieDbUriBuilder.getRetrieveEpisodeInformationUrl(id, season, episode), Episode.class);
        } catch (HttpClientErrorException ex) {
            return new Validation(false);
        }

        return new Validation(true);
    }
}