var socket = io('127.0.0.1:10101');
socket.on('event', function(data){});

socket.on('connect', (data) => {
    socket.emit('hello', 'jul');
});

socket.on('re', (data) => {
    console.log(data);
});

//socket.on('disconnect', function(){});