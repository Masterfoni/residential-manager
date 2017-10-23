
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home Page</h1>
            <form action="ServletLoginLogout" method="post">  
            <input type="hidden" name="ACTION" value="LOGOUT"/>
            <input type="submit" value="logout"/>  
        </form>  
    </body>
</html>
