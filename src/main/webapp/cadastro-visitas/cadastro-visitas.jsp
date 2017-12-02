<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Visita</title>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="cadastro-visitas/cadastro-visitas.css">

    </head>
    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>

        <div class="container-fluid">
            <div class="page-header">
                <h1>Cadastre Sua Visita</h1>
            </div>
            <div class="row">
                <form id="visita-form" action="ServletVisita" method="post" role="form">
                    <input type="hidden" name="ACTION" value="CADASTRAR"/>
                    <div class="form-group">
                        <div>
                            <input type="text" name="valNome" class="form-control"
                                    placeholder="Digite o Nome da Visita">
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <input type="text" name="valCpf" class="cpf form-control"
                                   placeholder="Digite o CPF">
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <input type="datetime-local" name="valData" class="form-control"
                                   placeholder="Digite a data da visita">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-6 col-md-offset-3">
                            <button type="submit" class="btn btn-lg btn-primary btn-block">Cadastrar Visita</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>  

        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Atenção</h4>
                    </div>
                    <div class="modal-body">
                        <p class="error">${errorMessage}</p>
                        <p class="success">${successMessage}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>        
        <script src="cadastro-visitas/cadastro-visitas.js"></script>
    </body>
</html>
