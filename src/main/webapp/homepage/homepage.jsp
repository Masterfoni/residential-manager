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
            <h1>Bem-Vindo!</h1>
            <h3>Esta página de testes foi desenvolvida por Danilo e João</h3>
            <h4>TEstando a responsividade....</h4>
            TIPO DE USUÁRIO DA SESSÃO: ${sessionScope.userType == 2 ? 'Síndico' : 'Não-síndico'}
	</div>
            
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body> 
</html>
