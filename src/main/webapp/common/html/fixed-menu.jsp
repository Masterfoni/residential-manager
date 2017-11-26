<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <style>
        a:hover {
            cursor: pointer;
        }
    </style>

    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#theNavbar" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Gerenciador de Condominios</a>
            </div>
            <div id="theNavbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class=" dropdown">
                        <a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Informativos <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li class=" dropdown">
                                <a onclick="encaminhaRequisicao('VINFORMATIVO')" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Visualizar Informativos</a>
                            </li>
                            <c:if test="${sessionScope.userType == 2}">
                                <li>
                                    <a onclick="encaminhaRequisicao('CINFORMATIVO')">Publicar Informativo</a>
                                </li>
                            </c:if>
                        </ul>
                    </li>
                    <li class=" dropdown"><a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Portal de Transparencia <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Visualizar Transparencia</a></li>
                            <li><a href="#">Adicionar Transparencia</a></li>
                        </ul>
                    </li>
                    <li class=" dropdown"><a href="#" class="dropdown-toggle active" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reservas <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Gerenciar Reservas</a></li>
                            <li><a href="#">Realizar Reserva</a></li>
                            <li><a href="#">Visualizar Reserva</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle active" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Visitas <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <c:if test="${sessionScopeUserType !=3}">
                                <li>
                                    <a onclick="encaminhaRequisicao('RVISITA')" class="dropdown-toggle" data-toogle="dropdow" role="button" aria-expanded="false">Registrar Visita</a>
                                </li>
                            </c:if>
                            <li>
                                <a onclick="encaminhaRequisicao('GVISITA')" class="dropdown-toggle" data-toogle="dropdow" role="button" aria-expanded="false">Gerenciar Visitas</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form pull-right" action="ServletLoginLogout" method="post">  
                    <input type="hidden" name="ACTION" value="LOGOUT"/>
                    <button class="btn btn-link" type="submit">Sair <span class="glyphicon glyphicon-log-out"></span></button>
                </form>  
            </div>
        </div>
    </nav>

    <script>
        function encaminhaRequisicao(valorRequisicao) {
            var form = document.createElement("form");
            var inputPagina = document.createElement("input");

            form.method = "POST";
            form.action = "ServletEncaminhador";

            inputPagina.value = valorRequisicao;
            inputPagina.name = "ACTION";

            form.appendChild(inputPagina);

            document.body.appendChild(form);

            form.submit();
        }
    </script>
</html>
