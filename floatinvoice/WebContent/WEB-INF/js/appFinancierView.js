 var finfloatInvoiceListApp = angular.module('finfloatInvoiceListApp',
  ['angularUtils.directives.dirPagination','ngRoute', 'route-segment', 'view-segment', 'angularModalService',
  'ngMaterial', 'ngMessages', 'kendo.directives']);

 /*       floatInvoiceListApp.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('default')
            .primaryPalette('light-blue')
            .accentPalette('green');
        });*/
 
    finfloatInvoiceListApp.config(
      function($locationProvider, $routeProvider, $routeSegmentProvider){
      $routeSegmentProvider.
        when('/findashboard', 't0').
        when('/finTxPg','t1').
        when('/t1/list','t1.list').
        when('/t1/offers','t1.offers').
        when('/t1/acceptedOffers','t1.acceptedOffers').
        when('/t1/approve','t1.approve').  
        //when('/t1/sanctioned','t1.sanctioned').       
        when('/t1/disburse','t1.disburse').        
        when('/t1/repaid','t1.repaid').  
        when('/finResearch', 't2').

       /* when('/finprofile','t2').*/
        when('/t2/prospects','t2.prospects').        
        when('/t2/profiles','t2.profiles').        
        when('/t2/industryTrends','t2.industryTrends').        
        when('/t2/loanPerformance','t2.loanPerformance').

        when('/t2/profiles/summary','t2.profiles.summary').
        when('/t2/profiles/directors','t2.profiles.directors').
        when('/t2/profiles/creditHist','t2.profiles.creditHist').
/*        when('/t2/profiles/suppDocs','t2.profiles.suppDocs').
*/        when('/t2/profiles/metrics','t2.profiles.metrics').
        when('/t2/profiles/cashflow','t2.profiles.cashflow');



      $routeSegmentProvider
      .segment('t0', {
        templateUrl: '/floatinvoice/html/financierDashbdPg.html',
        controller: 'FinDashboardCtrl'
      });

      $routeSegmentProvider
      .segment('t1', {
        templateUrl: '/floatinvoice/html/financierTxPg.html',
        controller: 'FinJustifiedTabsCtrl'
      });

    $routeSegmentProvider
      .within('t1')
      .segment('list', {
        templateUrl:'/floatinvoice/html/finInvoiceListView.html',
        controller:'InvoiceFinViewCtrl'
    });

    $routeSegmentProvider
      .within('t1')
      .segment('offers', {
        templateUrl:'/floatinvoice/html/finOffersView.html',
        controller:'FinOffersViewCtrl'
    });

    $routeSegmentProvider
      .within('t1')
      .segment('acceptedOffers', {
        templateUrl:'/floatinvoice/html/finAcceptedOffersView.html',
        controller:'FinAcceptedOffersViewCtrl'
    });

   $routeSegmentProvider
      .within('t1')
      .segment('approve', {
        templateUrl:'/floatinvoice/html/finApprovedLoan.html',
        controller:'FinApprovedLoanCtrl'
    });

    $routeSegmentProvider
      .within('t1')
      .segment('disburse', {
        templateUrl:'/floatinvoice/html/finLoanDisbursedView.html',
        controller:'LoanDisbursedViewCtrl'
    });

    $routeSegmentProvider
      .within('t1')
      .segment('repaid', {
        templateUrl:'/floatinvoice/html/finRePaidInvoiceView.html',
        controller:'InvoiceFinViewCtrl'
    });    

    $routeSegmentProvider
      .segment('t2', {
        templateUrl: '/floatinvoice/html/financierResearchPg.html',
        controller: 'FinResearchCtrl'
      });


    $routeSegmentProvider
      .within('t2')
      .segment('prospects', {
        templateUrl:'/floatinvoice/html/financierProspectPg.html'
    });
       
    $routeSegmentProvider
      .within('t2')
      .segment('profiles', {
        templateUrl:'/floatinvoice/html/financierProfilePg.html',
        controller:'FinProfileTabsCtrl'
    });

    $routeSegmentProvider
      .within('t2')
      .within('profiles')
      .segment('summary', {
        templateUrl:'/floatinvoice/html/companyInfo.html'
    });
    $routeSegmentProvider
      .within('t2')
      .within('profiles')
      .segment('directors', {
        templateUrl:'/floatinvoice/html/directorInfo.html',
        controller:'DirectorCtrl'
    });
    $routeSegmentProvider
      .within('t2')
      .within('profiles')
      .segment('creditHist', {
        templateUrl:'/floatinvoice/html/creditHistoryInfo.html'
    });
/*    $routeSegmentProvider
      .within('t2')
      .within('profiles')
      .segment('suppDocs', {
        templateUrl:'/floatinvoice/html/FinDocView.html'
    }); */     
    $routeSegmentProvider
      .within('t2')
      .within('profiles')
      .segment('metrics', {
        templateUrl:'/floatinvoice/html/FinMetrics.html'
    });
   $routeSegmentProvider
      .within('t2')
      .within('profiles')
      .segment('cashflow', {
        templateUrl:'/floatinvoice/html/FinCashflow.html'
    });

   $routeSegmentProvider
      .within('t2')
      .segment('industryTrends', {
         templateUrl:'/floatinvoice/html/customers.html',
        
    }); 
   $routeSegmentProvider
      .within('t2')
      .segment('loanPerformance', {
         templateUrl:'/floatinvoice/html/customers.html',
        
    }); 
      
    });