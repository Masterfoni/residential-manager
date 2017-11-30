<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Home Page</title>
        
        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="homepage/homepage.css">
    </head>

    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        
        <div class="container-fluid">
            <c:if test="${empty inforList}">
                <div class="jumbotron">
                    <h1>Bem-Vindo</h1> 
                    <p>Parece que ainda não existe nenhum informativo publicado.</p> 
                </div>
            </c:if>
            <c:forEach items="${inforList}" var="informativo">
                <div class="main-informativo" data-toggle="tooltip" data-placement="right"
                     title="Este informativo foi adicionado às: ${informativo.getDataCriacao()}">
                    <c:if test="${sessionScope.userType == 2}">
                        <button type="button" class="close" onclick="deletaInformativo(this, ${informativo.getId()})">&times;</button>
                    </c:if>
                    <div class="container-informativo">
                        ${informativo.getDescricao()}
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
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <script src="homepage/homepage.js"></script>
        
        <c:if test="${not empty errorMessage}">
            <script>
                $('#myModal').modal('toggle');
            </script>
        </c:if>
        <c:if test="${not empty successMessage}">
            <script>
                $('#myModal').modal('toggle');
            </script>
        </c:if>
    </body> 
</html>
