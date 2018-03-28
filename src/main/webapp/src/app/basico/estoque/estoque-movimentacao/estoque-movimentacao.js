(function () {
    
    'use strict';

    angular
        .module('estoque.estoque-movimentacao')
        .controller('EstoqueMovimentacao', CtrlForm);

    CtrlForm.$inject = [
        'estoqueMovimentacaoRest',
        'multiPromise',
        '$location',
        'estoqueMovimentacaoTipoUtils',
        'produtoUtils',
        'estoqueUtils',
        'estoqueMovimentacaoUtils']

    function CtrlForm(
        dataservice, 
        multiPromise,
        $location,
        estoqueMovimentacaoTipoUtils, 
        produtoUtils,
        estoqueUtils,
        estoqueMovimentacaoUtils) {

        /* jshint validthis: true */
        var vm = this;

        var ESTOQUE_MOVIMENTACAO_TIPO = 0;

        var PROD_ESTOQUE     = 0;
        var PROD_MVT_ESTOQUE = 1;

        vm.autocomplete = autocomplete;
        vm.setProduto = setProduto;
        vm.voltar = voltar;

        init();

        function autocomplete(auxiliar) {
            return produtoUtils.autocomplete(auxiliar).then(success).catch(error);

            function error(response) {
                return [];
            }

            function success(response) {
                return response.exec ? response.objeto : [];
            }
        }

        function init() {
            var promises = [];

            setarObjetoInicial();

            promises.push(estoqueMovimentacaoTipoUtils.carregarCombo());

            multiPromise.ready(promises).then(function(values) {
                if (values[ESTOQUE_MOVIMENTACAO_TIPO].exec) {
                    vm.tipoMovimentacaoList = values[ESTOQUE_MOVIMENTACAO_TIPO].objeto;
                } else {
                    toastr.error('Erro ao carregar a lista de tipo de movimentação.');
                }
            });

        }

        function setarObjetoInicial(codigo) {
            vm.model = {};
            vm.model.precoCusto         = 0;
            vm.model.precoVenda         = 0;
            vm.model.quantidade         = 0;
            vm.model.dataMovimentacao   = new Date();
            delete vm.produto;
        }

        function setProduto(objeto) {
            var promises = [];

            if (objeto === null) {
                delete vm.produtoDescricao;
                delete vm.produtoEstoque;
                delete vm.ultimasMovimentacoes;
            } else {
                if (vm.produto.id) {
                    promises.push(estoqueUtils.carregarPorProduto(vm.produto.id));
                    promises.push(estoqueMovimentacaoUtils.buscarUltimasMovimentacoesPorProduto(vm.produto.id));

                    multiPromise.ready(promises).then(function(values) {
                        if (values[PROD_ESTOQUE].exec) {
                            vm.produtoDescricao  = values[PROD_ESTOQUE].objeto.produto;
                            vm.produtoEstoque = values[PROD_ESTOQUE].objeto.quantidade;
                        } else {
                            delete vm.produtoDescricao;
                            delete vm.produtoEstoque;
                        }

                        if (values[PROD_MVT_ESTOQUE].exec) {
                            vm.ultimasMovimentacoes = values[PROD_MVT_ESTOQUE].objeto;
                        } else {
                            delete vm.ultimasMovimentacoes;
                        }

                    });
                }
            }
        }

        function voltar() {
            $location.path('/');
        }
    }

})(); 