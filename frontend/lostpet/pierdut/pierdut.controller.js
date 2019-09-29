(function () {
    'use strict';

    angular
        .module('app')
        .controller('PierdutController', PierdutController);
  //angular.module('demo', [])
    var locationSelected;

    PierdutController.$inject = ['$scope' ,'$http','$location','ListingService'];
    function PierdutController($scope, $http, $location,ListingService) {
        $scope.searchParams = {};
        $scope.searchParams.searchString = "";
        locationSelected = {};
        locationSelected.viewport = {};
        locationSelected.viewport.ga = {};
        locationSelected.viewport.ma = {};


        var url = $location.url();
        if(url.indexOf('pierdut') > -1) {
            $scope.titluPagina = "Caini Pierduti: ";
            $scope.searchParams.pierdut = 1
        }else{
            $scope.titluPagina = "Caini Gasiti: ";
            $scope.searchParams.pierdut = 0;
        }
        initAutocomplete($scope);
      // $http.get(baseAPI + '/listing/list')
      //     .then(function(response) {
      //         $scope.listings = response.data;
      //         //$scope.imageSrc = "http://localhost:63343/LostPet/backend_main/templates/ " + response.data.imgUrl;
      //     });

        $scope.goToDetaliiAnunt = function(listingId) {
        // window.location.replace('http://127.0.0.1:3000/listingDetail.html');

            $location.path('/detalii_anunt/' + listingId);

     };
        $http.get(baseAPI + '/breeds/list').
        then(function(response) {
            $scope.rase = response.data;
            //$scope.imageSrc = "http://localhost:63342/LostPet/backend_main/templates/" + response.data.imgUrl;

        });

        $scope.searchByParams = function(){


            console.log("search by params triggered");
            console.log($scope.searchParams);
            //lng_start
            $scope.searchParams.lng = locationSelected.viewport.ga.j;
            //lng_end
            $scope.searchParams.lng_end = locationSelected.viewport.ga.l;
            //lat_start
            $scope.searchParams.lat = locationSelected.viewport.ma.j;
            //lat_end
            $scope.searchParams.lat_end = locationSelected.viewport.ma.l;
            if ($scope.selectedBreed == null){
            }else {
                $scope.searchParams.breed = $scope.selectedBreed.id;
            }
            var formData = JSON.stringify(($scope.searchParams));
            console.log(formData);
            ListingService.FindBySearchParameters(formData)
                .then(function(data) {
                $scope.listings = data.data;
                console.log($scope.listings);
            });

        };
        $scope.searchByParams();
    }
    function initAutocomplete($scope) {
        // var map = new google.maps.Map(document.getElementById('map'), {
        //     center: {lat: -33.8688, lng: 151.2195},
        //     zoom: 13,
        //     mapTypeId: 'roadmap'
        // });

        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        // map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        // map.addListener('bounds_changed', function() {
        //     searchBox.setBounds(map.getBounds());
        // });

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
                //lng_start
                console.log(place.geometry.viewport.ga.j);
                //lng_end
                console.log(place.geometry.viewport.ga.l);
                //lat_start
                console.log(place.geometry.viewport.ma.j);
                //lat_end
                console.log(place.geometry.viewport.ma.l);

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
            //map.fitBounds(bounds);
        });
    }
})();

