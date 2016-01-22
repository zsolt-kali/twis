package me.twis.persistance;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by kalizsolt on 21/01/16.
 */
@Entity
public class FollowedTvShow {
    @Id
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("season")
    private short season;
    @JsonProperty("episode")
    private short episode;

    public FollowedTvShow() {}

    public FollowedTvShow(long id, String name, short season, short episode) {
        this.id = id;
        this.name = name;
        this.season = season;
        this.episode = episode;
    }
}
