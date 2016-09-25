 // create angular app
  var registrationApp = angular.module('registrationApp', ['ngRoute', 'route-segment', 'view-segment', 
  'ngMaterial', 'ngMessages','kendo.directives', 'angularModalService', 'ngAnimate']);
      /*  registrationApp.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('default')
            .primaryPalette('light-blue')
            .accentPalette('green');
        });*/

   registrationApp.service('regUserEmail', function(){
      this.getRegUserEmail = function(){
        return "${acronym}";
      };
    });

registrationApp.config(function ($locationProvider, $routeProvider, $routeSegmentProvider) {
    $routeSegmentProvider.
      when('/signInInfo','s1').
      when('/companyInfo','s2').
      when('/personalInfo','s3').
     // when('/createApp','s4').
      when('/documents','s4');
   
    $routeSegmentProvider
      .segment('s1', {
          templateUrl: '/floatinvoice/register/signInPage',
          controller: 'RegistrationSignInController'
    });

    $routeSegmentProvider
      .segment('s2', {
          templateUrl: '/floatinvoice/register/orgInfoPage',
          controller: 'RegistrationOrgInfoController'
    });

    $routeSegmentProvider
      .segment('s3', {
          templateUrl: '/floatinvoice/register/usrInfoPage',
          controller: 'RegistrationUsrInfoController'
    });

/* $routeSegmentProvider
      .segment('s4', {
          templateUrl: '/floatinvoice/register/chooseProductPage',
          controller: 'RegistrationProductController'
    });*/

    $routeSegmentProvider
      .segment('s4', {
          templateUrl: '/floatinvoice/register/docs',
          controller: 'RegistrationDocsController'
    });

 });

/*registrationApp.service('fiService', function(){
  this.getAcronym = function(){
    return "${acronym}";
  };
});*/

        // create angular controller
registrationApp.controller('RegistrationController', ['$scope', '$location', 'regUserEmail', function($scope, $location, regUserEmail) {

      $scope.bctabs = [
        { link : '#/signInInfo', label : 'Change Password' },
        { link : '#/companyInfo', label : 'Company Info' },
        { link : '#/personalInfo', label : 'Director Info' },
       // { link : '#/createApp', label : 'Choose Product' },
        { link : '#/documents', label : 'Documents' },
        
      ];
      $scope.bcselectedTab = $scope.bctabs[0];   

      var index = -1;
      var bctabList =  $scope.bctabs;
      for (var i=0; i<bctabList.length; i++){
        if( bctabList[i].link == '#'+$location.url() ) {
            index = i;
            break;
        }
      }

      $scope.bcselectedTab = $scope.bctabs[index];

      $scope.bcsetSelectedTab = function(tab) {
        $scope.bcselectedTab = tab;
      }
      $scope.bctabClass = function(tab) {
        if ($scope.bcselectedTab == tab) {
          return "active";
        } else {
          return "";
        }
      }

      $scope.getSelectedTab = function(){
          return $scope.bcselectedTab;
      }

      $scope.breadCrumbs = function(){
          var index = -1;
          var bctabList =  $scope.bctabs;
          for (var i=0; i<bctabList.length; i++){
            if( bctabList[i].link == '#'+$location.url() ) {
                index = i;
                break;
            }
          }
          $scope.bcselectedTab = $scope.bctabs[index];
      }

}]);