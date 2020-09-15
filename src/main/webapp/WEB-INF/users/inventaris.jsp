<%-- 
    Document   : gebToevoegen
    Created on : 20-mei-2017, 12:14:16
    Author     : zenodotus
--%>
<% if((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="databank.TblInventarisatie"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section id="hoofdinhoud">
    <article id="gebruikers">
        <table id="inventarisTable" border="1" width="100%">
            <thead>
                <tr>
                    <td>productnaam</td>
                    <td>aantal</td>
                    <td>opmerking</td>
                    <td>Verwijderen</td>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean id="invents" class="databank.dao.InventarisDao" />
                <c:forEach var="invent" items="${invents.inventaris}">
                <tr><td>${invent.product.naam}</td>
                    <td>${invent.aantal}</td>
                    <td>${invent.opmerking}</td>
                    <td><a href="inventVerwijderen?invent=${invent.id}">Verwijderen</a><br></td></tr>
                </c:forEach>
            </tbody>
        </table>
    </article>
</section>   
                
                <script language="JavaScript" type="text/javascript">
<!--
$(document).ready(function() {
    $('#inventarisTable').DataTable();
} );
-->
</script> 
</body>
</html>
<% } else {
    response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
}
%>