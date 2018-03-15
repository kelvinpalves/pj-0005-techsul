(function () {

	'use strict';

	angular
		.module('app')
		.config(routes);

	routes.$inject = ['$routeProvider', '$locationProvider'];

	function routes($routeProvider, $locationProvider) {
		$routeProvider
			.when('/basico/empresa/empresa', {
				templateUrl: 'src/app/basico/empresa/empresa/empresa.html?' + new Date().getTime(),
				controller: 'Empresa',
				controllerAs: 'vm'
			});
	}

})();