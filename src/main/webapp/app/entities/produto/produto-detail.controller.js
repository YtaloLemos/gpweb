(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .controller('ProdutoDetailController', ProdutoDetailController);

    ProdutoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Produto', 'Grupo', 'Marca', 'Unidade'];

    function ProdutoDetailController($scope, $rootScope, $stateParams, entity, Produto, Grupo, Marca, Unidade) {
        var vm = this;
        vm.produto = entity;
        
        var unsubscribe = $rootScope.$on('gpwebApp:produtoUpdate', function(event, result) {
            vm.produto = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
