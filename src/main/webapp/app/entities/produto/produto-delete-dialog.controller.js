(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('ProdutoDeleteController',ProdutoDeleteController);

    ProdutoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Produto'];

    function ProdutoDeleteController($uibModalInstance, entity, Produto) {
        var vm = this;
        vm.produto = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Produto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
