angular.module('floatInvoiceListApp')
  .run(function(editableOptions) {
    editableOptions.theme = 'bs3';
  })
  .controller('kycBuyerController', function($scope) {

  $scope.debtors = { list : [{compName: 'DTCC', city: 'Boston', country:'US'}, {compName: 'Omgeo', city: 'NY', country:'US'}]};
 // $scope.debtors = {list : []};
  $scope.addNewChoice = function() {
    var newItemNo = $scope.debtors.list.length+1;
    console.log($scope.debtors.list);
    $scope.debtors.list.push('');
  };
    
  $scope.removeChoice = function(index) {
    console.log(index);
     console.log($scope.debtors.list);
    //var lastItem = $scope.debtors.list.length-1;
    $scope.debtors.list.splice(index);
  };

   $scope.saveUser = function(data, index) {
    var debtorsList =  $scope.debtors.list;
    for (var i=0; i<debtorsList.length; i++){    
      if (i == index){
        $scope.debtors.list.splice(i, 1);
        break;
      }
    }
    $scope.debtors.list.push(data);

  };
  
});