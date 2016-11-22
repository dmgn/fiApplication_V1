 angular.module('floatInvoiceListApp')
 .controller('KYCAppRouterCtrl', ['$scope', '$location', 
      function ($scope, $location) {
      $scope.tabs = [
          { link : '#/s5/kyc', label : 'KYC' },
          { link : '#/s5/buyerList', label : 'Buyer Details' },
          { link : '#/s5/acctReceivable', label : 'Accounts Receivables' },
          { link : '#/s5/docs', label : 'Documents' },
         
        ];
      var index = -1;
      var tabList =  $scope.tabs;
      for (var i=0; i<tabList.length; i++){
        if( tabList[i].link == '#'+$location.url() ) {
            index = i;
            break;
        }
      }
      $scope.selectedTab = $scope.tabs[index];
      $scope.setSelectedTab = function(tab, alignment) {
        $scope.selectedTab = tab;
      }
      $scope.tabClass = function(tab, alignment) {
        if ($scope.selectedTab == tab) {
          return "active";
        } else {
          return "";
        }
      }
    }]);