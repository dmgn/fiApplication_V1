 angular.module('adminfloatInvoiceListApp')
 .controller('ChatController', [
  '$http','$scope', '$element', 'input', 'close', 
  function($http, $scope, $element, input, close) {


  $scope.cancel = function() {    
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
        alert("You can't send mmsg to your self.");
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
 .controller('ManageEnquiryCtrl', ['$scope', '$location', 'ModalService',
      function ($scope, $location, ModalService) {

      $scope.adtabs = [
          { link : '#/e1/list', label : 'New' },
          { link : '#/e1/pending', label: 'Qualified'},
          { link : '#/e1/staged', label : 'Staged' },
          { link : '#/e1/released', label : 'Released' }
        ];  


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
/*     var index = -1;
      var adtabList =  $scope.adtabs;
      for (var i=0; i<adtabList.length; i++){
        if( adtabList[i].link == '#'+$location.url() ) {
            index = i;
            break;
        }
      }
      $scope.adselectedTab = $scope.adtabs[index];
      $scope.adsetSelectedTab = function(tab, alignment) {
        $scope.adselectedTab = tab;
      }
      $scope.adtabClass = function(tab, alignment) {
        if ($scope.adselectedTab == tab) {
          return "active";
        } else {
          return "";
        }
      }*/
    }]);