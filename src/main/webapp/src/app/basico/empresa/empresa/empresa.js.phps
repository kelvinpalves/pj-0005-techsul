(function () {
	
	'use strict';

	angular
		.module('empresa.empresa')
		.controller('Empresa', CtrlForm);

	CtrlForm.$inject = [
		'empresaRest', 
		'$q', 
		'$http', 
		'multiPromise',
		'empresaCodigoRegimeTributarioUtils',
		'empresaEscritorioContabilUtils',
		'enderecoCidadeUtils']

	function CtrlForm(
		dataservice, 
		$q, 
		$http, 
		multiPromise,
		empresaCodigoRegimeTributarioUtils,
		empresaEscritorioContabilUtils,
		enderecoCidadeUtils) {

		/* jshint validthis: true */
		var vm = this;

		var CODIGO_REGIME_TRIBUTARIO = 0;
		var ESCRITORIO_CONTABIL      = 1;
		var CIDADE                   = 2;

		vm.anterior        = anterior;
		vm.autocomplete    = autocomplete;
		vm.carregarSiglaEstadoPorCidade = carregarSiglaEstadoPorCidade;
		vm.encontrouObjeto = encontrouObjeto;
		vm.modoEdicao      = false;
		vm.novo            = novo;
		vm.primeiro        = primeiro;
		vm.proximo         = proximo;
		vm.remover         = remover;
		vm.salvar          = salvar;
		vm.ultimo          = ultimo;

		init();

		function anterior() {
			if (vm.model.id) {
				dataservice.anterior(vm.model.id).then(success).catch(error);
			}

			function error(response) {
				console.log(response);
			}

			function success(response) {
				if (response.data.status == 'true') {
					vm.model = response.data.data.ProdutoGrupoDto;
					vm.auxiliar = response.data.data.ProdutoGrupoDto.descricao;
					vm.modoEdicao = true;
				}
			}
		}

		function autocomplete(auxiliar) {
			dataservice.autocomplete(auxiliar).then(success).catch(error);

			function error() {
				vm.produtoGrupoList = [];
			}

			function success(response) {
				if (response.data.status == 'true') {
					vm.produtoGrupoList = response.data.data.ArrayList;
				} else {
					vm.produtoGrupoList = [];
				}
			}
		}

		function encontrouObjeto() {
			if (vm.auxiliar.id) {
				dataservice.buscar(vm.auxiliar.id).then(success).catch(error);
			} else {
				vm.modoEdicao = false;
			}

			function error(response) {
				console.log(response);
			}

			function success(response) {
				vm.model = response.data.data.ProdutoGrupoDto;
				vm.modoEdicao = true;
			}
		}

		function init() {
			var promises = [];
			promises.push(empresaCodigoRegimeTributarioUtils.carregarCombo());
			promises.push(empresaEscritorioContabilUtils.carregarCombo());
			promises.push(enderecoCidadeUtils.carregarCombo());

			multiPromise.ready(promises).then(function(values) {
				if (values[CODIGO_REGIME_TRIBUTARIO].exec) {
					vm.crtList = values[CODIGO_REGIME_TRIBUTARIO].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de códigos do regime tributário.');
				}

				if (values[ESCRITORIO_CONTABIL].exec) {
					vm.escritorioList = values[ESCRITORIO_CONTABIL].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de escritório contábil.');
				}

				if (values[CIDADE].exec) {
					vm.cidadeList = values[CIDADE].objeto;
				} else {
					toastr.error('Erro ao carregar a lista de cidades.');
				}
			});
		}

		function novo(formulario) {
			delete vm.auxiliar;
			delete vm.model;
			vm.modoEdicao = false;
			formulario.$setPristine();
		}

		function primeiro() {
			dataservice.primeiro().then(success).catch(error);

			function error(response) {
				toastr.error('Ocorreu um erro ao carregar os dados.');
			}

			function success(response) {
				vm.model = response.data.data.ProdutoGrupoDto;
				vm.auxiliar = response.data.data.ProdutoGrupoDto.descricao;
				vm.modoEdicao = true;
			}
		}

		function proximo() {
			if (vm.model.id) {
				dataservice.proximo(vm.model.id).then(success).catch(error);
			}

			function error(response) {
				console.log(response);
			}

			function success(response) {
				if (response.data.status == 'true') {
					vm.model = response.data.data.ProdutoGrupoDto;
					vm.auxiliar = response.data.data.ProdutoGrupoDto.descricao;
					vm.modoEdicao = true;
				}
			}
		}

		function remover(formulario) {
			if (!confirm("Tem certeza que deseja remover o grupo?")) {
				return false;
			} else {
				dataservice.remover(vm.model.id).then(success).catch(error);
			}

			function error(response) {
				console.log(response);
				toastr.error('Ocorreu um erro ao remover o registro.');
			}

			function success(response) {
				if (response.data.status == 'true') {
					toastr.success(response.data.message[0].mensagem);
					novo(formulario);					
				} else {
					toastr.error(response.data.message[0].mensagem);
				}
			}
		}

		function salvar(formulario) {
			if (vm.modoEdicao) {
				vm.model.descricao = vm.auxiliar.descricao ? vm.auxiliar.descricao : vm.auxiliar;
				dataservice.atualizar(vm.model.id, vm.model).then(success).catch(error);
			} else {
				vm.model.descricao = vm.auxiliar.descricao ? vm.auxiliar.descricao : vm.auxiliar;
				dataservice.salvar(vm.model).then(success).catch(error);
			}

			function error(response) {
				console.log(response);
				toastr.error("Ocorreu um erro ao salvar.");
			}

			function success(response) {
				if (response.data.status == 'true') {
					toastr.success(response.data.message[0].mensagem);
					novo(formulario);					
				} else {
					toastr.error(response.data.message[0].mensagem);
				}
			}
		}

		function ultimo() {
			dataservice.ultimo().then(success).catch(error);

			function error(response) {
				console.log(response);
			}

			function success(response) {
				vm.model = response.data.data.ProdutoGrupoDto;
				vm.auxiliar = response.data.data.ProdutoGrupoDto.descricao;
				vm.modoEdicao = true;
			}
		}
	}

})(); 