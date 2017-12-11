<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <title>Gerenciar Reservas</title>
        
        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="visualizar-reserva/visualizar-reserva.css">
    </head>

    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        
        <div class="container-fluid">
            <c:if test="${empty reservaList}">
                <div class="jumbotron">
                    <h2>Não existem solicitações de reservas!</h2>
                </div>
            </c:if>
            <c:if test="${not empty reservaList}">
            <div>
                <div>
                    <table class="table table-striped">
                        <thead>
                            <th class="centered-header">Nome do solicitante</th>
                            <th class="centered-header">Dependencia</th>
                            <th class="centered-header">Período</th>
                        </thead>

                        <tbody>
                            <c:forEach items="${reservaList}" var="reserva">
                                <tr>
                                    <td>${reserva.getUsuario().getNome()}</td>
                                    <td>${reserva.getDependencia().getNome()}</td>
                                    <td>De ${reserva.getDataInicio()} à ${reserva.getDataFim()}</td>
                                    <td data-toggle="tooltip" data-placement="top" title="Aprovar reserva">
                                        <a onclick="aprovarReserva(this, ${reserva.getId()})">
                                            <span class="glyphicon glyphicon-ok"></span>
                                        </a>
                                    </td>
                                    <td data-toggle="tooltip" data-placement="top" title="Reprovar reserva">
                                        <a onclick="rejeitarReserva(this, ${reserva.getId()})">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>    
                    </table>
                </div>
            </div>
            </c:if>
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <script src="visualizar-reserva/visualizar-reserva.js"></script>
    </body>
</html>
