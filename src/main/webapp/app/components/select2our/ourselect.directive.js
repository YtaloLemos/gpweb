(function() {
    'use strict';

    angular
        .module('gpwebApp')
        .directive('ourselect', ourselect);

    function ourselect () {
        var directive = {
            restrict: 'E',            
            require: '?ngModel',  
            scope: {
            	entityFieldName: '@', //name of field name for each entity: nmGrupo, nmFabricante...
                idSelect: '@',        //id of the select component that need to be initialized
                entityUrl: '@'        //url will be used for each component
            },
            link: linkFunc
        };

        return directive;

        function linkFunc (scope, element, attrs, ngModel) {
        	       
        	ngModel.$render = function(){
        		
        		//Return the string representation for the entity searched
        		function ret(){                 	
                	if(ngModel.$modelValue){                 		
                		return ngModel.$modelValue.id + ' - ' + ngModel.$modelValue[scope.entityFieldName];
                	}
                }
        		
        		//Setting the result of the select on scope
        		var settingValueToScope = function(valueRepo){        		
             		if(valueRepo.id && valueRepo[scope.entityFieldName]){
             			var obj = {id: valueRepo.id};
             			obj[scope.entityFieldName] = valueRepo[scope.entityFieldName];
             			ngModel.$setViewValue(obj);            			
             		}
             	}
        		
        		//Used by component select2 to formatter the results inside of the select
        		function formatRepo (repo) {            	
                    if (repo.loading) return repo.text;
                	var markup = "<div class='select2-result-repository clearfix'>" +
                				 "<div class='select2-result-repository__statistics'>" +
    	            			 "<div class='select2-result-repository__forks'><i class='fa fa-flash'></i> " + repo.id + " - " + repo[scope.entityFieldName] + "</div>" +
    	                  		 "</div>";
                				 "</div>";
                    return markup;
                }
                
        		//Used by component select2 to formatter the results inside of the select
                function formatRepoSelection (repo) {
                	settingValueToScope(repo); //applying the result on model                 	
                	if(repo){            	
    	            	if(repo.id && repo[scope.entityFieldName]){	            
    	            		return repo.id + ' - ' + repo[scope.entityFieldName];    	            
    	            	}
                	}
                }
                
                //Initializing the component select2
                if(scope.idSelect !== undefined){                	
	                $('#' + scope.idSelect).select2({
	              	  placeholder: 'asdf', //placeholder won't rendered but is necessary for to clear correctly
	                  // allowClear: true, //Don't use this cause not work with ngModel
	              	  closeOnSelect: true,
	              	  cache: false,
	              	  theme: "classic",
	              	 minimumResultsForSearch: -1,
	              	  ajax: {
	              		url: "/api/_search/" + scope.entityUrl + "/select?size=20&sort=id,asc",
	              	    dataType: 'json',
	              	    delay: 20,            	    
	              	    data: function (params) {
	              	      return {
	              	        query: params.term.toUpperCase(), 
	              	        page: params.page
	              	      };
	              	    },              	  
	              	    processResults: function (data, params) {
	              	      // parse the results into the format expected by Select2
	              	      // since we are using custom formatting functions we do not need to
	              	      // alter the remote JSON data, except to indicate that infinite
	              	      // scrolling can be used
	              	      params.page = params.page || 0;
	
	              	      return {
	              	        results: data.items,
	              	        pagination: {
	              	          //if has more result to show then return true else return false 	
	              	          more: (data.total_count > 20)?((params.page * 20) < data.total_count):false
	              	        }
	              	      };
	              	    },
	              	  },
	              	  escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
	              	  minimumInputLength: 1,
	              	  templateResult: formatRepo, // omitted for brevity, see the source of this page
	              	  templateSelection: formatRepoSelection // omitted for brevity, see the source of this page            	  
	              })
                }
        	}; 
        }
    }
})();
