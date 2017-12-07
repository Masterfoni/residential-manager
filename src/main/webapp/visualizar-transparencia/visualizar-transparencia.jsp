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

    </head>
    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>

        <h1>Visualizar Transparencias</h1>
        <div class="container-fluid">
            <c:if test="${empty transpList}">
                <div class="jumbotron">
                    <h2>Não há Transparências Adicionadas até o Momento.</h2> 
                </div>
            </c:if>
            <c:forEach items="${transpList}" var="transparencia">
                    <c:if test="${sessionScope.userType == 2}">
                       <!-- <button type="button" class="close" onclick="deletaInformativo(this, ${informativo.getId()})">&times;</button>-->
                    </c:if>
                    <div class="container-transparencia">
                        ${transparencia.getDescricao()}
                        ${transparencia.getValor()}
                        ${transparencia.getdataVigencia()}
                    </div>
                </div>
            </c:forEach>

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
    </body>
</html>
