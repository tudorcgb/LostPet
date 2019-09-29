(function () {
    'use strict';

    angular
        .module('app')
        .factory('ChatService', ChatService);

    ChatService.$inject = ['$http'];
    function ChatService($http) {
        var service = {};


        service.GetByChatsForUser = GetByChatsForUser;
        service.Create = Create;
        service.Delete = Delete;

        return service;
        function GetByChatsForUser(userId) {
            return $http.get(baseAPI + '/chat/' + userId).then(handleSuccess, handleError('Error getting chats for user'));
        }

        function Create(newChat) {
            return $http.post(baseAPI + '/chat/save', newChat, {
                headers: {'Content-Type': 'application/json'},
                transformRequest: angular.identity
            })
                .then(handleSuccess, handleError('Error creating chat'));
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
