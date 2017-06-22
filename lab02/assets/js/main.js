var app = angular.module("lab02", []);

app.controller("lab02Controller", function ($scope,$http) {
	$scope.app = "Lab02";
	$scope.series = [];
	$scope.watchlist = [];
	$scope.profile = [];

	$scope.buscarSerie = function (serie) {
		$http.get("https://omdbapi.com/?s="+ serie +"&apikey=93330d3c&type=series").then(function (response) {

			if(response.data.Response == "True") {
				$scope.series = response.data.Search;
			} else {
				alert("A série não foi encontrada");
			};
			delete $scope.serie;
		});
	}

	$scope.addSerieWatchlist = function (serie) {
		if($scope.verificaArray(serie, $scope.watchlist)) {
			alert("A série selecionada já está na sua Watchlist");
		} else if ($scope.verificaArray(serie, $scope.profile)) {
			alert("A série selecionada já está no seu perfil.")
		} else {
			$scope.watchlist.push(serie);
		}
	}

	$scope.addSerieProfile = function (serie) {
		if ($scope.verificaArray(serie, $scope.profile)) {
			alert("A série selecionada já está no seu perfil.")
		} else {
			$scope.watchlist.push(serie);
		}
	}
});
