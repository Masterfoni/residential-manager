<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transparência</title>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="visualizar-transparencia/visualizar-transparencia.css">
    </head>
    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        <div class="container-fluid">
            <div class="page-header">
                <h1>Visualize as Finanças</h1>
            </div> 
            <c:if test="${empty transpList}">
                <div class="jumbotron">
                    <h2>Não há Transparências Adicionadas até o Momento.</h2> 
                </div>
            </c:if>
            <c:forEach items="${transpList}" var="transparencia">
                <div class="main-transparencia" data-toggle="tooltip" data-placement="right"
                     title="Remover Transparencia"> 
                    <c:if test="${sessionScope.userType == 2}">
                        <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                        <span id="remove" class="glyphicon glyphicon-remove"></span>
                        </a>
                    </c:if>
                    <div class="container-transparencia">
                        <div> Descrição:${transparencia.getDescricao()}</div>
                        <div> Valor Gasto R$:${transparencia.getValor()}</div>
                        <div> Data Vigência:${transparencia.getDataVigencia()}</div>
                    </div> 
                </div>
                </c:forEach>
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
            <script src="visualizar-transparencia/visualizar-transparencia.js"></script>
    </body>
</html>
