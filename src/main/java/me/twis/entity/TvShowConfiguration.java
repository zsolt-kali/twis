package me.twis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by kalizsolt on 17/01/16.
 */
public class TvShowConfiguration {


    @JsonProperty("change_keys")
    private List<String> changeKeys;

    private TvShowImages images;

    public TvShowImages getImages() {
        return images;
    }

    public void setImages(TvShowImages images) {
        this.images = images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }
}
