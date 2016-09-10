floatInvoiceListApp
 .controller('OffersCtrl', ['$scope', '$http', '$routeParams', 'fiService',
 			function($scope, $http, $routeParams, fiService){
	    var acro = fiService.getAcronym();	
        $http.get('/floatinvoice/invoice/viewOffers?acro='+acro).success(function(data){
        $scope.invoices = data.list;
      });
}]);