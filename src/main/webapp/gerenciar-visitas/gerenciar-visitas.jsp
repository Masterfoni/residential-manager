<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <title>Gerenciar Visitas</title>
        
        <%@include file="../common/html/header-libs.html"%>
    </head>

    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        <div class="container-fluid">
            <c:if test="${empty visitaList}">
                <div class="jumbotron">
                    <h2>Não Existem Visitas Cadastradas!</h2>
                </div>
            </c:if>
            <c:forEach items="${visitaList}" var="visita">
                <div data-toggle="tooltip"
                     title="Visita Cadastrada às: ${visita.getDataCriacao()}">
                    <div>
                        <table class="table table-striped">
                            <thead>
                            <th>NOME VISITANTE</th>
                            <th>DOCUMENTO CPF</th>
                            </thead>

                            <tbody>
                            <td>${visita.getNome()}</td>
                            <td>${visita.getCpf()}</td>
                            <td><a href="#">
                                    <span class="glyphicon glyphicon-ok"></span>
                                </a>
                            </td>
                            <td>
                                <a href="#">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </td>
                            </tbody>    
                        </table>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
