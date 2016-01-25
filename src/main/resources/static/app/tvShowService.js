twisApp.service('tvShowService', function ($http) {
    this.getFollowedSeries = function (successHandler, failureHandler) {
        $http.get('/list/followed/tv-shows').then(
            successHandler,
            failureHandler
        );
    };

    this.getTvShowByTitle = function (title, successHandler, failureHandler) {
        return $http.get('/search/tv-show', {
            params: {
                title: title
            }
        }).then(
            successHandler,
            failureHandler);
    };

    this.followTvShow = function (tvShow, successHandler, failureHandler) {
        $http.post('/follow/tv-show',
            {
                id: tvShow.id,
                name: tvShow.name,
                season: tvShow.season,
                episode: tvShow.episode
            }
        ).then(
            successHandler,
            failureHandler
        );
    }

});