<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="index/index.css">
        
        <title>GD - Login</title>
    </head>
    <body>

        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-login">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-6">
                                    <a href="#" class="active" id="login-form-link">Login</a>
                                </div>
                                <div class="col-xs-6">
                                    <a href="#" id="register-form-link">Registrar</a>
                                </div>
                            </div>
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" action="ServletLoginLogout" method="post" role="form" style="display: block;">
                                        <div class="form-group">
                                            <input type="text" name="valLogin" tabindex="1" class="form-control" 
                                                   placeholder="Digite seu login" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="valSenha" tabindex="2" class="form-control" 
                                                   placeholder="Digite sua senha">
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" tabindex="4" class="form-control btn btn-login" value="Logar">
                                                </div>
                                            </div>
                                        </div>
                                        <input type="hidden" name="ACTION" value="LOGIN"/>
                                    </form>
                                    <form id="register-form" action="ServletCadastro" method="post" role="form" style="display: none;">
                                        <div class="form-group">
                                            <label for="selectApartamento">Escolha seu apartamento:</label>
                                            <select class="form-control" id="selectApartamento" name="valApartamento">
                                                <c:forEach items="${apartamentos}" var="ap">
                                                    <option value="${ap.id}"><c:out value="${ap.numero}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="valLogin" id="username" tabindex="1" class="form-control" placeholder="Digite seu login" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="valNome" tabindex="1" class="form-control" placeholder="Digite seu nome" value="">
                                        </div>
                                        <div class="form-group">
                                            <label for="selectSexo">Sexo:</label>
                                            <select class="form-control" id="selectSexo" name="valSexo">
                                                <option value="Masculino">Masculino</option>
                                                <option value="Feminino">Feminino</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="valCpf" tabindex="1" class="cpf form-control" placeholder="Digite seu CPF" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="valSenha" id="password" tabindex="2" class="form-control" placeholder="Digite sua senha">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="valConfSenha" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirme sua senha">
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" tabindex="4" class="form-control btn btn-register" value="Registrar">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
        
        <footer class="footer">
            <div class="container">
                <span>
                    Gerenciador de Condomínios ® </br>
                    João Vitor, Danilo Pereira
                </span>
            </div>
        </footer>
        
        <script src="common/js/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.bootcss.com/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
        <script src="index/index.js"></script>
        
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
