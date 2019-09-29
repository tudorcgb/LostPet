(function () {
    'use strict';

    angular
        .module('app')
        .factory('ChatCommentService', ChatCommentService);

    ChatCommentService.$inject = ['$http'];
    function ChatCommentService($http) {
        var service = {};


        service.GetByChatCommentsById = GetByChatCommentsById;
        service.Create = Create;
        service.Delete = Delete;

        return service;
        function GetByChatCommentsById(userId) {
            return $http.get(baseAPI + '/chatComment/' + userId).then(handleSuccess, handleError('Error getting chats for user'));
        }

        function Create(comment) {
            return $http.post(baseAPI + '/chatComment/save', comment, {
                headers: {'Content-Type': 'application/json' },
                transformRequest: angular.identity
            })
                .then(handleSuccess, handleError('Error creating comment'));
        }

        function Delete(id) {
            return $http.delete(baseAPI + '/chatComment/' + id).then(handleSuccess, handleError('Error deleting user'));
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
