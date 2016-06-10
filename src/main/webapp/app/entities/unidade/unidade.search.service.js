(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .factory('UnidadeSearch', UnidadeSearch);

    UnidadeSearch.$inject = ['$resource'];

    function UnidadeSearch($resource) {
        var resourceUrl =  'api/_search/unidades/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
