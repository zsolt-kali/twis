package me.twis.controller;

import me.twis.entity.TvShow;
import me.twis.entity.TvShowConfiguration;
import me.twis.entity.TvShowImages;
import me.twis.entity.TvShowSearchResult;
import me.twis.util.TheMovieDbUriBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 * Created by kalizsolt on 10/01/16.
 */

public class TvShowControllerTest {
    public static final String TV_SHOW_TITLE = "TvShowTitle";
    public static final int TV_SHOW_ID = 123;
    public static final String TV_SHOW_IMAGES_BASE_URL = "tvShowImagesBaseUrl";
    public static final String POSTER_SIZE = "posterSize";
    public static final String PROP_KEY_MOVIE_DB_API_KEY = "movieDbApiKey";
    public static final String PROP_VALUE_API_KEY = "apiKey";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TvShowController underTest;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockServer = createServer(restTemplate);
        ReflectionTestUtils.setField(underTest, PROP_KEY_MOVIE_DB_API_KEY, PROP_VALUE_API_KEY);
    }

    @Test
    public void testDummyWithRestTemplate() throws Exception {
        TvShowSearchResult mockedResponse = new TvShowSearchResult();
        TvShow tvShow = new TvShow();
        tvShow.setId(TV_SHOW_ID);
        mockedResponse.setResults(Arrays.asList(tvShow));
        when(restTemplate.getForObject(TheMovieDbUriBuilder.getSearchTvShowByTitleUrl(TV_SHOW_TITLE), TvShowSearchResult.class)).thenReturn(mockedResponse);

        TvShowConfiguration tvShowConfiguration = new TvShowConfiguration();
        TvShowImages tvShowImages = new TvShowImages();
        tvShowImages.setBaseUrl(TV_SHOW_IMAGES_BASE_URL);
        tvShowImages.setPosterSizes(Arrays.asList(POSTER_SIZE));
        tvShowConfiguration.setImages(tvShowImages);
        when(restTemplate.getForObject(TheMovieDbUriBuilder.getConfigurationUrl(), TvShowConfiguration.class)).thenReturn(tvShowConfiguration);

        mockServer
                .expect(requestTo(TheMovieDbUriBuilder.getSearchTvShowByTitleUrl(TV_SHOW_TITLE)))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess());

        TvShowSearchResult tvShowSearchResult = underTest.searchTvShowByTitle(TV_SHOW_TITLE);

        Assert.assertEquals(TV_SHOW_ID, tvShowSearchResult.getResults().get(0).getId());
    }
}