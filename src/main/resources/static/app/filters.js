angular.module('twis.filters', []).filter('padToTwoChars', function () {
    return function (input) {
        if (('' + input).length === 1) {
            return '0' + input;
        }
        return input;
    };
});