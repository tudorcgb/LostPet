(function () {
    'use strict';

    angular
        .module('app')
        .controller('AnuntController', AnuntController);

    var app = angular.module('app');

    var locationSelected ;
    var selectedFile;
    var fileName;

    AnuntController.$inject = ['$scope' ,'$http','ListingService','$rootScope','$location'];
    function AnuntController($scope,$http,ListingService,$rootScope,$location) {

        if($location.path() == '/edit_anunt') {
            console.log($rootScope.editArticleId);
            //$rootScope.editArticleId = ;
            $scope.idAnunt = $rootScope.editArticleId;
            initEditAnunt($scope,ListingService);
        }

        initAutocomplete($scope);


    $scope.uploadFile = function() {
        var fd = new FormData();
        var d = new Date();
        console.log($rootScope.globals.currentUser);
        //fileName = "" + $rootScope.globals.currentUser.id +  d.getTime() + '.jpg';



        $scope.advert.pierdut = $scope.tipAnunt;
        $scope.advert.writer = {
            "id": $rootScope.globals.currentUser.id,
            "name": $rootScope.globals.currentUser.username};
        $scope.advert.breed ={
            "id": $scope.selectedRasa.id,
            "name": $scope.selectedRasa.name};

        $scope.advert.imgUrl = fileName;
        //lng_start
        $scope.advert.lng = locationSelected.viewport.ga.j;
        //lng_end
        $scope.advert.lng_end = locationSelected.viewport.ga.l;
        //lat_start
        $scope.advert.lat = locationSelected.viewport.ma.j;
        //lat_end
        $scope.advert.lat_end = locationSelected.viewport.ma.l;

        //$scope.advert.adress = $scope.adress;

        var formData = JSON.stringify(($scope.advert));
        ListingService.Create(formData)
            .then(function (data) {
                // $scope.listings = data.data;
                fd.append("file",selectedFile, fileName);

                $http.post(baseAPI + "/images/", fd, {
                    headers: {'Content-Type': undefined },
                    transformRequest: angular.identity
                });

                $location.path('/anunturile_mele');
            });

    };

    $http.get(baseAPI + '/breeds/list').
    then(function(response) {
        $scope.rase = response.data;
        //$scope.imageSrc = "http://localhost:63342/LostPet/backend_main/templates/" + response.data.imgUrl;

    });
    $scope.imageSrc = "assets/img/dog_face_add_pic.png";

    console.log($scope.imageSrc);
    $scope.$on("fileProgress", function(e, progress) {
        $scope.progress = progress.loaded / progress.total;
    });
}

    app.directive("ngFileSelect", function(fileReader, $timeout,$rootScope) {

        return {
            scope: {
                ngModel: '='
            },
            link: function($scope, el) {
                function getFile(file) {
                    $scope.myFileFile = file;
                    selectedFile = file;
                    console.log($scope.myFileFile);
                    fileReader.readAsDataUrl(file, $scope)
                        .then(function(result) {
                            $scope.imageSrc = result;
                            var d = new Date();
                            fileName = d.getTime() + '.jpg';
                            $scope.fileName = fileName;
                            $timeout(function() {
                                $scope.ngModel = result;
                            });
                        });
                }

                el.bind("change", function(e) {
                    var file = (e.srcElement || e.target).files[0];

                    getFile(file);

                });
            }
        };
    });
    app.factory("fileReader", function($q, $log) {
        var onLoad = function(reader, deferred, scope) {
            return function() {
                scope.$apply(function() {
                    deferred.resolve(reader.result);
                });
            };
        };

        var onError = function(reader, deferred, scope) {
            return function() {
                scope.$apply(function() {
                    deferred.reject(reader.result);
                });
            };
        };

        var onProgress = function(reader, scope) {
            return function(event) {
                scope.$broadcast("fileProgress", {
                    total: event.total,
                    loaded: event.loaded
                });
            };
        };

        var getReader = function(deferred, scope) {
            var reader = new FileReader();
            reader.onload = onLoad(reader, deferred, scope);
            reader.onerror = onError(reader, deferred, scope);
            reader.onprogress = onProgress(reader, scope);
            return reader;
        };

        var readAsDataURL = function(file, scope) {
            var deferred = $q.defer();

            var reader = getReader(deferred, scope);
            reader.readAsDataURL(file);

            return deferred.promise;
        };

        return {
            readAsDataUrl: readAsDataURL
        };
    });

    function initEditAnunt($scope,ListingService){

        GetAnuntById();




        function GetAnuntById(){
            ListingService.GetById($scope.idAnunt)
                .then(function (data) {
                    $scope.listing = data.data;
                    console.log($scope.listing);
                    fillForm();
                });

        }

        function fillForm(){
            $scope.advert = {};
            $scope.advert = $scope.listing;
            $scope.selectedRasa = $scope.listing.breed;
            if($scope.listing.pierdut) {
                $scope.pierdutSelected = true;
                $scope.advert.pierdut = true;
            }else{
                $scope.gasitSelected = true;
                $scope.advert.pierdut = false;
            }
            $scope.imageSrc = baseAPI + "/images/files/" + $scope.listing.imgUrl;
            locationSelected = {};
            locationSelected.viewport = {};
            locationSelected.viewport.ga = {};
            locationSelected.viewport.ma = {};

            locationSelected.viewport.ga.j = $scope.listing.lng;
            //lng_end
            locationSelected.viewport.ga.l = $scope.listing.lng_end;
            //lat_start
            locationSelected.viewport.ma.j = $scope.listing.lat;
            //lat_end
            locationSelected.viewport.ma.l = $scope.listing.lat_end;

            fileName = $scope.listing.imgUrl;

            console.log($scope.advert);
        }


    }

    function initAutocomplete($scope) {

        // Create the search box and link it to the UI element.
        var input = document.getElementById('address_input');
        var searchBox = new google.maps.places.SearchBox(input);

        var markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
            var places = searchBox.getPlaces();

            if (places.length == 0) {
                return;
            }

            // Clear out the old markers.
            markers.forEach(function(marker) {
                //marker.setMap(null);
                console.log(marker.getPosition());
            });
            markers = [];

            // For each place, get the icon, name and location.
            var bounds = new google.maps.LatLngBounds();
            //console.log(bounds.ga.j);
            places.forEach(function(place) {
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                var icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };
                locationSelected = place.geometry;
                $scope.advert.adress = place.name;

                // Create a marker for each place.
                markers.push(new google.maps.Marker({
                    //map: map,
                    icon: icon,
                    title: place.name,

                    position: place.geometry.location
                }));
                markers.forEach(function(marker) {
                    //marker.setMap(null);
                    console.log(marker.getPosition());
                });
                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);

                } else {
                    bounds.extend(place.geometry.location);
                }
            });

        });
    }
})();

