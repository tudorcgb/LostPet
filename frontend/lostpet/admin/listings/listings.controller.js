(function () {
    'use strict';

    angular
        .module('app')
        .controller('ListingsController', ListingsController);

    ListingsController.$inject = ['$scope' ,'$http','ListingService','$rootScope','$location','$routeParams','CommentsService','UserService','BreedService'];
    function ListingsController($scope,$http,ListingService,$rootScope,$location,$routeParams,CommentsService,UserService,BreedService) {

        BreedService.GetAll()
            .then(function (data) {
                $scope.breeds = data.data;

            });
        ListingService.GetAll()
            .then(function (data) {
                $scope.listings = data.data;
                console.log($scope.listings);
            });

        $scope.types = [
            {value: 0, name: 'Gasit'},
            {value: 1, name: 'Pierdut'}
        ];

        $scope.saveListing = function(data, breed) {
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
        $scope.removeListing = function(index) {
            var breed = $scope.breeds.splice(index, 1)[0];
            console.log(breed);
            BreedService.Delete(breed.id)
                .then(function (data) {
                    //$scope.users = data;
                });

        };
        // add user
        $scope.addListing = function() {

            $scope.inserted = {
                name: ''
            };
            $scope.breeds.push($scope.inserted);
        };

        $scope.showBreedNames = function(user) {
            // var selected = [];
            // // if(listing.breed.id) {
            // //     selected = $filter('filter')($scope.statuses, {value: user.status});
            // // }
            // return selected.length ? selected[0].text : 'Not set';
        };

        $scope.showType = function(listing) {
            var selected = [];
            if(listing.pierdut) {
                //selected = $filter('filter')($scope.statuses, {value: user.status});
                return selected = "Pierdut";
            }else{
                return selected = "Gasit";
            }
            return selected.length ? selected[0].text : 'Not set';
        };
    }

})();
