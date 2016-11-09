 angular.module('nbfcApp')
.controller('ChatController', [
  '$http','$scope', '$element', 'input', 'close', 'NbfcAcroNameService',
  function($http, $scope, $element, input, close, NbfcAcroNameService) {
    $scope.cancel = function() {
      //  Manually hide the modal.
      $element.modal('hide');
    };
    var user_name=window.prompt('Enter Your Name'); 
    var socket = io("http://54.210.238.169:7000");
    $scope.clicked=null;
    $scope.selected_id=null;
    $scope.msgs=null;
    $scope.my_id=null;
    $scope.is_msg_show=false;
  socket.emit('user name',user_name); // sending user name to the server

  socket.on('user entrance',function(data, my_id){
      //checking the user id
      if($scope.my_id==null){
          $scope.my_id=my_id;
      }
      $scope.user_show=data;
    $scope.$apply(); 
  });   

  //function to send messages.
  $scope.send_msg = function($event){
      var keyCode = $event.which || $event.keyCode;
      if($scope.selected_id==$scope.my_id){
        alert("You can't send msg to your self.");
      }else{
        if (keyCode === 13) { 
          var data_server={
            id:$scope.selected_id,
            msg:$scope.msg_text,
            name:user_name
          };
          $scope.msg_text='';
          $('#messages').append($('<li>').text(data_server.name + " : " + data_server.msg));
          socket.emit('send msg',data_server);
          }
      }     
  };

  //to highlight selected row
  $scope.clicked_highlight = function(id){
    $scope.clicked=id;
    $scope.selected_id=id;
  };
  
  //on exit updating the List od users
  socket.on('exit',function(data){
    $scope.user_show=data;
      $scope.$apply(); 
  });

  //displaying the messages.
  socket.on('get msg',function(data){
    console.log(data.msg);
    $scope.msgs=data;
    $scope.is_msg_show=true;
    $scope.$apply(); 
    $('#messages').append($('<li>').text(data.name + " : " + data.msg));
  });



  }])

.controller('NbfcEnquiryCtrl', [ '$scope', '$window', '$http', 'NbfcAcroNameService', 'ModalService', function($scope, $window, $http, NbfcAcroNameService,
  ModalService) {
 $scope.selectedoption = "";

 $http.get('/floatinvoice/enquiries?acro='+NbfcAcroNameService.getAcronym())
       .success(function(data){
            $scope.enquiryList = data.list;
       });


 /* $scope.nextAction = function(){
  	$window.location.replace('/floatinvoice/prospectsDocs?refId='+$scope.selectedoption);
  }*/

  $scope.optionSelected = function(){  	
  	return $scope.selectedoption == "" ? true : false;
  }


  $scope.displayFiles = function(){
      
      console.log($scope.selectedoption);
      ModalService.showModal({
      templateUrl: "html/supportDocModal.html",
      controller: "ModalController",
      inputs: {
        input:$scope.selectedoption
      }
    }).then(function(modal) {
      console.log(modal);
      modal.element.modal();
      modal.close.then(function(result) {
        //$scope.complexResult  = "Name: " + result.name + ", age: " + result.age;
      });
    });
  }

  $scope.displayChatWindow = function(){
      ModalService.showModal({
      templateUrl: "html/chatModal.html",
      controller: "ChatController",
      inputs: {
        input:"Hello"
      }
    }).then(function(modal) {
      console.log(modal);
      modal.element.modal();
      modal.close.then(function(result) {
        //$scope.complexResult  = "Name: " + result.name + ", age: " + result.age;
      });
    });
  }


}]);