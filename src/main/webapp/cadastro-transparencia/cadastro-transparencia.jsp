<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Adicionar Despesa</title>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="cadastro-transparencia/cadastro-transparencia.css">
    </head>
    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        
        <div class="container-fluid">
            <div class="page-header">
                <h1>Cadastrar Transparência</h1>
            </div>
            
            <div class="row">
                <form id="form-transparencia" action="ServletTransparencia" method="post" role="form">
                    <input type="hidden" name="ACTION" value="CADASTRAR"/>
                    <div class="form-group">
                        <textarea rows="6" name ='valDescricao' class="form-control" placeholder="Dê detalhes sobre o gasto condominial"></textarea>
                    </div>
                    
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">R$</span>
                            <input type="text" name="valValor" class="valor form-control" placeholder="Digite o valor desejado">
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="date" name="valData" class="form-control" placeholder="Digite a data relativa ao gasto">
                    </div>
                    
                    <div class="form-group">
                        <div class="col-sm-6 col-md-offset-3">
                            <button type="submit" class="btn btn-lg btn-primary btn-block">Adicionar Transparência</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        
        <div class="modal fade" id="myModal" role="dialog" id="form-transparencia">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Atenção</h4>
                    </div>
                    <div class="modal-body">
                        <p class="error">${errorMessage}</p>
                        <p class="success">${sucessMessage}</p>
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
        <script src="common/js/jquery.maskMoney.min.js"></script>
        <script src="cadastro-transparencia/cadastro-transparencia.js"></script>
    </body>
</html>
