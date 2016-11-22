
  angular.module('floatInvoiceListApp')
  	 .controller('kycBuyerController', ['$scope', '$http', '$routeParams', 'fiService', function ($scope, $http, $routeParams, fiService){}])
  	 .controller('kycAcctReceivableController', ['$scope', '$http', '$routeParams', 'fiService', function ($scope, $http, $routeParams, fiService){}])
  	 .controller('kycDocmentsController', ['$scope', '$http', '$routeParams', 'fiService', function ($scope, $http, $routeParams, fiService){}])
     .controller('KYCApplicationCtrl', ['$scope', '$http', '$routeParams', 'fiService', function ($scope, $http, $routeParams, fiService){
     	var acro = fiService.getAcronym();  
     	$scope.user={};
     	//$scope.user.regCompName='TESTACRO';
	    /*$http.get('/floatinvoice/viewKYCApp?refId=F881C61808764D9087653B0D5AA0B0E8&acro=DTCC').success(function(data){
		    console.log(data);
			$scope.application = {};
			$scope.application.compInfo.regCompName = data.regCompName;
			$scope.application.compInfo.website = data.regCompName;
			$scope.application.compInfo.contactPersonName = data.contactPersonName;
			$scope.application.compInfo.contactPersonContactNo = data.contactPersonContactNo;
			$scope.application.compInfo.contactPersonEmail = data.contactPersonEmail;
			$scope.application.compInfo.regOffice.street = data.regOffice.street;
			$scope.application.compInfo.regOffice.city = data.regOffice.city;
			//$scope.application.compInfo.regOffice.country 
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

 