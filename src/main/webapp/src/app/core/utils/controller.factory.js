(function () {

	angular
		.module('core.utils')
		.factory('controller', controller);

	function controller() {

		var service = {
			criarRetornoPromise: criarRetornoPromise,
			ler: ler
		};

		return service;

		function criarRetornoPromise(exec, objeto) {
			var retorno = {};
			retorno.exec = exec;
			retorno.objeto = objeto;
			return retorno;
		}

		function ler(data, value) {
			if (value === undefined) {
				return data.data.data;
			}

			return data.data.data[value];
		}
	}
})();
