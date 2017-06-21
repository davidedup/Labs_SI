var app = angular.module("lab02", []);

app.controller("lab02Controller", function ($scope,$http) {
	$scope.app = "Lab02";
	$scope.series = [];
	$scope.buscarSerie = function (serie) {
		$http.get("https://omdbapi.com/?s="+ serie +"&apikey=93330d3c&type=series").then(function (response) {
			$scope.series = response.data.Search;
			delete $scope.serie;
		});
	}
});
