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
        '$location',
        '$window'];

    function CtrlForm(
        dataservice, 
        $q, 
        $http, 
        multiPromise,
        $rootScope,
        $scope, 
        $location,
        $window) {

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

        var larguraTela = $window.screen.width - 50;
        var alturaTela = $window.screen.height;
        
        vm.larguraBotaoPdv = larguraTela / 7;
        
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

        var BTN_ASTERISCO = 56; // Com shift Ativo
        var BTN_D         = 68;
        var BTN_E         = 69;
        var BTN_F4        = 115;
        var BTN_F7        = 118;
        var BTN_F9        = 120;
        var BTN_H         = 72;
        var BTN_K         = 75;
        var BTN_L         = 76;
        var BTN_M         = 77;
        var BTN_MENOS     = 189;
        var BTN_Q         = 81;
        var BTN_R         = 82;
        var BTN_T         = 84;
        var BTN_V         = 86;
        
        function atalho(e) {
            if (ATALHO_SAIR === e.keyCode) {
                voltar();
            } else if (ATALHO_PRECO_ESPECIAL === e.keyCode && e.altKey === true) {
                if (vm.model.precoEspecial) {
                    mudarPrecoEspecial();
                }
            } else if (BTN_F4 === e.keyCode) {
                console.log('Consultar Produto.');
            } else if (BTN_F9 === e.keyCode) {
                console.log('Cadastrar Produto');
            } else if (BTN_Q === e.keyCode || (BTN_ASTERISCO === e.keyCode && e.shiftKey)) {
                quantidade(e);
            } else if (BTN_E === e.keyCode || BTN_MENOS === e.keyCode) {
                preco(e);
            } else if (BTN_V === e.keyCode) {
                total();
            } else if (BTN_H === e.keyCode) {
                console.log('Observação');
            } else if (BTN_D === e.keyCode) {
                tipoDesconto();
            } else if (BTN_M === e.keyCode) {
                desconto();
            } else if (BTN_K === e.keyCode) {
                console.log('Cancelar Venda');
            } else if (BTN_T === e.keyCode) {
                console.log('Totalizar Venda');
            } else if (BTN_F7 === e.keyCode) {
                console.log('Pessoa');
            } else if (BTN_L === e.keyCode) {
                console.log('Informar Cliente');
            } else if (BTN_R === e.keyCode) {
                console.log('Reimprime')
            }
        }

        var QUANTIDADE     = 1;
        var DESCONTO       = 2;
        var PRECO_UNITARIO = 3;
        var VALOR_TOTAL    = 4;

        function recalcularValor(tipo) {
            switch (tipo) {
                case QUANTIDADE: {
                    console.log('quantidade');
                } break;
                case DESCONTO: {
                    console.log('desconto');
                } break;
                case PRECO_UNITARIO: {
                    console.log('preço');
                } break;
                case VALOR_TOTAL: {
                    console.log('valor');
                } break;
            }
        }

        function tipoDesconto() {
            vm.tipoDesconto = !vm.tipoDesconto;

            if (vm.model.codigo) {
                vm.model.codigo = vm.model.codigo.replace("d", "");
                vm.model.codigo = vm.model.codigo.replace("D", "");
            }
            
            $scope.$apply();
        }

        function desconto(codigo) {
            if (vm.model.codigo) {
                vm.model.codigo = vm.model.codigo.replace("m", "");
                vm.model.codigo = vm.model.codigo.replace("M", "");
            }
            
            setarDesconto(vm.model.codigo);
        }

        function setarDesconto(codigo) {
            if (codigo) {
                vm.model.desconto = parseInt(codigo);
            }

            delete vm.model.codigo;
            $scope.$apply();    
            recalcularValor(DESCONTO);
        }

        function quantidade(e) {
            if (BTN_Q === e.keyCode) {
                if (vm.model.codigo) {
                    vm.model.codigo = vm.model.codigo.replace("q", "");
                    vm.model.codigo = vm.model.codigo.replace("Q", "");
                }
            } else {
                if (vm.model.codigo) {
                    vm.model.codigo = vm.model.codigo.replace("*", ""); 
                }
            }

            setarQuantidade(vm.model.codigo);
        }

        function setarQuantidade(codigo) {
            if (codigo) {
                if (codigo.indexOf(".") == -1 && codigo.indexOf(",") == -1) {
                    vm.model.quantidade = parseInt(codigo);
                } else {
                    vm.model.quantidade = (codigo.indexOf(",") == -1) ? parseFloat(codigo) : parseFloat(codigo.replace(",", "."));
                }
            }    

            delete vm.model.codigo;
            $scope.$apply();    
            recalcularValor(QUANTIDADE);

        }

        function preco(e) {
            if (BTN_E === e.keyCode) {
                if (vm.model.codigo) {
                    vm.model.codigo = vm.model.codigo.replace("e", "");
                    vm.model.codigo = vm.model.codigo.replace("E", "");
                }
            } else {
                if (vm.model.codigo) {
                    vm.model.codigo = vm.model.codigo.replace("-", ""); 
                }
            }

            setarPreco(vm.model.codigo);
        }

        function setarPreco(codigo) {
            if (codigo) {
                vm.model.preco = (codigo.indexOf(",") == -1) ? parseFloat(codigo) : parseFloat(codigo.replace(",", "."));
            }    

            delete vm.model.codigo;
            $scope.$apply();    
            recalcularValor(PRECO_UNITARIO);
        }

        function total() {
            if (vm.model.codigo) {
                vm.model.codigo = vm.model.codigo.replace("v", "");
                vm.model.codigo = vm.model.codigo.replace("V", "");
            }
            
            setarTotal(vm.model.codigo);
        }

        function setarTotal(codigo) {
            if (codigo) {
                vm.model.valorTotalProduto = (codigo.indexOf(",") == -1) ? parseFloat(codigo) : parseFloat(codigo.replace(",", "."));
            }    

            delete vm.model.codigo;
            $scope.$apply();    
            recalcularValor(VALOR_TOTAL);
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