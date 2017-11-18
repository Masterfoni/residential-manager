<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Informativo</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="informativos/cadastro-informativo.css">
        
        <script src="common/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        
        <div class="container-fluid">
            <form id="ckeditorForm" method="POST" action="ServletInformativo">
                <textarea name="editor1" id="editor1" rows="20" cols="80">
                    Digite aqui o informativo que você deseja publicar.
                </textarea>
                <input type="submit" class="form-control btn btn-register" value="Publicar">
            </form>
            
            <script>
                CKEDITOR.replace('editor1');
                
                $('#ckeditorForm').submit(function () {
                   
                   return true;
                });

            </script>
	</div>
    </body>
</html>
