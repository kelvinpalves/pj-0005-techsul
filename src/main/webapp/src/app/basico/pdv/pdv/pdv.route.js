(function () {

    'use strict';

    angular
        .module('app')
        .config(routes);

    routes.$inject = ['$routeProvider', '$locationProvider'];

    function routes($routeProvider, $locationProvider) {
        $routeProvider
            .when('/basico/pdv/pdv', {
                templateUrl: 'src/app/basico/pdv/pdv/pdv.html?' + new Date().getTime(),
                controller: 'PDV',
                controllerAs: 'vm'
            });
    }

})();