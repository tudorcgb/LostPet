(function () {
    'use strict';

    angular
        .module('app')
        .controller('AnuntDetailsController', AnuntDetailsController);

    var app = angular.module('app');

    var selectedFile;
    var fileName;

    AnuntDetailsController.$inject = ['$scope' ,'$http','ListingService','$rootScope','$location','$routeParams','CommentsService','UserService'];
    function AnuntDetailsController($scope,$http,ListingService,$rootScope,$location,$routeParams,CommentsService,UserService) {
        $scope.notLoggedIn = false;
        if($rootScope.loggedIn !== undefined) {

            $scope.userId = $rootScope.globals.currentUser.id;

        }else{
            console.log("not logged in");
            $scope.notLoggedIn = true;
        }
        var anuntId = $routeParams.idAnunt;
        getComments();
        function getComments() {
            ListingService.GetById(anuntId)
                .then(function (data) {
                    $scope.listingDetails = data.data;
                    $scope.imageSrc = $scope.listingDetails.imgUrl;
                    console.log($scope.listingDetails);
                    CommentsService.GetByListingId(anuntId)
                        .then(function (data) {
                            console.log( data.data);
                            $scope.comments = data.data;
                            $scope.comments.forEach(function(element) {
                                //var d = Date.parse(element.date);
                                var st = new Date(element.date).toLocaleString();
                                console.log(st);
                                element.date = st;
                                if($scope.userId === element.user ){
                                    element.belongsToUser = true;
                                }
                            });
                        });
                });
        }
        $scope.deleteClicked = function(commentId){
            CommentsService.Delete(commentId)
                .then(function (data){
                    getComments();
                    //$scope.newComment = '';
                    //console.log($scope.comment);
                });
        };


        $scope.addComment = function(){
            var d = new Date();

            $scope.comment = {};
            $scope.comment.user = $scope.userId ;
            $scope.comment.listing = anuntId;
            $scope.comment.comment = $scope.newComment;
            $scope.comment.date = d.getTime();
            $scope.comment.username = $rootScope.globals.currentUser.username;

            var formData = JSON.stringify(($scope.comment));

            CommentsService.Create(formData)
                .then(function (data){
                    getComments();
                    $scope.newComment = '';
                    console.log($scope.comment);
            });

        };
    }
})();


