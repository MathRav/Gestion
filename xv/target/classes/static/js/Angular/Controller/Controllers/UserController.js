
var module = angular.module('App.controllers', []);
console.log("controllers");
module.controller("UserController",["$scope","UserService",function($scope, UserService){
    $scope.UserData={
        nom:null,
        password:null
    };
    $scope.saveUser=function(){
        UserService.Inscription($scope.UserData).then(function(value){
            console.log("gg");
            console.log(value);
        })

    }


}])