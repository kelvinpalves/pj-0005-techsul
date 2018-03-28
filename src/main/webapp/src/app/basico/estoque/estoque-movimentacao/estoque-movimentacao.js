(function () {
    
    'use strict';

    angular
        .module('estoque.estoque-movimentacao')
        .controller('EstoqueMovimentacao', CtrlForm);

    CtrlForm.$inject = [
        'estoqueMovimentacaoRest',
        'multiPromise',
        '$location']

    function CtrlForm(
        dataservice, 
        multiPromise,
        $location) {

        /* jshint validthis: true */
        var vm = this;

        function voltar() {
            $location.path('/');
        }
    }

})(); 