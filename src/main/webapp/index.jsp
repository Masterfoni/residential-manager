<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GD - Login</title>
    </head>
    <body>
        <h1>AINDA EM FASE DE TESTES E SEM LAYOUT!!</h1>
        <form action="ServletLoginLogout" method="post">  
            Login: <input type="text" name="valLogin"/><br/>  
            Senha: <input type="password" name="valSenha"/><br/>  
            <input type="hidden" name="ACTION" value="LOGIN"/>
            <input type="submit" value="login"/>  
        </form>  
    </body>
</html>
