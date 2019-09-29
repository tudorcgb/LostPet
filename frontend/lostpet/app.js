
var baseAPI = "http://tudorcabau.go.ro:8089";

(function () {
    'use strict';

    var app = angular
        .module('app', ['ngRoute', 'ngCookies','ngMaterial','xeditable'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider

            .when('/home', {
                controller: 'HomeController',
                templateUrl: 'home/home.view.html',
                controllerAs: 'vm',
                title: 'Home'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm',
                title: 'Login'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'register/register.view.html',
                controllerAs: 'vm',
                title: 'Register'
            })
            .when('/gasit', {
                controller: 'PierdutController',
                templateUrl: 'pierdut/pierdut.view.html',
                controllerAs: 'vm',
                title: 'Found Dogs'
            })
            .when('/pierdut', {
                controller: 'PierdutController',
                templateUrl: 'pierdut/pierdut.view.html',
                controllerAs: 'vm',
                title: 'Lost Dogs'
            })
            .when('/profil', {
                controller: 'ProfilController',
                templateUrl: 'profil/profil.view.html',
                controllerAs: 'vm',
                title: 'Profile'
            })
            .when('/profil_details', {
                controller: 'ProfilController',
                templateUrl: 'profil/profil_details.view.html',
                controllerAs: 'vm',
                title: 'User Profile Details'
            })
            .when('/anunt', {
                controller: 'AnuntController',
                templateUrl: 'anunt/anunt.view.html',
                controllerAs: 'vm',
                title: 'New Ad'

            })
            .when('/places', {
                controller: 'AnuntController',
                templateUrl: 'places_test/places.view.html',
                controllerAs: 'vm'
            })
            .when('/anunturile_mele', {
                controller: 'AnunturileMeleController',
                templateUrl: 'anunturile_mele/anunturile_mele.view.html',
                controllerAs: 'vm',
                title: 'My Ads'

            })
            .when('/edit_anunt', {
                controller: 'AnuntController',
                templateUrl: 'anunt/anunt.view.html',
                controllerAs: 'vm',
                title: 'Edit Ad'
            })
            .when('/chat', {
                controller: 'ChatController',
                templateUrl: 'chat/chat.view.html',
                controllerAs: 'vm',
                title: 'Chat'
            })
            .when('/detalii_anunt/:idAnunt', {
                controller: 'AnuntDetailsController',
                templateUrl: 'anunt_details/anunt_details.view.html',
                controllerAs: 'vm',
                title: 'Ad Details'
            })
            .when('/detalii_user/:idUser', {
                controller: 'ProfilController',
                templateUrl: 'profil/profil_details.view.html',
                controllerAs: 'vm',
                title: 'User Details'
            })
            .when('/admin', {
                controller: 'AdminController',
                templateUrl: 'admin/admin.view.html',
                controllerAs: 'vm',
                title: 'Admin Home'
            })
            .when('/admin/breeds', {
                controller: 'BreedController',
                templateUrl: 'admin/breed/breed.view.html',
                controllerAs: 'vm',
                title: 'Manage Breeds'
            })
            .when('/admin/listings', {
                controller: 'ListingsController',
                templateUrl: 'admin/listings/listings.view.html',
                controllerAs: 'vm',
                title: 'Manage Listings'
            })
            .when('/license', {
                templateUrl: 'license.html',
                title: 'License'
            })
            .when('/', {
                redirectTo: '/home'
            })
            .otherwise({ redirectTo: '/login' });
    }
    app.directive('errSrc', function() {
        return {
            link: function(scope, element, attrs) {
                element.bind('error', function() {
                    if (attrs.src != attrs.errSrc) {
                        attrs.$set('src', attrs.errSrc);
                    }
                });
            }
        }
    });
    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    function run($rootScope, $location, $cookies, $http, $scope) {

        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.loggedIn = $rootScope.globals.currentUser;
        $rootScope.adImageDefault = "assets/img/dog_face.png";

        $rootScope.$on('$locationChangeStart', function (event, next, current) {

            if(( $location.url().indexOf('/admin') > -1) && !$rootScope.globals.isAdmin){
                $location.path('/login');
            }

            //todo remove '/adauga anunt'
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = true;
            var allowedPages = ['/login', '/register', '/gasit', '/pierdut','/places','/detalii_anunt','/license','/home','/'];
            var url = $location.url();
            allowedPages.forEach(function(element){
                if(url.indexOf(element) > -1){
                    console.log(element);
                    restrictedPage = false;
                }
            });
            //var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/gasit', '/pierdut','/places','/detalii_anunt']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            $rootScope.loggedIn = $rootScope.globals.currentUser;
            $rootScope.isAdmin = $rootScope.globals.isAdmin;
            $rootScope.baseAPIGlobal = baseAPI;

            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });

        //connect($rootScope);
    }
    //Changes page title
    app.run(['$rootScope', '$route', function($rootScope, $route) {
        $rootScope.$on('$routeChangeSuccess', function() {
            document.title = $route.current.title;
        });
    }]);
    // Closes responsive menu when a scroll trigger link is clicked
    $('.js-scroll-trigger').click(function() {
        $('.navbar-collapse').collapse('hide');
    });

    // Activate scrollspy to add active class to navbar items on scroll
    $('body').scrollspy({
        target: '#mainNav',
        offset: 56
    });

    // Collapse Navbar
    var navbarCollapse = function() {
        if ($("#mainNav").offset().top > 100) {
            $("#mainNav").addClass("navbar-shrink");
        } else {
            $("#mainNav").removeClass("navbar-shrink");
        }
    };
    // Collapse now if page is not at top
    navbarCollapse();
    // Collapse the navbar when page is scrolled
    $(window).scroll(navbarCollapse);

    // function connect($rootScope) {
    //     var socket = new SockJS(baseAPI + '/chat-websocket');
    //     var stompClient = Stomp.over(socket);
    //     stompClient.connect({}, function (frame) {
    //         //setConnected(true);
    //         console.log('Connected: ' + frame);
    //         stompClient.subscribe('/chat_notifications/' + $rootScope.globals.currentUser.username + '/', function (chatMessage) {
    //             console.log(chatMessage.body);
    //             console.log("newNotification");
    //         });
    //     });
    // }
    app.run(['$rootScope', function($rootScope) {
        var socket = new SockJS(baseAPI + '/chat-websocket');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            //setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/queue/greeting-as', function (chatMessage) {
                console.log(chatMessage.body);
                console.log("newNotification");
            });
        });
    }]);

})();