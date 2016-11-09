/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var output = document.getElementById("output");
var category = document.getElementById("category").value;
var websocket = null;

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

function sendText(txt) {
    console.log("sending text: " + txt);
    websocket.send(txt);
}

function selection_change(){
    var wsUri = "";
    var category = document.getElementById("category").value;
    
    if (websocket != null)
        websocket.close();
    
    if (category != "SELECT"){
        wsUri = "ws://" + document.location.host + document.location.pathname + "wbendpoint/" + category;
        websocket = new WebSocket(wsUri);
    }
    else{
        return;
    }
    
    
    websocket.onerror = function(evt) { 
        writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
    };

    websocket.onopen = function(evt) { 
        writeToScreen("Connected to " + wsUri);
    };

    websocket.onmessage = function(evt) { 
        output.innerHTML += evt.data + "<br>";
    };
}
                