function deletaVisita(element, id) {
    $.post("ServletVisita", {ACTION: 'DELETAR', visitaId: id}, function(response) {
        var convertedResponse = JSON.parse(response);
        if(convertedResponse.Success) {
            element.parentElement.parentElement.parentElement.removeChild(element.parentElement.parentElement);
        } else {
            alert("Ocorreu um problema ao deletar a visita");
        }
    });
}

function atualizaVisita(element, id) {
    $.post("ServletVisita", {ACTION: 'ATUALIZAR', visitaId: id}, function(response) {
        var convertedResponse = JSON.parse(response);
        if(convertedResponse.Success) {
            element.parentElement.parentElement.parentElement.removeChild(element.parentElement.parentElement);
        } else {
            alert("Ocorreu um problema ao atualizar a visita");
        }
    });
}