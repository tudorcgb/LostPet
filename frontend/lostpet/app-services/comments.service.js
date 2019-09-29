(function () {
    'use strict';

    angular
        .module('app')
        .factory('CommentsService', CommentsService);

    CommentsService.$inject = ['$http'];
    function CommentsService($http) {
        var service = {};


        service.GetByListingId = GetByListingId;
        service.Create = Create;
        service.Delete = Delete;

        return service;
        function GetByListingId(id) {
            return $http.get(baseAPI + '/comments/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function Create(comment) {
            return $http.post(baseAPI + '/comments/save', comment, {
                headers: {'Content-Type': 'application/json' },
                transformRequest: angular.identity
            })
                .then(handleSuccess, handleError('Error creating comment'));
        }

        function Delete(id) {
            return $http.delete(baseAPI + '/comments/' + id).then(handleSuccess, handleError('Error deleting user'));
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
