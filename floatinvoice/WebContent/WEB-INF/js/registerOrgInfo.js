  // create angular app
        var validationApp = angular.module('registrationApp')
          /*.service('fiService', function(){
              this.getAcronym = function(){
              return "${acronym}";
         };
        })*/
         /*validationApp.config(function($mdThemingProvider) {
          $mdThemingProvider.theme('customTheme')
            .primaryPalette('grey')
            .accentPalette('pink');
        });*/
        // create angular controller
        validationApp.controller('RegistrationOrgInfoController', function($scope, $window, $http, $location) {
            $scope.user= {};
           /* $http.get('/floatinvoice/register/findCorpInfo?acro='+fiService.getAcronym()).success(function(data){
                $scope.products = data.list;
            });*/
            $scope.respMsg = "";
            $scope.states = ('Andhra Pradesh,Arunachal Pradesh,Assam,Bihar,Chhattisgarh,Goa,Gujarat,Haryana,Himachal Pradesh,Jammu and Kashmir,Jharkhand,Karnataka,Kerala,Madhya Pradesh,Maharashtra,Manipur,Meghalaya,Mizoram,Nagaland,Orissa,Punjab,Rajasthan,Sikkim,Tamil Nadu,Tripura,Uttarakhand,Uttar Pradesh,West Bengal,Andaman and Nicobar Islands,Chandigarh,Dadra and Nagar Haveli,Daman and Diu,Delhi,Lakshadweep,Pondicherry').split(',').map(function(state) {
            return {abbrev: state};
            });

            $scope.nextAction = function(){
                //bcsetSelectedTab(bctabs[1]);
                $location.path("/personalInfo");
                $scope.breadCrumbs();
            }
            $scope.checkRespMsg = function(){

                if($scope.respMsg.length > 1){
                    
                    return true;
                }else{
                    
                    return false;
                }   
            }

            $scope.orgBreadCrumbs = function(){
                console.log("orgBreadCrumbs");
                $scope.breadCrumbs();
            }

            // function to submit the form after all validation has occurred            
            $scope.submitForm = function() {
                // check to make sure the form is completely valid
                if ($scope.userForm.$valid) {
                    
                    $http({
                        method:'POST',
                        url:'/floatinvoice/register/corpInfo',
                        data:$scope.user,
                        xhrFields: {
                            withCredentials: true
                        },
                        headers:{'Content-Type':'application/json'
                                 }
                        }).then(function successCallback(response) {
                            //$window.location.replace('/floatinvoice/register/usrInfoPage');
                            console.log(response);
                            $scope.errRespMsg = "";
                            $scope.respMsg = "Message saved successfully."
                          }, function errorCallback(response) {
                            console.log(response);
                            $scope.errRespMsg = "Error saving data."
                            
                      });
                }
            };
        });