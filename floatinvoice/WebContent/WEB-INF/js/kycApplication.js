
  angular.module('floatInvoiceListApp')
     .controller('KYCApplicationCtrl', ['$scope', '$http','fiService', function ($scope, $http, fiService){
     	var acro = fiService.getAcronym();  
     	$scope.user={};
     	$scope.user.regCompName='TESTACRO';
	  /*  $http.get('/floatinvoice/invoice/bids/offer?acro='+acro).success(function(data){
	    console.log(data.list);
		$scope.invoices = data.list;
        $scope.sortField = 'loanId';
	    });*/
        /*$scope.application = {};
        $scope.application.compInfo.regCompName = 'TESTACRO';
        $scope.application.compInfo.website = 'TESTACRO';
		$scope.application.compInfo.directorName = 'TESTACRO';
		$scope.application.compInfo.directorContactNo = 'TESTACRO';
		$scope.application.compInfo.directorEmailAddress = 'TESTACRO';
		$scope.application.compInfo.contactPersonName = 'TESTACRO';
		$scope.application.compInfo.contactPersonContactNo;
		$scope.application.compInfo.contactPersonEmail;
		$scope.application.compInfo.regOffice.street;
		$scope.application.compInfo.regOffice.city;
		$scope.application.compInfo.regOffice.country;
		$scope.application.compInfo.corpOffice.street;
		$scope.application.compInfo.corpOffice.city;
		$scope.application.compInfo.corpOffice.country;
		$scope.application.compInfo.tradeLicense.licenseNo;
		$scope.application.compInfo.tradeLicense.licensingAuthority;
		$scope.application.compInfo.tradeLicense.incorporationDt;
		$scope.application.compInfo.tradeLicense.licenseExpiryDt;
		$scope.application.compInfo.tradeLicense.tradeActivities;

		$scope.application.compInfo.corpInfo.legalStatus;
		$scope.application.compInfo.corpInfo.shareCapital;
		$scope.application.compInfo.corpInfo.shareHoldingStruct;
		$scope.application.compInfo.corpInfo.dirList;
		$scope.application.compInfo.corpInfo.keyBankers;
		$scope.application.compInfo.corpInfo.statutoryAuditors;
		$scope.application.compInfo.corpInfo.employeeStrength;
		$scope.application.compInfo.corpInfo.branchesWarehouses;
		$scope.application.compInfo.corpInfo.parentAffiliatedComps;
		
		$scope.application.compInfo.bankDtls.bankName;
		$scope.application.compInfo.bankDtls.bankAcctNo;
		$scope.application.compInfo.bankDtls.bankAddr;
		$scope.application.compInfo.bankDtls.swift;
		$scope.application.compInfo.bankDtls.routing;*/
		
		
		
     }]);

 