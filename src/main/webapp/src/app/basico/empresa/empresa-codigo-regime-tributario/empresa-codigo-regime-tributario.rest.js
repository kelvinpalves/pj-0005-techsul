(function () {
	'use strict';

	angular
		.module('empresa.empresa-codigo-regime-tributario')
		.factory('empresaCodigoRegimeTributarioRest', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

	function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
		return new RestUtils('basico/empresa/empresa-codigo-regime-tributario');
	}
	
})();