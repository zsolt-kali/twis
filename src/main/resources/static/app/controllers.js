var AppController = function ($scope, $http) {
    var getFollowedSeries = function () {
        $http.get('/list/followed/tv-shows').then(function (response) {
            $scope.followedSeries = response.data;
        });
    };

    $scope.followedSeries = getFollowedSeries();

    $scope.getTvShowByTitle = function (title) {
        return $http.get('/search/tv-show', {
            params: {
                title: title
            }
        }).then(function (response) {
            return response.data.results.map(function (item) {
                return item;
            });
        });
    };

    $scope.onSelect = function ($item) {
        $http.post('/follow/tv-show',
            {
                id: $item.id,
                name: $item.name,
                season: $item.season,
                episode: $item.episode
            })
            .then(
            function (responseSuccess) {
                $scope.asyncSelected = "";
                getFollowedSeries();
            },
            function (responseError) {
                //TODO: add error handler
                $scope.asyncSelected = "";
            });
    };

    $scope.switchMode = function (tvShow) {
        if (tvShow.mode !== 'edit') {
            tvShow.mode = 'edit';
        }
        else {
            tvShow.mode = 'browse';
        }
    };

    $scope.unfollowTvShow = function (tvShow) {
        //TODO: not implemented
    };

    $scope.increaseSeason = function (tvShow) {
        tvShow.season += 1;
    };

    $scope.decreaseSeason = function (tvShow) {
        if (tvShow.season > 0) {
            tvShow.season -= 1;
        }
    };

    $scope.increaseEpisode = function (tvShow) {
        tvShow.episode += 1;
    };

    $scope.decreaseEpisode = function (tvShow) {
        if (tvShow.episode > 0) {
            tvShow.episode -= 1;
        }
    };
};

angular.module("twis.controllers").controller("AppController", AppController);
