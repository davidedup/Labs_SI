var app = angular.module("lab02", ['ngMaterial']);

app.controller("lab02Controller", function ($scope, $http, $mdDialog) {
	$scope.app = "Lab02";
	$scope.series = [];
	$scope.watchlist = [];
	$scope.profile = [];
	$scope.dialogSerie = {};

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
		if($scope.serieExists(serie, $scope.watchlist)) {
			alert("A série selecionada já está na sua Watchlist");
		} else if ($scope.serieExists(serie, $scope.profile)) {
			alert("A série selecionada já está no seu perfil.")
		} else {
			$scope.watchlist.push(serie);
		}
	}

	$scope.addSerieProfile = function (serie) {
		if ($scope.serieExists(serie, $scope.profile)) {
			alert("A série selecionada já está no seu perfil.")
		} else {
			$scope.profile.push(serie);
			if ($scope.serieExists(serie, $scope.watchlist)) {
				$scope.removeSerie(serie, $scope.watchlist);
			}
		}
	}

	$scope.removeSerie = function (serie, list) {
		if (confirm('Tem certeza que deseja remover "'+serie.Title+'"?') === true) {
			var index = list.indexOf(serie);
			if (index > -1) {
				list.splice(index, 1);
			}
		}
	};

	$scope.serieExists = function (serie, list) {
		return (list.indexOf(serie) != -1);
	};

	$scope.verInfo = function (ev, serie) {
		$http.get("https://omdbapi.com/?i="+ serie.imdbID +"&apikey=93330d3c&type=series").then(function (response) {
			$scope.serieDialog = response.data;
			
			$mdDialog.show({
				controller: DialogController,
				templateUrl: 'seriesInfo.html',
				parent: angular.element(document.body),
				targetEvent: ev,
				clickOutsideToClose:true,
				locals: {
					serieDialog: $scope.serieDialog
				}
			});
		});

	}

	function DialogController($scope, $mdDialog, serieDialog) {
		$scope.serie = serieDialog;

		$scope.hide = function() {
			$mdDialog.hide();
		};

		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		$scope.answer = function(answer) {
			$mdDialog.hide(answer);
		};
	}
});