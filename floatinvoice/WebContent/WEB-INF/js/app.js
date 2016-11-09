var floatInvoiceListApp = angular.module('floatInvoiceListApp',
  ['angularUtils.directives.dirPagination','ngRoute', 'route-segment', 'view-segment', 'angularModalService',
  'ngMaterial', 'ngMessages','kendo.directives']);
floatInvoiceListApp.config(function ($locationProvider, $routeProvider, $routeSegmentProvider) {
    $routeSegmentProvider.
    when('/homePg','s1').
    when('/reports','s3').
    when('/loans','s2').
    when('/profile','s4').
    when('/applications','s5').
    when('/s1/upload','s1.upload').
    when('/s1/unpaid','s1.unpaid').
    when('/s1/pending','s1.pending').
    when('/s1/funded','s1.funded').
    when('/s1/offers','s1.offers').
    when('/s1/rejected','s1.rejected').
    when('/s1/delinquent','s1.delinquent').
    when('/s4/dashbd','s4.dashbd').
    when('/s4/compInfo','s4.compInfo').
    when('/s4/directorInfo','s4.directorInfo');
    //when('/s4/funded','s4.funded');

    $routeSegmentProvider
    .segment('s1', {
        templateUrl: '/floatinvoice/html/homePage.html',
        controller: 'JustifiedTabsCtrl'
    });

    $routeSegmentProvider
    .segment('s2', {
        templateUrl: '/floatinvoice/html/payment.html',
        controller:'PaymentCtrl'
    });

    $routeSegmentProvider
    .segment('s3', {
        templateUrl: '/floatinvoice/html/paid.html',
        controller: 'JustifiedTabsCtrl'
    });

    $routeSegmentProvider
    .segment('s4', {
        templateUrl: '/floatinvoice/html/profile.html',
        controller: 'ProfileTabsCtrl'
    });
    
    $routeSegmentProvider
    .segment('s5', {
        templateUrl: '/floatinvoice/html/kycApplications.html',
        controller: 'KYCApplicationCtrl'
    });
/*   $routeSegmentProvider
      .within('s1')
      .segment('upload', {
        templateUrl:'/floatinvoice/html/upload.html',
        controller:'UploadCtrl'
    });
*/

   $routeSegmentProvider
      .within('s1')
      .segment('upload', {
        templateUrl:'/floatinvoice/html/uploadNew.html',
        controller:'NewUploadController'
    });

    $routeSegmentProvider
      .within('s1')
      .segment('unpaid', {
        templateUrl:'/floatinvoice/html/unpaid.html',
        controller:'UnpaidCtrl'
    });  
     
    $routeSegmentProvider
      .within('s1')
      .segment('pending', {
        templateUrl:'/floatinvoice/html/pending.html',
        controller:'PendingCtrl'
    });

    $routeSegmentProvider
      .within('s1')
      .segment('funded', {
        templateUrl:'/floatinvoice/html/funded.html',
        controller:'FundedCtrl'
    });

    $routeSegmentProvider
      .within('s1')
      .segment('offers', {
        templateUrl:'/floatinvoice/html/offers.html',
        controller:'OffersCtrl'
    });

    $routeSegmentProvider
      .within('s1')
      .segment('rejected', {
        templateUrl:'/floatinvoice/html/rejectedInvoices.html',
        controller:'RejectedInvoicesCtrl'
    });

    $routeSegmentProvider
      .within('s1')
      .segment('delinquent', {
        templateUrl:'/floatinvoice/html/delinquent.html',
        controller:'DelinquentCtrl'
    });      

    $routeSegmentProvider
      .within('s4')
      .segment('dashbd', {
        templateUrl:'/floatinvoice/html/borrowerDashboard.html',
        controller:'PaidCtrl'
    });

    $routeSegmentProvider
      .within('s4')
      .segment('compInfo', {
        templateUrl:'/floatinvoice/html/companyInfo.html',
        controller:'PaidCtrl'
    });
    
    $routeSegmentProvider
      .within('s4')
      .segment('directorInfo', {
        templateUrl:'/floatinvoice/html/directorInfo.html',
        controller:'PaidCtrl'
    });  
});


/* var floatInvoiceListApp = angular.module('floatInvoiceListApp',
  ['angularUtils.directives.dirPagination','ngRoute', 'angularModalService',
  'ngMaterial', 'ngMessages']);

 /*       floatInvoiceListApp.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('default')
            .primaryPalette('light-blue')
            .accentPalette('green');
        });
 
    floatInvoiceListApp.config( function($locationProvider, $routeProvider){
      $routeProvider.when('/upload',{
        templateUrl:'/floatinvoice/html/upload.html',
        controller:'UploadCtrl'
      })
      .when('/unpaid',{
        templateUrl:'/floatinvoice/html/unpaid.html',
        controller:'UnpaidCtrl'
      })
      .when('/pending',{
        templateUrl:'/floatinvoice/html/pending.html',
        controller:'PendingCtrl'
      })
      .when('/funded',{
        templateUrl:'/floatinvoice/html/funded.html',
        controller:'FundedCtrl'
      })
      .when('/paid',{
        templateUrl:'/floatinvoice/html/paid.html',
        controller:'PaidCtrl'
      })
      .when('/homePg',{
        templateUrl:'/floatinvoice/html/homePage.html',
        controller:'JustifiedTabsCtrl'
      })
    


    });*/