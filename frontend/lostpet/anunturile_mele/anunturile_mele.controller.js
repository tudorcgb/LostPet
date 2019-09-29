(function () {
    'use strict';

    angular
        .module('app')
        .controller('AnunturileMeleController', AnunturileMeleController);
  //angular.module('demo', [])
    AnunturileMeleController.$inject = ['$scope' ,'$http','$location','ListingService','$route','$routeParams','$rootScope'];
    function AnunturileMeleController($scope, $http, $location,ListingService,$route, $routeParams,$rootScope) {
        //$scope.listings = ListingService.GetAll();
        GetAllForUser();
        $scope.goLogin = function(listingId) {
        // window.location.replace('http://127.0.0.1:3000/listingDetail.html');
         window.location = '/listingDetail.html?id=' + listingId;
     };
        $scope.editClicked = function(listingId) {
            // window.location.replace('http://127.0.0.1:3000/listingDetail.html');
            //window.location = '/listingDetail.html?id=' + listingId;
            $rootScope.editArticleId = listingId;
            $location.path('/edit_anunt');
        };
        $scope.deleteClicked = function(listingId) {
            // window.location.replace('http://127.0.0.1:3000/listingDetail.html');
            //window.location = '/listingDetail.html?id=' + listingId;
            var r = confirm("Sunteti sigur/a ca vreti sa stergeti anuntul?");
            if (r == true) {
                ListingService.Delete(listingId);
                $route.reload();
                //$rootScope.editArticleId = listingId;
                //$location.path('/edit_anunt');
            } else {

            }


        };

        function GetAllForUser(){
            ListingService.GetByWriter($rootScope.globals.currentUser.id)
                .then(function (data) {
                    $scope.listings = data.data;
                    console.log( $scope.listings);
                });
            console.log($scope.listings);
        }
  }

})();

