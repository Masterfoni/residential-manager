<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Reserva</title>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="cadastro-reserva/cadastro-reserva.css">
    </head>
    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        
        <div class="container-fluid">
            <div class="page-header">
                <h1>Solicitar Reserva</h1>
            </div>
            
            <div class="row">
                <form id="form-reserva" action="ServletReserva" method="post" role="form">
                    <input type="hidden" name="ACTION" value="CADASTRAR"/>
                    <div class="form-group">
                        <select class="form-control" name="valDependencia">
                            <optgroup label="Dependencias">
                                <c:forEach items="${dependencias}" var="dependencia">
                                    <option value="${dependencia.id}"><c:out value="${dependencia.nome}"/></option>
                                </c:forEach>
                            </optgroup>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="datetime-local" name="valDataInicio" class="form-control" placeholder="Digite a partir de quando reservará">
                    </div>
                    
                    <div class="form-group">
                        <input type="datetime-local" name="valDataFim" class="form-control" placeholder="Escolha até quando reservará">
                    </div>
                    
                    <div class="form-group">
                        <div class="col-sm-6 col-md-offset-3">
                            <button type="submit" class="btn btn-lg btn-primary btn-block">Realizar Solicitação</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>  
        <script src="cadastro-reserva/cadastro-reserva.js"></script>
    </body>
</html>
