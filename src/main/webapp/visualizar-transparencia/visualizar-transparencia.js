function deletaTransparencia(element, id) {
    $.get("ServletTransparencia", {transparenciaId: id}, function(response) {
        var convertedResponse = JSON.parse(response);
        if(convertedResponse.Success) {
            element.parentElement.parentElement.parentElement.removeChild(element.parentElement.parentElement);
        } else {
            alert("Ocorreu um problema ao deletar a transparencia");
        }
    });
}
