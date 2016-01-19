package me.twis.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by 212412346 on 1/19/2016.
 */
public final class TheMovieDbUriBuilder {
    @Value("${themoviedb.url}")
    private static String theMovieDbRootUrl;

    @Value("${themoviedb.apikey}")
    private static String movieDbApiKey;

    private static final String MOVIE_DB_SEARCH_TV = "search/tv";
    private static final String MOVIE_DB_SEARCH_EPISODE = "tv";
    public static final String MOVIE_DB_CONFIGURATION = "configuration";

    private static final String PATH_PARAM_QUERY = "query=";
    private static final String PATH_PARAM_API_KEY = "api_key=";

    private TheMovieDbUriBuilder() {
    }

    public static String getSearchTvShowByTitleUrl(String title) {
        return theMovieDbRootUrl + "/" + MOVIE_DB_SEARCH_TV + "?" +
                PATH_PARAM_QUERY + title + "&" + PATH_PARAM_API_KEY + movieDbApiKey;
    }

    public static String getConfigurationUrl() {
        return theMovieDbRootUrl + "/" + MOVIE_DB_CONFIGURATION + "?" + PATH_PARAM_API_KEY + movieDbApiKey;
    }

    public static String getRetrieveEpisodeInformationUrl(@PathVariable int id, @PathVariable int season, @PathVariable int episode) {
        return theMovieDbRootUrl + "/" + MOVIE_DB_SEARCH_EPISODE + "/" + id + "/season/" +
                season + "/episode/" + episode + "?" + PATH_PARAM_API_KEY + movieDbApiKey;
    }
}
