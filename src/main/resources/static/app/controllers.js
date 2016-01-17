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


};

angular.module("twis.controllers").controller("AppController", AppController);
