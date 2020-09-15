<%-- 
    Document   : uitloggen
    Created on : 27-mei-2017, 15:41:24
    Author     : zenodotus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uitloggen</title>
    </head>
    <body>
        <c:remove var="gebruikersnaam" scope="session" />
        <c:remove var="soort" scope="session" />
        <c:redirect url="inloggen.jsp" />
    </body>
</html>
