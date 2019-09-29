(function () {
    'use strict';

    angular
        .module('app')
        .factory('BreedService', BreedService);

    BreedService.$inject = ['$http'];
    function BreedService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.Create = Create;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get(baseAPI + '/breeds/list').then(handleSuccess, handleError('Error getting all breeds'));
        }

        function Create(breed) {
            return $http.post(baseAPI + '/breeds/save', breed).then(handleSuccess, handleError('Error creating breed'));
        }

        function Delete(id) {
            return $http.delete(baseAPI + '/breeds/delete/' + id).then(handleSuccess, handleError('Error deleting breed'));
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
