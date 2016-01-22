package me.twis.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kalizsolt on 21/01/16.
 */
@Repository
public interface TvShowRepository extends CrudRepository<FollowedTvShow, Long> {
}
