package me.twis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by kalizsolt on 14/01/16.
 */
public class Episode {
    @JsonProperty("air_date")
    private Date airDate;

    private List<Person> crew;

    @JsonProperty("episode_number")
    private int episodeNumber;

    @JsonProperty("guest_stars")
    private List<Person> guestStars;

    private String name;

    private String overview;

    private int id;

    @JsonProperty("production_code")
    private String productionCode;

    @JsonProperty("season_number")
    private int seasonNumber;

    @JsonProperty("still_path")
    private String stillPath;

    @JsonProperty("voteAverage")
    private int voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    public Date getAirDate() {
        return airDate;
    }

    public void setAirDate(Date airDate) {
        this.airDate = airDate;
    }

    public List<Person> getCrew() {
        return crew;
    }

    public void setCrew(List<Person> crew) {
        this.crew = crew;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public List<Person> getGuestStars() {
        return guestStars;
    }

    public void setGuestStars(List<Person> guestStars) {
        this.guestStars = guestStars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
