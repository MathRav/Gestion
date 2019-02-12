console.log("services");
angular.module('App.services', []).factory('UserService', ["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};
    service.Inscription = function(userDto) {
        return $http.post(CONSTANTS.saveUser, userDto);
    }
    return service;

}]);