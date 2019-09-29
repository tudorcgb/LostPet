(function () {
    'use strict';

    angular
        .module('app')
        .controller('AdminController', AdminController);

    AdminController.$inject = ['$scope' ,'$http','ListingService','$rootScope','$location','$routeParams','CommentsService','UserService'];
    function AdminController($scope,$http,ListingService,$rootScope,$location,$routeParams,CommentsService,UserService) {

        UserService.GetAll()
            .then(function (data) {
                $scope.users = data.data;

            });


        $scope.groups = [];
        $scope.loadGroups = function() {
            return $scope.groups.length ? null : $http.get('/groups').success(function(data) {
                $scope.groups = data;
            });
        };

        $scope.showGroup = function(user) {
            if(user.group && $scope.groups.length) {
                var selected = $filter('filter')($scope.groups, {id: user.group});
                return selected.length ? selected[0].text : 'Not set';
            } else {
                return user.groupName || 'Not set';
            }
        };

        $scope.showStatus = function(user) {
            var selected = [];
            if(user.status) {
                selected = $filter('filter')($scope.statuses, {value: user.status});
            }
            return selected.length ? selected[0].text : 'Not set';
        };

        $scope.checkName = function(data, id) {
            // if (id === 2 && data !== 'awesome') {
            //     return "Username 2 should be `awesome`";
            // }
        };

        $scope.saveUser = function(data, user) {
            //$scope.user not updated yet
            angular.extend(data, {id: user.id});
            console.log(data);
            console.log(user);
            return UserService.Create(data)
                .then(function (data) {
                    //$scope.users = data;
                });

        };
        // remove user
        $scope.removeUser = function(index) {
            var user = $scope.users.splice(index, 1)[0];
            console.log(user);
            UserService.Delete(user.id)
                .then(function (data) {
                    //$scope.users = data;
                });

        };
        // add user
        $scope.addUser = function() {
            var d = new Date();
            $scope.inserted = {
                username: '',
                address : '',
                email: '',
                telephone: '',
                imgUrl: '',
                created: d.getTime()
            };
            $scope.users.push($scope.inserted);
        };
    }

})();
