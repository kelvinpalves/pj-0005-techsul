(function () {
	
	'use strict';

	angular
		.module('produto.produto')
		.controller('Produto', CtrlForm);

	CtrlForm.$inject = [
		'produtoRest',
		'produtoGrupoUtils',
		'multiPromise',
		'produtoUnidadeUtils',
		'produtoCestUtils',
		'produtoOrigemUtils',
		'produtoCsosnUtils']

	function CtrlForm(
		dataservice, 
		produtoGrupoUtils,
		multiPromise,
		produtoUnidadeUtils,
		produtoCestUtils,
		produtoOrigemUtils,
		produtoCsosnUtils) {

		/* jshint validthis: true */
		var vm = this;

		var PRODUTO_GRUPO   = 0;
		var PRODUTO_UNIDADE = 1;
		var PRODUTO_CEST    = 2;
		var PRODUTO_ORIGEM  = 3;
		var PRODUTO_CSOSN   = 4;

		var zerado = {
			precoCusto: 0,
			precoVenda: 0,
			precoEspecial: 0,
			lucro: 0,
			quantidadePrecoEspecial: 0
		}

		vm.evtChangeLucro      = evtChangeLucro;
		vm.evtChangePrecoCusto = evtChangePrecoCusto;
		vm.evtChangePrecoVenda = evtChangePrecoVenda;
		vm.model               = zerado;
		vm.salvar              = salvar;
		vm.setCsosn            = setCsosn;
		vm.setGrupo            = setGrupo;
		vm.setNCM              = setNCM;
		vm.setOrigem           = setOrigem;
		vm.setUnidade          = setUnidade;

		init();

		function evtChangeLucro() {
			if (vm.model.lucro && vm.model.lucro > 0) {
				vm.model.precoVenda = vm.model.precoCusto + (vm.model.lucro * vm.model.precoCusto);
			} else {
				vm.model.precoVenda = vm.model.precoCusto;
			}
		}

		function evtChangePrecoCusto() {
			if (vm.model.lucro && vm.model.lucro > 0) {
				vm.model.precoVenda = vm.model.precoCusto + (vm.model.lucro * vm.model.precoCusto);
			} else {
				vm.model.precoVenda = vm.model.precoCusto;
			}
		}

		function evtChangePrecoVenda() {
			if (vm.model.precoVenda > vm.model.precoCusto) {
				vm.model.lucro = (((vm.model.precoVenda - vm.model.precoCusto)) * 100 / vm.model.precoCusto) / 100;
			} else {
				vm.model.lucro = 0;
			}
		}

		function init() {
			var promises = [];

			promises.push(produtoGrupoUtils.carregarCombo());
			promises.push(produtoUnidadeUtils.carregarCombo());
			promises.push(produtoCestUtils.carregarCombo());
			promises.push(produtoOrigemUtils.carregarCombo());
			promises.push(produtoCsosnUtils.carregarCombo());

			multiPromise.ready(promises).then(function(values) {
				if (values[PRODUTO_GRUPO].exec) {
					vm.grupoList = values[PRODUTO_GRUPO].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de grupos.');
				}

				if (values[PRODUTO_UNIDADE].exec) {
					vm.unidadeList = values[PRODUTO_UNIDADE].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de unidades.');
				}

				if (values[PRODUTO_CEST].exec) {
					vm.ncmList = values[PRODUTO_CEST].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de NCM/CEST.');
				}

				if (values[PRODUTO_ORIGEM].exec) {
					vm.origemMercadoriaList = values[PRODUTO_ORIGEM].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de origens da mercadoria.');
				}

				if (values[PRODUTO_CSOSN].exec) {
					vm.csosnList = values[PRODUTO_CSOSN].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de CSOSN.');
				}
			});
		}

		function salvar() {
			console.log(vm.model);
		}

		function setCsosn(objeto) {
			if (objeto == null) {
				delete vm.model.csosn;
			} else {
				vm.model.csosn = objeto.id;
			}
		}

		function setGrupo(objeto) {
			if (objeto == null) {
				delete vm.model.grupo;
			} else {
				vm.model.grupo = objeto.id;
				vm.model.lucro = objeto.dados.lucro == null ? vm.model.lucro : objeto.dados.lucro;

				if (objeto.dados.lucro != null) {
					if (vm.model.precoCusto != 0 && vm.model.precoCusto) {
						vm.model.precoVenda = vm.model.precoCusto + (objeto.dados.lucro * vm.model.precoCusto);
					}
				}
			}
		}

		function setNCM(objeto) {
			if (objeto == null) {
				delete vm.model.cest;
				delete vm.cest;
			} else {
				vm.model.cest = objeto.id;
				vm.cest = objeto.dados.cest + ' - ' + objeto.dados.cestDescricao;
			}
		}

		function setOrigem(objeto) {
			if (objeto == null) {
				delete vm.model.origem;
			} else {
				vm.model.origem = objeto.id;
			}
		}

		function setUnidade(objeto) {
			if (objeto == null) {
				delete vm.model.unidade;
			} else {
				vm.model.unidade = objeto.id;
			}
		}
	}

})(); 