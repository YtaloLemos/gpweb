(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('ProdutoDialogController', ProdutoDialogController);

    ProdutoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Produto', 'Grupo', 'Marca', 'Unidade'];

    function ProdutoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Produto, Grupo, Marca, Unidade) {
        var vm = this;
        vm.produto = entity;
        vm.grupos = Grupo.query({filter: 'produto-is-null'});
        $q.all([vm.produto.$promise, vm.grupos.$promise]).then(function() {
            if (!vm.produto.grupo || !vm.produto.grupo.id) {
                return $q.reject();
            }
            return Grupo.get({id : vm.produto.grupo.id}).$promise;
        }).then(function(grupo) {
            vm.grupos.push(grupo);
        });
        vm.marcas = Marca.query({filter: 'produto-is-null'});
        $q.all([vm.produto.$promise, vm.marcas.$promise]).then(function() {
            if (!vm.produto.marca || !vm.produto.marca.id) {
                return $q.reject();
            }
            return Marca.get({id : vm.produto.marca.id}).$promise;
        }).then(function(marca) {
            vm.marcas.push(marca);
        });
        vm.unidades = Unidade.query({filter: 'produto-is-null'});
        $q.all([vm.produto.$promise, vm.unidades.$promise]).then(function() {
            if (!vm.produto.unidade || !vm.produto.unidade.id) {
                return $q.reject();
            }
            return Unidade.get({id : vm.produto.unidade.id}).$promise;
        }).then(function(unidade) {
            vm.unidades.push(unidade);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('gpwebApp:produtoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.produto.id !== null) {
                Produto.update(vm.produto, onSaveSuccess, onSaveError);
            } else {
                Produto.save(vm.produto, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
