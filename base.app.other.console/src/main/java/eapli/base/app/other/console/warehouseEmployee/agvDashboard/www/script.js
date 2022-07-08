function refreshAGV() {
    const request = new XMLHttpRequest();
    const warehouseBoard = document.getElementById("grid-container-warehouse");
    const agvBoard = document.getElementById("agv-container");
    const timer = document.getElementById("timer");
    const alert = document.getElementById("alert");

    request.onload = function () {
        alert.innerHTML = '';
        warehouseBoard.insertAdjacentHTML('beforeend', agvWarehouseFormatter(this.responseText));
        agvBoard.innerHTML = agvBoardFormatter(this.responseText);
        timer.innerHTML = 'Last update: ' + formatDate(new Date());
        setTimeout(refreshAGV, 2000);
    };

    request.ontimeout = function () {
        alert.innerHTML = `<div class="alert-text">Server timeout, still trying ...</div>`;
        setTimeout(refreshAGV, 100);
    };

    request.onerror = function () {
        alert.innerHTML = `<div class="alert-text">No server reply, still trying ...</div>`;
        setTimeout(refreshAGV, 5000);
    };

    request.open("GET", "https://localhost:8443/", true);
    request.timeout = 10000;
    request.send();
}

function agvWarehouseFormatter (response) {
    const agvList = document.getElementsByClassName("agv");

    for(var i = agvList.length - 1; 0 <= i; i--)
        if(agvList[i] && agvList[i].parentElement)
            agvList[i].parentElement.removeChild(agvList[i]);

    var json = JSON.parse(response);
    var string = "";

    for (var i = 0; i < json.AGVList.length; i++) {
        var agv = json.AGVList[i];
        
        string = string.concat(`<div class="agv" id="agv" style="grid-column: ${agv.currentPosition.coordinates.lSquare}; grid-row: ${agv.currentPosition.coordinates.wSquare};"> <span class="agv-span">AGV${padTo2Digits(agv.id.id)}</span> </div>`);
    }

    return string;
}

function agvBoardFormatter (response) {
    var json = JSON.parse(response);
    var string = "";

    for (var i = 0; i < json.AGVList.length; i++) {
        var agv = json.AGVList[i];
        string = string.concat(`<div class="agv-data"><h2>AGV${padTo2Digits(agv.id.id)}</h2><hr><p><b>Status: </b>${agv.status}</p></div>`);
    }

    return string;
}

function padTo2Digits(num) {
    return num.toString().padStart(2, '0');
}

function formatDate(date) {
    var horas = [
        padTo2Digits(date.getHours()),
        padTo2Digits(date.getMinutes()),
        padTo2Digits(date.getSeconds())
    ].join(":");

    var data = [
        padTo2Digits(date.getDate()),
        padTo2Digits(date.getMonth() + 1),
        date.getFullYear(),
    ].join('/');

    return horas + " - " + data;
  }