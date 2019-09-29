(function () {
    'use strict';

    angular
        .module('app')
        .controller('BreedController', BreedController);

    BreedController.$inject = ['$scope' ,'$http','ListingService','$rootScope','$location','$routeParams','CommentsService','UserService','BreedService'];
    function BreedController($scope,$http,ListingService,$rootScope,$location,$routeParams,CommentsService,UserService,BreedService) {

        BreedService.GetAll()
            .then(function (data) {
                $scope.breeds = data.data;

            });
        $scope.saveBreed = function(data, breed) {
            //$scope.user not updated yet
            angular.extend(data, {id: breed.id});
            console.log(data);
            console.log(breed);
            return BreedService.Create(data)
                .then(function (data) {
                    //$scope.users = data;
                });

        };
        // remove user
        $scope.removeBreed = function(index) {
            var breed = $scope.breeds.splice(index, 1)[0];
            console.log(breed);
            BreedService.Delete(breed.id)
                .then(function (data) {
                    //$scope.users = data;
                });

        };
        // add user
        $scope.addBreed = function() {

            $scope.inserted = {
                name: ''
            };
            $scope.breeds.push($scope.inserted);
        };
    }

})();
