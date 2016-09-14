angular.module('finfloatInvoiceListApp')
     .controller('FinApprovedLoanCtrl', ['$scope', '$http','acroNameService', function ($scope, $http, acroNameService){
     	var acro = acroNameService.getAcronym();  
	    $http.get('/floatinvoice/bank/sanctionedLoans?acro='+acro).success(function(data){
		$scope.loans = data.list;
        $scope.sortField = 'loanId';
	    });
     
		$scope.refresh = function(refId){
			var index = -1;
			var loanList =  $scope.loans;
			for (var i=0; i<loanList.length; i++){
				if( loanList[i].refId == refId ) {
						index = i;
						break;
				}
			}
			$scope.loans.splice(index, 1);
		};	
	 	$scope.disburseLoan = function(loanRefId) {
	 		$scope.refId = loanRefId;
	        $http.put('/floatinvoice/bank/disburseLoan?loanRefId='+loanRefId).success(function(){
				$scope.refresh($scope.refId);
	        }
				
		    );
		    $scope.refId = "";
	      }

	    $scope.downLoadAgreement = function(loanRefId){
	    	return "/floatinvoice/view/agreement?refId="+loanRefId+"&acro="+acro+"&type=pdf";
	    }


      }]);

 