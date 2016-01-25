var AppController = function ($scope, $http, tvShowService) {
    var getFolloedSeriesSuccessHandler = function (response) {
        $scope.followedSeries = response.data;
    };
    var getFolloedSeriesErrorHandler = function () {
        // TODO: add error handler
        $scope.followedSeries = {};
    };
    tvShowService.getFollowedSeries(getFolloedSeriesSuccessHandler, getFolloedSeriesErrorHandler);

    var getTvShowByTitleSuccessHandler = function (response) {
        return response.data.results.map(function (item) {
            return item;
        });
    };
    var getTvShowByTitleErrorHandler = function () {
        //TODO: implement error handler
    };
    $scope.getTvShowByTitle = function (title) {
        return tvShowService.getTvShowByTitle(title, getTvShowByTitleSuccessHandler, getTvShowByTitleErrorHandler);
    };

    var followTvShowSuccessHandler = function () {
        $scope.asyncSelected = "";
        tvShowService.getFollowedSeries(getFolloedSeriesSuccessHandler, getFolloedSeriesErrorHandler);
    };
    var followTvShowErrorHandler = function () {
        //TODO: add error handler
        $scope.asyncSelected = "";
    };
    $scope.onSelect = function ($item) {
        tvShowService.followTvShow(
            {
                id: $item.id,
                name: $item.name,
                season: $item.season,
                episode: $item.episode
            }, followTvShowSuccessHandler, followTvShowErrorHandler);
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
