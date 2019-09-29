(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;

            AuthenticationService.Login(vm.username, vm.password, function (response) {
                var isAdmin = false;
                if (response.success) {
                    if(response.userRole === 'ADMIN'){
                        isAdmin = true;
                    }
                    console.log(response);

                    AuthenticationService.SetCredentials(response.id.id,vm.username, vm.password,isAdmin);

                    $location.path('/');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }

            });
        }
    }

})();
