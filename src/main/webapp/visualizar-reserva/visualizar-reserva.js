function rejeitarReserva(element, id) {
    element.style.display = "none";
    $.post("ServletReserva", {ACTION: 'REJEITAR', visitaId: id}, function(response) {
        var convertedResponse = JSON.parse(response);
        if(convertedResponse.Success) {
            element.parentElement.parentElement.parentElement.removeChild(element.parentElement.parentElement);
        } else {
            element.style.display = "block";
            alert("Ocorreu um problema ao rejeitar a reserva");
        }
    });
}

function aprovarReserva(element, id) {
    element.style.display = "none";
    $.post("ServletReserva", {ACTION: 'APROVAR', visitaId: id}, function(response) {
        var convertedResponse = JSON.parse(response);
        if(convertedResponse.Success === "TRUE") {
            element.parentElement.parentElement.parentElement.removeChild(element.parentElement.parentElement);
        } else {
            element.style.display = "block";
            alert("Ocorreu um problema ao aprovar a reserva");
        }
    });
}