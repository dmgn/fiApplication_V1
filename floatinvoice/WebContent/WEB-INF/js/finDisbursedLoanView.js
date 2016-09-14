
  angular.module('finfloatInvoiceListApp')
     .controller('LoanDisbursedViewCtrl', ['$scope', '$http','acroNameService', function ($scope, $http, acroNameService){
     	var acro = acroNameService.getAcronym();  
	    $http.get('/floatinvoice/bank/viewAllLoans?acro='+acro).success(function(data){
		$scope.invoices = data.list;
        $scope.sortField = 'loanId';
	    });
     }]);

 