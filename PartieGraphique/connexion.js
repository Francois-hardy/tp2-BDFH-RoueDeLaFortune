var phrase;

var socket = io('127.0.0.1:10101');

socket.on('event', function(data){});

socket.on('connect', (data) => {
    socket.emit('demarrage', 'demarrage');
});

socket.on('phrase', (data) => {
    drawGame(data);
});

function drawGame(data) {
    var table = document.getElementById("jeu");
    table.innerHTML = "";

    let phrase = data.join("");
    console.log(phrase);

    var body = document.createElement("tbody");
    var tr1 = document.createElement("tr");
    var tr2 = document.createElement("tr");
    var tr3 = document.createElement("tr");
    var tr4 = document.createElement("tr");

    for (var i = 0; i < phrase.length; i++) {
        if (i < 14) {
            if (phrase.charAt(i) !== "!" && phrase.charAt(i) !== " ") {
                var td = document.createElement("td");
                td.innerHTML = phrase.charAt(i);
                td.setAttribute("id", "whitecell");
                tr1.appendChild(td);
            }
            else {
                var td = document.createElement("td");
                td.innerHTML = "";
                if (phrase.charAt(i) === "!") {
                    td.setAttribute("id", "emptycell");
                }
                tr1.appendChild(td);
            }
        }
        else if (i >= 14 && i < 28) {
            if (phrase.charAt(i) !== "!" && phrase.charAt(i) !== " ") {
                var td = document.createElement("td");
                td.innerHTML = phrase.charAt(i);
                td.setAttribute("id", "whitecell");
                tr2.appendChild(td);
            }
            else {
                var td = document.createElement("td");
                td.innerHTML = "";
                if (phrase.charAt(i) === "!") {
                    td.setAttribute("id", "emptycell");
                }
                tr2.appendChild(td);
            }
        }
        else if (i >= 28 && i < 42) {
            if (phrase.charAt(i) !== "!" && phrase.charAt(i) !== " ") {
                var td = document.createElement("td");
                td.innerHTML = phrase.charAt(i);
                td.setAttribute("id", "whitecell");
                tr3.appendChild(td);
            }
            else {
                var td = document.createElement("td");
                td.innerHTML = "";
                if (phrase.charAt(i) === "!") {
                    td.setAttribute("id", "emptycell");
                }
                tr3.appendChild(td);
            }
        }
        else {
            if (phrase.charAt(i) !== "!" && phrase.charAt(i) !== " ") {
                var td = document.createElement("td");
                td.innerHTML = phrase.charAt(i);
                td.setAttribute("id", "whitecell");
                tr4.appendChild(td);
            }
            else {
                var td = document.createElement("td");
                td.innerHTML = "";
                if (phrase.charAt(i) === "!") {
                    td.setAttribute("id", "emptycell");
                }
                tr4.appendChild(td);
            }
        }
    }

    body.appendChild(tr1);
    body.appendChild(tr2);
    body.appendChild(tr3);
    body.appendChild(tr4);
    table.appendChild(body);

    //wait in js

}

function buzzer() {
    socket.emit('action_buzz', 'action_buzz');
    var phrase_tentative = window.prompt("Entrer la phrase :");
    socket.emit('envoie_phrase_buzz', phrase_tentative);
}

//socket.on('disconnect', function(){});