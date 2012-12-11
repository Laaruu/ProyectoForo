<%-- 
    Document   : cerrarSesion
    Created on : 06-nov-2012, 19:49:49
    Author     : Laur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    /* Aquí cerramos la sesión
     Para cerrar la sesion se usa el metodo invalidate() */
    HttpSession sesion = request.getSession();
    sesion.invalidate();
%>
<jsp:forward page="index.jsp" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyecto Foro</title>
    </head>
    <body>


    </body>
</html>
