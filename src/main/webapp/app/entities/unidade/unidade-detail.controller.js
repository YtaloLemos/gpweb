(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('UnidadeDetailController', UnidadeDetailController);

    UnidadeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Unidade'];

    function UnidadeDetailController($scope, $rootScope, $stateParams, entity, Unidade) {
        var vm = this;
        vm.unidade = entity;
        
        var unsubscribe = $rootScope.$on('gpwebApp:unidadeUpdate', function(event, result) {
            vm.unidade = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
