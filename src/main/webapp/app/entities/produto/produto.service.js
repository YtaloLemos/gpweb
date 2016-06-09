(function() {
    'use strict';
    angular
        .module('gpwebApp')
        .factory('Produto', Produto);

    Produto.$inject = ['$resource'];

    function Produto ($resource) {
        var resourceUrl =  'api/produtos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
