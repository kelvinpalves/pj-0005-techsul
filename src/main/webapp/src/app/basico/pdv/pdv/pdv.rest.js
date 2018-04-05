(function () {
    'use strict';

    angular
        .module('pdv.pdv')
        .factory('pdvRest', dataservice);

    dataservice.$inject = ['$http', '$location', '$q', '$httpParamSerializer', 'RestUtils'];

    function dataservice($http, $location, $q, $httpParamSerializer, RestUtils) {
        return new RestUtils('basico/pdv/pdv');
    }
    
})();