<%-- 
    Document   : TelaInicial
    Created on : 11 de dez. de 2023, 23:39:43
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String login = request.getParameter("login");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina inicial</title>
    </head>
    <body>
        <br>
        <br>
        <h3>ola <%=login%>, voce esta autenticado</h3>
    </body>
</html>
