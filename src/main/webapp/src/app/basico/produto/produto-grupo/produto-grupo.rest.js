(function () {
	'use strict';

	angular
		.module('produto.produto-grupo')
		.factory('produtoGrupoRest', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

	function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
		return new RestUtils('basico/produto/produto-grupo');
	}
	
})();