(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('UnidadeDialogController', UnidadeDialogController);

    UnidadeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Unidade'];

    function UnidadeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Unidade) {
        var vm = this;
        vm.unidade = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('gpwebApp:unidadeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.unidade.id !== null) {
                Unidade.update(vm.unidade, onSaveSuccess, onSaveError);
            } else {
                Unidade.save(vm.unidade, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
