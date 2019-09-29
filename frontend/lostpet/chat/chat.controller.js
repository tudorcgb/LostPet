(function () {
    'use strict';

    angular
        .module('app')
        .controller('ChatController', ChatController);

    var app = angular.module('app');

    var selectedFile;
    var fileName;

    var usernamePage = document.querySelector('#username-page');
    var chatPage = document.querySelector('#chat-page');
    var usernameForm = document.querySelector('#usernameForm');
    var messageForm = document.querySelector('#messageForm');
    var messageInput = document.querySelector('#message');
    var messageArea = document.querySelector('#messageArea');
    var connectingElement = document.querySelector('.connecting');

    var stompClient = null;
    var username = null;
    var destUser = null;
    var idCurrentUser = null;
    var chatName= null;
    var selectedChatId =null;


    ChatController.$inject = ['$scope' ,'$http','ChatService','$rootScope','$location','$routeParams','CommentsService','UserService','ChatCommentService'];
    function ChatController($scope,$http,ChatService,$rootScope,$location,$routeParams,CommentsService,UserService,ChatCommentService) {
        $scope.selectedChatTrue = false;
        // $(function () {
        //     var socket = io();
        //     $('form').submit(function(e){
        //         e.preventDefault(); // prevents page reloading
        //         socket.emit('chat message', $('#m').val());
        //         $('#m').val('');
        //         return false;
        //     });
        // });
        // var socket = new SockJS('/ws');
        // stompClient = Stomp.over(socket);

        //stompClient.connect({}, onConnected, onError);
        //connect();
        getChatsForUser();
        // function connect(event) {
        //    // username = document.querySelector('#name').value.trim();
        //
        //     var socket = new SockJS(baseAPI + '/chat/ws');
        //     stompClient = Stomp.over(socket);
        //
        //     stompClient.connect({}, onConnected, onError);
        //
        //     event.preventDefault();
        // }
        //todo
        //destUser = "user2";
        username = $rootScope.globals.currentUser.username;
        idCurrentUser = $rootScope.globals.currentUser.id;

        //var chatName = username + destUser;
        $scope.addComment = function(){


        };

        function onConnected(res) {
            console.log("succes connected");
            res.success = true;
            return res;
        }

        function onError(error) {
            console.log("error not connected");
            return function () {
                return { success: false, message: error };
            };
        }



        function setConnected(connected) {
            $("#connect").prop("disabled", connected);
            $("#disconnect").prop("disabled", !connected);
            if (connected) {
                $("#conversation").show();
            }
            else {
                $("#conversation").hide();
            }
            //$("#chat_box").html("");
        }

        function connect() {
            var socket = new SockJS(baseAPI + '/chat-websocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/user_chat/' + chatName, function (chatMessage) {
                    console.log(chatMessage.body);
                    chatMessage = JSON.parse(chatMessage.body);
                    var messageElem = {};
                    messageElem.message = chatMessage.content;
                    var d = new Date(chatMessage.date);
                    messageElem.date = d.toLocaleTimeString() + " " + d.toLocaleDateString() ;
                    messageElem.username = chatMessage.senderUsername;

                    showMessage(messageElem);

                });

            });
        }


        function updateScroll(){
            var chatBox = document.getElementById("conversation");
            chatBox.scrollIntoView(false);
            console.log("scroll to bottom");
        }

        function disconnect() {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }

        //sends message on enter key pressed and clears textarea
        function submitOnEnter(event){
            if(event.which === 13 && !event.shiftKey){
                event.target.form.dispatchEvent(new Event("submit", {cancelable: true}));
                event.preventDefault(); // Prevents the addition of a new line in the text field (not needed in a lot of cases)
                $scope.sendMessage();
                event.target.value = "";
            }
        }

        document.getElementById("message_textarea").addEventListener("keypress", submitOnEnter);


        function showMessage(messageWithDate) {
        if(messageWithDate.username === username) {
            $("#chat_box").append("<tr align=\"right\" bgcolor=\"#c2f9c6\"><td><b> " + messageWithDate.username + "</b> a spus: " + messageWithDate.message + "<br> " + messageWithDate.date + " </td></tr>");
        }else{
            $("#chat_box").append("<tr bgcolor=\"#3fdb49\"><td><b> " + messageWithDate.username + "</b> a spus: " + messageWithDate.message + "<br> " + messageWithDate.date + " </td></tr>");
        }
            updateScroll();
        }

        $(function () {
            $("form").on('submit', function (e) {
                e.preventDefault();
            });
            //$( "#connect" ).click(function() { connect(); });
            $( "#disconnect" ).click(function() { disconnect(); });
            $( "#send" ).click(function() { sendName(); });
        });
        $scope.sendMessage  = function() {
            if($scope.newCommentMessage !== "") {
                var d = new Date();
                console.log($scope.newCommentMessage);
                var chatMessage = {
                    senderUsername: username,
                    dest: $scope.otherUser.username,
                    content: $scope.newCommentMessage,
                    type: 'CHAT',
                    date: d.getTime(),
                    idChat: selectedChatId

                };

                console.log("send message triggered");
                console.log(chatMessage);
                stompClient.send("/lost_pet_chat/user_chat/" + chatName, {}, JSON.stringify(chatMessage));
                $scope.newCommentMessage = "";
            }
        };

        function getChatsForUser(){
            ChatService.GetByChatsForUser($rootScope.globals.currentUser.id)
                .then(function (data) {
                    $scope.chats = data.data;
                    console.log( $scope.chats);
                    $scope.chats.forEach(function(element){
                        if(element.sender.id  === idCurrentUser){
                            element.otherUser = element.dest;
                        }else{
                            element.otherUser = element.sender;
                        }
                    });
                });
        }

        $scope.chatSelected = function (chat) {
            $scope.selectedChatTrue = true;
            ChatCommentService.GetByChatCommentsById(chat.id)
                .then(function (data) {
                    $("#chat_box").empty();
                    chatName = chat.chatName;
                    selectedChatId = chat.id;

                    //$scope.chatComments.otherUser = {}
                    $scope.otherUser = chat.otherUser;
                    console.log(chat.otherUser);
                    $scope.chatComments = data.data;
                    console.log( $scope.chatComments);
                    $scope.chatComments.forEach(function(element) {

                        var messageElem = {};
                        messageElem.message = element.message;
                        var d = new Date(element.date);
                        messageElem.date = d.toLocaleTimeString() + " " + d.toLocaleDateString() ;
                        messageElem.username = element.senderUsername;
                        console.log( messageElem);
                        showMessage(messageElem);

                    });
                    connect();
                    // $scope.chats.forEach(function(element){
                    //     if(element.sender.id  === idCurrentUser){
                    //         element.otherUser = element.dest;
                    //     }else{
                    //         element.otherUser = element.sender;
                    //     }
                    // });
                });

        }

    }
})();




