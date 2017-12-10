<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Visualizar Despesas</title>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="../common/html/header-libs.html"%>
        <link rel="stylesheet" type="text/css" href="visualizar-transparencia/visualizar-transparencia.css">
    </head>
    <body>
        <%@include  file="../common/html/fixed-menu.jsp" %>
        
        <div class="container-fluid">
            <div class="page-header">
                <h1>Despesas Condominiais</h1>
                <form action="ServletTransparencia" method="post" role="form">
                    <input type="hidden" name="ACTION" value="SELECTANO"/>
                    <select class="form-control" name="valAno" onchange="this.form.submit()">
                        <option value="2010">2010</option>
                        <option value="2011">2011</option>
                        <option value="2012">2012</option>
                        <option value="2013">2013</option>
                        <option value="2014">2014</option>
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                    </select>
                </form>
            </div> 
            
            <c:if test="${empty transpList}">
                <div class="jumbotron">
                    <h2>Não existem transparências adicionadas para este ano!</h2> 
                </div>
            </c:if>
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty janTranspList}">
            <div class="row">
                <h4> Janeiro </h4>
            </div>
            </c:if>
            <c:forEach items="${janTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty fevTranspList}">
            <div class="row">
                <h4> Fevereiro </h4>
            </div>
            </c:if>
            <c:forEach items="${fevTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty marTranspList}">
            <div class="row">
                <h4> Março </h4>
            </div>
            </c:if>
            <c:forEach items="${marTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty maiTranspList}">
            <div class="row">
                <h4> Maio </h4>
            </div>
            </c:if>
            <c:forEach items="${maiTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty abrTranspList}">
            <div class="row">
                <h4> Abril </h4>
            </div>
            </c:if>
            <c:forEach items="${abrTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty junTranspList}">
            <div class="row">
                <h4> Junho </h4>
            </div>
            </c:if>
            <c:forEach items="${junTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty julTranspList}">
            <div class="row">
                <h4> Julho </h4>
            </div>
            </c:if>
            <c:forEach items="${julTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty agoTranspList}">
            <div class="row">
                <h4> Agosto </h4>
            </div>
            </c:if>
            <c:forEach items="${agoTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty setTranspList}">
            <div class="row">
                <h4> Setembro </h4>
            </div>
            </c:if>
            <c:forEach items="${setTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty outTranspList}">
            <div class="row">
                <h4> Outubro </h4>
            </div>
            </c:if>
            <c:forEach items="${outTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty novTranspList}">
            <div class="row">
                <h4> Novembro </h4>
            </div>
            </c:if>
            <c:forEach items="${novTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
            
            <!-- INICIO DE UMA LISTA MENSAL -->
            <c:if test="${not empty dezTranspList}">
            <div class="row">
                <h4> Dezembro </h4>
            </div>
            </c:if>
            <c:forEach items="${dezTranspList}" var="transparencia" varStatus="loop">
                <c:if test="${loop.index == 0 || (loop.index % 3) == 0}">
                    <div class="row">
                </c:if>
                <div class="block-update-card status col-md-4">
                    <div class="v-status">
                    </div>
                    <div class="update-card-body">
                        <h4>
                            Dia <fmt:formatDate value ="${transparencia.getDataVigencia()}" pattern="dd"/> - 
                            R$ ${transparencia.getValor()}
                        </h4>
                        <p>${transparencia.getDescricao()}</p>
                        <c:if test="${sessionScope.userType == 2}">
                            <a  onclick="deletaTransparencia(this, ${transparencia.getId()})">
                                <span id="remove" class="glyphicon glyphicon-remove"></span>
                            </a>
                        </c:if>
                    </div>        
                </div>
                <c:if test="${((loop.index + 1) % 3) == 0 || loop.last}">
                    </div>
                </c:if>
            </c:forEach>
            <!-- FINAL DE UMA  LISTA MENSAL -->
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>        
        <script src="visualizar-transparencia/visualizar-transparencia.js"></script>
    </body>
</html>
