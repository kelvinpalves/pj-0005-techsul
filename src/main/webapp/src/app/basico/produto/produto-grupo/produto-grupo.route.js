(function () {

	'use strict';

	angular
		.module('app')
		.config(routes);

	routes.$inject = ['$routeProvider', '$locationProvider'];

	function routes($routeProvider, $locationProvider) {
		$routeProvider
			.when('/basico/produto/produto-grupo', {
				templateUrl: 'src/app/basico/produto/produto-grupo/produto-grupo.html?' + new Date().getTime(),
				controller: 'ProdutoGrupo',
				controllerAs: 'vm'
			});
	}

})();