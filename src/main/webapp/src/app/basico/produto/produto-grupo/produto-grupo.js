(function () {
	
	'use strict';

	angular
		.module('produto.produto-grupo')
		.controller('ProdutoGrupo', CtrlForm);

	CtrlForm.$inject = ['produtoGrupoRest', '$q', '$http']

	function CtrlForm(dataservice, $q, $http) {
		/* jshint validthis: true */
		var vm = this;

		vm.autocomplete    = autocomplete;
		vm.encontrouObjeto = encontrouObjeto;
		vm.novo = novo;
		vm.modoEdicao = false;
		vm.salvar = salvar;
		vm.remover = remover;
		vm.primeiro = primeiro;
		vm.proximo = proximo;
		vm.anterior = anterior;
		vm.ultimo = ultimo;

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

		function primeiro() {
			dataservice.primeiro().then(success).catch(error);

			function error(response) {
				console.log(response);
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

		function novo(formulario) {
			delete vm.auxiliar;
			delete vm.model;
			vm.modoEdicao = false;
			formulario.$setPristine();
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
	}

})(); 