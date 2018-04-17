(function () {
    'use strict';

    angular
        .module('pdv.pdv')
        .factory('pdvRest', dataservice);

    dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

    function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
        var service = new RestUtils('basico/pdv/pdv');

        service.buscarProduto = buscarProduto;

        return service;

        function buscarProduto(data) {
            return $http.get(service.url + '/produto/' + data);
        }
    }
    
})();