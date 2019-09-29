(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get(baseAPI + '/user/list').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get(baseAPI + '/user/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.get(baseAPI + '/user/username/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            return $http.post(baseAPI + '/user/save', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put(baseAPI + "/user/update", user, {
                headers: {'Content-Type': 'application/json' },
                transformRequest: angular.identity
            })
            .then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete(baseAPI + '/user/delete/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            res.success = true;
            return res;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
