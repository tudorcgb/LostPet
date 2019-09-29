(function () {
    'use strict';

    angular
        .module('app')
        .factory('ListingService', ListingService);

    ListingService.$inject = ['$http'];
    function ListingService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByWriter = GetByWriter;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.FindBySearchParameters = FindBySearchParameters;

        return service;

        function GetAll() {
            return $http.get(baseAPI + '/listing/list').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get(baseAPI + '/listing/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByWriter(writer_id) {
            return $http.get(baseAPI + '/listing/writer/' + writer_id).then(handleSuccess, handleError('Error getting user by writer_id'));
        }

        function Create(comment) {
            return $http.post(baseAPI + '/listing/save', comment, {
                headers: {'Content-Type': 'application/json' },
                transformRequest: angular.identity
            })
                .then(handleSuccess, handleError('Error creating listing'));
        }

        function Update(listing) {
            return $http.put(baseAPI + '/listing/listing', listing).then(handleSuccess, handleError('Error updating listing'));
        }

        function Delete(id) {
            return $http.delete(baseAPI + '/listing/delete/' + id).then(handleSuccess, handleError('Error deleting listing'));
        }

        function FindBySearchParameters(params) {
            return $http.post(baseAPI + '/listing/search', params, {
                headers: {'Content-Type': 'application/json' },
                transformRequest: angular.identity
            })
                .then(handleSuccess, handleError('Error updating user'));
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
