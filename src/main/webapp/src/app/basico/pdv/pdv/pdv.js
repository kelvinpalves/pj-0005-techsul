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
        '$rootScope',
        '$scope',
        '$location'];

    function CtrlForm(
        dataservice, 
        $q, 
        $http, 
        multiPromise,
        $rootScope,
        $scope, 
        $location) {

        /* jshint validthis: true */
        var vm   = this;
        var $doc = angular.element(document);

        var KEY_ENTER = 13;

        var ATALHO_SAIR = 27;
        var ATALHO_MUDAR_DESCONTO = 68;
        var ATALHO_PRECO_ESPECIAL = 80;
        var ATALHO_MUDAR_QUANTIDADE_Q   = 81;
        var ATALHO_MUDAR_QUANTIDADE_AST = 56;
        var QUANTIDADE_DEFAULT = 1;

        vm.tipoDesconto = true; // true = percentual, false = valor

        vm.buscarPorCodigo              = buscarPorCodigo;
        vm.descontoProdutoPorValor      = descontoProdutoPorValor;
        vm.descontoProdutoPorPercentual = descontoProdutoPorPercentual;
        vm.evtChangePrecoOuQuantidade   = evtChangePrecoOuQuantidade;
        vm.evtKeyUpAdicionarNaLista     = evtKeyUpAdicionarNaLista;
        vm.evtKeyUpMudarTipoDesconto     = evtKeyUpMudarTipoDesconto;
        vm.itens                        = [];
        vm.valorTotalNota               = 0;
        vm.voltar                       = voltar;

        $doc.on("keyup", atalho);

        $scope.$on("$destroy", function () {
            $doc.off("keyup", atalho);
        });

        angular.element('#codigo').focus();

        vm.model = {
            quantidade: 1,
            preco: 0,
            desconto: 0,
            valorTotalProduto: 0
        };

        function atalho(e) {
            if (ATALHO_SAIR === e.keyCode) {
                voltar();
            } else if (ATALHO_PRECO_ESPECIAL === e.keyCode && e.altKey === true) {
                if (vm.model.precoEspecial) {
                    mudarPrecoEspecial();
                }
            }
        }

        function buscarPorCodigo(codigo, evt) {
            if (evt.keyCode === KEY_ENTER) {
                if (codigo) {
                    dataservice.buscarProduto(codigo).then(success).catch(error);
                }
            } else if (evt.keyCode === ATALHO_MUDAR_QUANTIDADE_Q || (evt.keyCode === ATALHO_MUDAR_QUANTIDADE_AST && evt.shiftKey)) {
                if (codigo.indexOf(".") == -1 && codigo.indexOf(",") == -1) {
                    vm.model.quantidade = parseInt(codigo.replace("q", "").replace("*", ""));
                } else {
                    if (codigo.indexOf(",") == -1) {
                        vm.model.quantidade = parseFloat(codigo.replace("q", "").replace("*", ""));
                    } else {
                        vm.model.quantidade = parseFloat(codigo.replace("q", "").replace("*", "").replace(",", "."));
                    }
                }

                delete vm.model.codigo;
            }

            function error(response) {
                console.log(response);
            }

            function success(response) {
                if (response.data.status == 'true') {
                    criarModel(response.data.data.ProdutoDto);
                    angular.element('#quantidade').focus();
                } else {
                    toastr.error(response.data.message[0].mensagem);
                }
            }
        }

        function calcularValorTotalNota() {
            angular.forEach(vm.itens, calcular);

            function calcular(value, index) {
                if (index === 0) {
                    vm.valorTotalNota = value.totalItem;
                } else {
                    vm.valorTotalNota += value.totalItem;
                }
            }
        }

        function criarModel(model) {
            vm.model = {};
            vm.model.id         = model.id;
            vm.model.descricao  = model.descricao;
            vm.model.codigo     = model.codigo;
            vm.model.preco      = model.precoVenda ? model.precoVenda : 0;
            vm.model.precoVenda = model.precoVenda;
            vm.model.quantidade = QUANTIDADE_DEFAULT;
            vm.model.desconto   = 0;

            if (model.precoEspecial) {
                vm.model.precoEspecial      = model.precoEspecial;
                vm.model.quantidadeEspecial = model.quantidadeEspecial;
            }

            evtChangePrecoOuQuantidade(vm.model);
        }

        function descontoProdutoPorValor() {
            return !vm.tipoDesconto;
        }

        function descontoProdutoPorPercentual() {
            return vm.tipoDesconto;
        }

        function evtChangePrecoOuQuantidade(model) {
            if (vm.model.precoEspecial) {
                if (vm.model.quantidade >= vm.model.quantidadeEspecial) {
                    vm.model.preco = vm.model.precoEspecial;
                } else {
                    vm.model.preco = vm.model.precoVenda;
                }
            }
            
            vm.model.valorTotalProduto = (model.quantidade ? model.quantidade : 0) * (model.preco ? model.preco : 0);

            if (vm.model.desconto && vm.model.desconto > 0) {
                if (vm.tipoDesconto) {
                    vm.model.valorTotalProduto = vm.model.valorTotalProduto - ((vm.model.valorTotalProduto * vm.model.desconto) / 100);
                } else {
                    vm.model.valorTotalProduto -= vm.model.desconto;
                }
            }
        }

        function evtKeyUpMudarTipoDesconto(evt) {
            if (ATALHO_MUDAR_DESCONTO === evt.keyCode) {
                if (vm.tipoDesconto) {
                    vm.tipoDesconto = false;
                } else {
                    vm.tipoDesconto = true;
                }
            }

            evtChangePrecoOuQuantidade(vm.model);
        }

        function evtKeyUpAdicionarNaLista(model, evt) {
            if (evt.keyCode === KEY_ENTER) {
                if (model.id) {
                    vm.itens.push({
                        id: model.id,
                        descricao: model.descricao,
                        quantidade: model.quantidade,
                        preco: model.valorTotalProduto / model.quantidade,
                        totalItem: model.valorTotalProduto
                    });

                    delete vm.model;

                    calcularValorTotalNota();
                    angular.element('#codigo').focus();
                } else {
                    toastr.error("Erro ao adicionar o item na lista.");
                }
            }
        }

        function mudarPrecoEspecial() {
            if (vm.model.precoEspecial) {
                vm.model.preco = vm.model.precoEspecial;
                evtChangePrecoOuQuantidade(vm.model);
            }
        }

        function voltar() {
            $location.path('/');
            $scope.$apply();
        }
    }

})(); 