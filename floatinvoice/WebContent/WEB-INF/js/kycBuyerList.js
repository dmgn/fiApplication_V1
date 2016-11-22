angular.module('floatInvoiceListApp')
  .controller('kycBuyerController', function($scope) {

  $scope.debtors = { list : [{compName: 'DTCC', city: 'Boston', country:'US'}, {compName: 'Omgeo', city: 'NY', country:'US'}]};
  
  $scope.addNewChoice = function() {
    var newItemNo = $scope.debtors.list.length+1;
    console.log($scope.debtors.list);
    $scope.debtors.list.push('');
  };
    
  $scope.removeChoice = function() {
    var lastItem = $scope.debtors.list.length-1;
    $scope.debtors.list.splice(lastItem);
  };
  
});