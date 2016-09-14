 angular.module('finfloatInvoiceListApp')
 .controller('FinJustifiedTabsCtrl', ['$scope', '$location', 
      function ($scope, $location) {

      $scope.tabs = [
          { link : '#/t1/list', label : 'Listed' },
          { link : '#/t1/offers', label: 'Offered'},
          { link : '#/t1/acceptedOffers', label: 'Accepted'},
          { link : '#/t1/approve', label: 'Approved'},
          { link : '#/t1/disburse', label : 'Disbursed' },
          { link : '#/t1/repaid', label : 'Repaid' }
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