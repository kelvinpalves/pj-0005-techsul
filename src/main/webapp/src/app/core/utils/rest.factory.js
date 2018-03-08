(function () {
	'use strict';

	angular
		.module('core.utils')
		.factory('RestUtils', dataservice);

	dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer'];

	function dataservice($http, $location, $q, $httpParamSerializer) {

		function RestUtilsFactory(urlFuncionalidade) {
			var service = {
				atualizar: atualizar,
				autocomplete: autocomplete,
				buscar: buscar,
				buscarCombo: buscarCombo,
				buscarTodos: buscarTodos,
				buscarTodosDebug: buscarTodosDebug,
				remover: remover,
				salvar: salvar,
				primeiro: primeiro,
				proximo: proximo,
				anterior: anterior,
				ultimo: ultimo,
				url: 'http://localhost:8080/TechSulSistemas/rs/' + urlFuncionalidade
			};

			return service;

			function atualizar(id, data) {
				return $http.put(service.url + '/' + id, data);
			}

			function autocomplete(valor) {
				return $http.get(service.url + '/autocomplete/' + valor);
			}

			function buscar(data) {	
				return $http.get(service.url + '/' + data);
			}

			function primeiro() {	
				return $http.get(service.url + '/primeiro');
			}

			function anterior(id) {	
				return $http.get(service.url + '/anterior/' + id);
			}

			function proximo(id) {	
				return $http.get(service.url + '/proximo/' + id);
			}

			function ultimo() {	
				return $http.get(service.url + '/ultimo');
			}

			function buscarCombo(){
				return $http.get(service.url + "/combo");
			}

			function buscarTodos(data) {
				return $http.get(service.url + '?' + data);
			}

			function buscarTodosDebug(data) {
				return $http.get(service.url);
			}
			
			function remover(data) {
				return $http.delete(service.url + '/' + data);
			}

			function salvar(data) {
				return $http.post(service.url, data);
			}

		}

		return RestUtilsFactory;
	}
})();