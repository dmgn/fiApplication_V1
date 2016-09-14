
  angular.module('finfloatInvoiceListApp')
     .controller('FinOffersViewCtrl', ['$scope', '$http','acroNameService', function ($scope, $http, acroNameService){
     	var acro = acroNameService.getAcronym();  
	    $http.get('/floatinvoice/invoice/bids/offer?acro='+acro).success(function(data){
	    console.log(data.list);
		$scope.invoices = data.list;
        $scope.sortField = 'loanId';
	    });
     }]);

 