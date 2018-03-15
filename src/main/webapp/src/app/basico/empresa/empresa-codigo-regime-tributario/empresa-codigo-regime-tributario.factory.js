(function () {
	'use strict';

	angular
		.module('empresa.empresa-codigo-regime-tributario')
		.factory('empresaCodigoRegimeTributarioUtils', empresaCodigoRegimeTributarioUtils);

	empresaCodigoRegimeTributarioUtils.$inject = ['controller', 'empresaCodigoRegimeTributarioRest'];

	function empresaCodigoRegimeTributarioUtils(utils, dataservice) {
		var service = {
			carregarCombo: carregarCombo
		};

		return service;

		function carregarCombo() {
			return dataservice.buscarCombo().then(success).catch(error);

			function error(response) {
				return utils.criarRetornoPromise(false, []);
			}

			function success(response) {
				var array = utils.ler(response, 'ArrayList');
				return utils.criarRetornoPromise(true, array);
			}
		}
	}
})();