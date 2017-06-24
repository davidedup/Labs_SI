var app = angular.module("lab02", ['ngMaterial']);

app.controller("lab02Controller", function ($scope, $http, $mdDialog) {
	$scope.app = "Lab02";
	$scope.series = [];
	$scope.watchlist = [];
	$scope.profile = [];
	$scope.episodiosVistos = [];
	$scope.notas = [];
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
			alert('"'+serie.Title+'" já está na sua Watchlist');
		} else if ($scope.serieExists(serie, $scope.profile)) {
			alert('"'+serie.Title+'" já está no seu perfil.')
		} else {
			$scope.watchlist.push(serie);
			alert('"'+serie.Title+'" foi adicionada à sua Watchlist')
		}
	}

	$scope.addSerieProfile = function (serie) {
		if ($scope.serieExists(serie, $scope.profile)) {
			alert("A série selecionada já está no seu perfil.")
		} else {
			if ($scope.serieExists(serie, $scope.watchlist)) {
				$scope.profile.push(serie);
				$scope.episodiosVistos.push(0);
				$scope.notas.push(0);
				$scope.removeSerieWatchlist(serie);
				alert('"'+serie.Title+'" foi movida da sua watchlist para o seu perfil.')
			} else {
				$scope.profile.push(serie);
				$scope.episodiosVistos.push(0);
				$scope.notas.push(0);
				alert('"'+serie.Title+'" foi adicionada ao seu perfil')
			}

		}
	}

	$scope.removeSerieProfile = function (serie) {
		if (confirm('Tem certeza que deseja remover "'+serie.Title+'"?') === true) {
			var indexSerieProfile = $scope.profile.indexOf(serie);
			if (indexSerieProfile > -1) {
				$scope.profile.splice(indexSerieProfile, 1);
				$scope.episodiosVistos.splice(indexSerieProfile, 1);
				$scope.notas.splice(indexSerieProfile, 1);
				alert('"'+serie.Title+'" foi removida do seu perfil.')
			}
		}
	};

	$scope.removeSerieWatchlist = function (serie) {
		var indexSerieWatchlist = $scope.watchlist.indexOf(serie);
		
		if (indexSerieWatchlist > -1) {
			$scope.watchlist.splice(indexSerieWatchlist, 1);
		}
	}


	$scope.marcarEpisodio = function (episodio, serie) {
		var indexEpisodio = $scope.profile.indexOf(serie);

		if (indexEpisodio > -1) {
			if (Number.isInteger(parseInt(episodio))) {
				$scope.episodiosVistos[indexEpisodio] = episodio;
			} else {
				alert('Por favor, digite apenas o número do episódio visto')
			}
		}
	}

	$scope.atribuirNota = function (nota, serie) {
		var indexNota = $scope.profile.indexOf(serie);

		if (indexNota > -1) {
			$scope.notas[indexNota] = nota;
		}
	}

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

	$scope.serieExists = function (serie, list) {
		return (list.indexOf(serie) != -1);
	};

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