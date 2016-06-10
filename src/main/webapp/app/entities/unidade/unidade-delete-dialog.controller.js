(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('UnidadeDeleteController',UnidadeDeleteController);

    UnidadeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Unidade'];

    function UnidadeDeleteController($uibModalInstance, entity, Unidade) {
        var vm = this;
        vm.unidade = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Unidade.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
