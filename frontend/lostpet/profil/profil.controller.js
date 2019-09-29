(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProfilController', ProfilController);

    var app = angular.module('app');

    var selectedFile;
    var fileName;

    ProfilController.$inject = ['$scope' ,'$http','UserService','$rootScope','$location','$routeParams','ListingService','ChatService'];
    function ProfilController($scope,$http,UserService,$rootScope,$location,$routeParams,ListingService,ChatService) {

        $scope.profil = {};
        $scope.userId = $rootScope.globals.currentUser.id;
        $scope.username = $rootScope.globals.currentUser.username;

        console.log($scope.userId);
        var url = $location.url();
        if(url.indexOf('detalii_user') > -1) {
            console.log("detalii user");
            var userId = $routeParams.idUser;

            if($rootScope.globals.currentUser.id == userId) {
                console.log("own profile");
                $scope.isOwnProfile = true;
            }else{
                console.log("not own profile");
                $scope.isOwnProfile = false;
                $scope.userId = userId;
                GetAllForUser(userId);
            }
        }else{
            $scope.isOwnProfile = true;
        }

        initEditProfil($scope,UserService);
        
        $scope.onFileSelected = function (){
            console.log($scope.imageSrc);
            $scope.imageSrc = $scope.imageSrcSelected;
            console.log($scope.imageSrc);
        };

        $scope.updateProfileData = function() {


            var fd = new FormData();
            var d = new Date();

            function urltoFile(url, filename, mimeType){
                mimeType = mimeType || (url.match(/^data:([^;]+);/)||'')[1];
                return (fetch(url)
                        .then(function(res){return res.arrayBuffer();})
                        .then(function(buf){return new File([buf], filename, {type:mimeType});})
                );
            }
            var d = new Date();
            fileName = "poza_" +  $scope.userId + '.jpg';
            var profilePic;
            urltoFile($scope.imageSrc, fileName )
                .then(function(file){
                    profilePic = file;
                    console.log($scope.imageSrc);
                    $scope.profil.imgUrl = fileName;
                    console.log($scope.profil.new_password);
                    if(!isBlank($scope.profil.new_password)){
                        $scope.profil.password = $scope.profil.new_password;
                    }else{
                        $scope.profil.password = $scope.user.password;
                    }
                    //$scope.profil.password = "laal";
                    var formData = JSON.stringify(($scope.profil));
                    console.log(formData);
                    //console.log($scope.profil.imgUrl);
                    fd.append("file",profilePic, fileName);
                    if(UserService.Update(formData)){
                        console.log("user saved");
                        $http.post(baseAPI + "/images/", fd, {
                            headers: {'Content-Type': undefined },
                            transformRequest: angular.identity
                        });
                    }
                });

            // $http.post(baseAPI + "/listing/save", formData, {
            //
            //     headers: {'Content-Type': 'application/json' },
            //     transformRequest: angular.identity
            // });

        };
        $scope.imageSrc = "assets/img/dog_face_large.png";

        console.log($scope.imageSrc);
        $scope.$on("fileProgress", function(e, progress) {
            $scope.progress = progress.loaded / progress.total;
        });

        $scope.evaluateChange = function(obj,$event) {
            //var currentElement = $event.target;
            if ($scope.profil.new_password != $scope.profil.new_password_conf) {
                //console.log($scope.profil.new_password);//this will give you value of current element
                document.getElementById('id_password').setCustomValidity('Parolele nu se potrivesc');
            } else {
                // input is valid -- reset the error message
                document.getElementById('id_password').setCustomValidity('');
            }
        };

        function GetAllForUser(userId){
            ListingService.GetByWriter(userId)
                .then(function (data) {
                    $scope.listings = data.data;

                    console.log("get all for user:" +  $scope.listings);
                });
            console.log($scope.listings);
        }
        $scope.goToDetaliiAnunt = function(listingId) {

            $location.path('/detalii_anunt/' + listingId);

        };

        $scope.startNewChat = function () {
            console.log( "newChat Attempt");
            var newChat = {};
            newChat.sender = {};
            newChat.sender.id = $rootScope.globals.currentUser.id;
            newChat.sender.username = $rootScope.globals.currentUser.username;
            newChat.dest = {};
            newChat.dest.id = $scope.userId;
            newChat.dest.username = $scope.user.username;
            var newChatJSON = JSON.stringify((newChat));
            console.log(newChatJSON);
            ChatService.Create(newChatJSON)
                .then(function (data) {
                    //$scope.listings = data.data;

                    $location.path('/chat');

                });
            ///console.log($scope.listings);
        }
    }
    function isBlank(str) {
        return (!str || /^\s*$/.test(str));
    }

    function initEditProfil($scope,UserService){

        GetProfilById();

        function GetProfilById(){
            UserService.GetById($scope.userId)
                .then(function (data) {
                    $scope.user = data.data;
                    $scope.user.password = "";
                    console.log($scope.user);
                    fillForm();
                });
        }

        function fillForm(){
            $scope.profil = {};
            $scope.profil = $scope.user;
            $scope.imageSrc = baseAPI + "/images/files/" + $scope.profil.imgUrl;

            console.log($scope.profil);
        }
    }

})();

