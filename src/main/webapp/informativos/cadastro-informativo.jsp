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
                <input type="submit" class="iButton btn btn-success" value="Publicar">
            </form>
	</div>
        
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Atenção</h4>
                    </div>
                    <div class="modal-body">
                        <p class="error">Você precisa digitar algum conteúdo para publicar o informativo!</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>

        <script>
            CKEDITOR.replace('editor1');

            $('#ckeditorForm').submit(function () {
               if(!CKEDITOR.instances.editor1.getData())
               {
                   $('#myModal').modal('toggle');
                   return false;
               }

               return true;
            });
        </script>
    </body>
</html>
