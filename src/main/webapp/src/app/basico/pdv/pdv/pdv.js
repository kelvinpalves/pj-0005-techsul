(function () {
    
    'use strict';

    angular
        .module('pdv.pdv')
        .controller('PDV', CtrlForm);

    CtrlForm.$inject = [
        'pdvRest', 
        '$q', 
        '$http', 
        'multiPromise',
        '$rootScope'];

    function CtrlForm(
        dataservice, 
        $q, 
        $http, 
        multiPromise,
        $rootScope) {

        /* jshint validthis: true */
        var vm = this;

        $rootScope.mostrarMenu = false;

    }

})(); 