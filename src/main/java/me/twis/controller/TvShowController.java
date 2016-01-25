package me.twis.controller;

import me.twis.entity.*;
import me.twis.persistance.FollowedTvShow;
import me.twis.persistance.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.List;

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

    @Autowired
    TvShowRepository tvShowRepository;

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

    @RequestMapping(value = "/follow/tv-show", method = RequestMethod.PUT, headers = {"Content-type=application/json"})
    public void followTvShow(@RequestBody FollowedTvShow tvShow) {
        tvShowRepository.save(tvShow);
    }

    @RequestMapping(value = "/unfollow/tv-show", method = RequestMethod.DELETE)
    public void unfollowTvShow(@PathParam("tvShowId") Long tvShowId) {
        tvShowRepository.delete(tvShowId);
    }

    @RequestMapping("/list/followed/tv-shows")
    public Iterable<FollowedTvShow> getFollowedTvShows() {
        return tvShowRepository.findAll();
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