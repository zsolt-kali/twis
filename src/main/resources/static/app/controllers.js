var AppController = function ($scope, $http) {

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

    var getFollowedSeries = function () {
        $http.get('/list/followed/tv-shows').then(function (response) {
            $scope.followedSeries = response.data;
        });
    };

    $scope.followedSeries = getFollowedSeries();
};

angular.module("twis.controllers").controller("AppController", AppController);
