<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <title>Gerenciar Visitas</title>
        
        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="gerenciar-visitas/gerenciar-visitas.css">
    </head>

    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        
        <div class="container-fluid">
            <c:if test="${empty visitaList}">
                <div class="jumbotron">
                    <h2>Não Existem Visitas Cadastradas!</h2>
                </div>
            </c:if>
            <div>
                <div>
                    <table class="table table-striped">
                        <thead>
                            <th class="centered-header">Nome do Visitante</th>
                            <th class="centered-header">CPF</th>
                            <th class="centered-header">Data da visita</th>
                        </thead>

                        <tbody>
                            <c:forEach items="${visitaList}" var="visita">
                                <tr>
                                    <td>${visita.getNome()}</td>
                                    <td>${visita.getCpf()}</td>
                                    <td>${visita.getDataVisita()}
                                    <td data-toggle="tooltip" data-placement="top" title="Marcar que visita compareceu">
                                        <a onclick="atualizaVisita(this, ${visita.getId()})">
                                            <span class="glyphicon glyphicon-ok"></span>
                                        </a>
                                    </td>
                                    <td data-toggle="tooltip" data-placement="top" title="Marcar que visita não compareceu">
                                        <a onclick="deletaVisita(this, ${visita.getId()})">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>    
                    </table>
                </div>
            </div>
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <script src="gerenciar-visitas/gerenciar-visitas.js"></script>
    </body>
</html>
