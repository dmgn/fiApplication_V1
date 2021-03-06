 angular.module('floatInvoiceListApp')
 .controller('PendingCtrl', ['$scope', '$window', '$http', '$routeParams', 'fiService', 'ModalService',
 	function($scope, $window, $http, $routeParams, fiService, ModalService){
 		var acro = fiService.getAcronym();			
		$http.get('/floatinvoice/invoice/pending?acro='+acro)
		 .success(function(data){
    			$scope.invoices = data.list;
    			$scope.sortField = 'amount';
   				 console.log(data);
    			});


	$scope.displayInvoicePoolDtls = function( poolRefId ){
		  ModalService.showModal({
	      templateUrl: "html/invoicePoolDtls.html",
	      controller: "ModalInvoicePoolController",
	      inputs: {
	        input:poolRefId
	      }
		  }).then(function(modal) {
		   modal.element.modal();
		   modal.close.then(function(result) {
		        //$scope.complexResult  = "Name: " + result.name + ", age: " + result.age;
		  });
	    });
 	}
}]);