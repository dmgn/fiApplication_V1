 angular.module('chatApp',['angularUtils.directives.dirPagination','ngRoute', 'route-segment', 'view-segment', 'angularModalService',
  'ngMaterial', 'ngMessages'])
.controller('ChatController', [
  '$http','$scope', '$element', 'input', 'close', 'NbfcAcroNameService',
  function($http, $scope, $element, input, close, NbfcAcroNameService) {
    console.log("input " +input);

    $scope.cancel = function() {   
      
      $element.modal('hide');
    };

  }]);