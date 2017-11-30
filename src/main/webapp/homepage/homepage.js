$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
});

function deletaInformativo(element, id) {
    $.get("ServletInformativo", { deleteId: id }, function(response) {
        var convertedResponse = JSON.parse(response);
        if(convertedResponse.Success) {
            element.parentElement.parentElement.removeChild(element.parentElement);
            $(".tooltip").hide();
        } else {
            alert("Ocorreu um problema ao deletar o informativo");
        }
    });
}


