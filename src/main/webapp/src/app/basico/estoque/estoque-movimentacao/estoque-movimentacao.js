(function () {
    
    'use strict';

    angular
        .module('estoque.estoque-movimentacao')
        .controller('EstoqueMovimentacao', CtrlForm);

    CtrlForm.$inject = [
        'estoqueMovimentacaoRest',
        'multiPromise',
        '$location',
        'estoqueMovimentacaoTipoUtils']

    function CtrlForm(
        dataservice, 
        multiPromise,
        $location,
        estoqueMovimentacaoTipoUtils) {

        /* jshint validthis: true */
        var vm = this;

        var ESTOQUE_MOVIMENTACAO_TIPO = 0;

        vm.voltar = voltar;

        init();

        function init() {
            var promises = [];

            promises.push(estoqueMovimentacaoTipoUtils.carregarCombo());

            multiPromise.ready(promises).then(function(values) {
                if (values[ESTOQUE_MOVIMENTACAO_TIPO].exec) {
                    vm.tipoMovimentacaoList = values[ESTOQUE_MOVIMENTACAO_TIPO].objeto;
                } else {
                    toastr.error('Erro ao carregar a lista de tipo de movimentação.');
                }
            });

        }

        function voltar() {
            $location.path('/');
        }
    }

})(); 