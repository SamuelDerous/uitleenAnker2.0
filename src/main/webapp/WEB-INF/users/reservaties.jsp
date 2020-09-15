<%-- 
    Document   : reservaties
    Created on : 28-jun-2017, 20:51:33
    Author     : zenodotus
--%>

<%@page import="databank.TblReservatie"%>
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
<%@taglib uri="/struts-tags" prefix="s"%>
<script>
    
</script>
    
<section id="hoofdinhoud">
    <article id="gebruikers">
        <div id="foutmelding"><s:actionerror escape="false" /></div>
        <table id="reservatiesTable" border="1" width="100%">
            <thead>
                <tr>
                    <td>Gebruikersnaam</td>
                    <td>Productnaam</td>
                    <td>Aantal</td>
                    <td>Plaats</td>
                    <td>ReservatieDatum</td>
                    <td>Bewerkingen</td>
                </tr>
            </thead>
            <tbody>
            
                
                    <jsp:useBean id="reservaties" class="databank.dao.ReservatieDao" />
                    <c:forEach var="reservatie" items="${reservaties.alleReservaties}">
                        <tr><td><a href="aanpassenGebruiker?gebruikersnaam=${reservatie.gebruiker.gebruikersnaam}">${reservatie.gebruiker.gebruikersnaam}</a></td>
                            <td><a href="aanpassenProductForm?productId=${reservatie.product.id}">${reservatie.product.naam}</a></td>
                    <td>${reservatie.aantal}</td>
                    <td>${reservatie.product.plaats}</td>
                    <td>${reservatie.reservatieDatum}</td>
                    <td><a href="reservatieVerwijderen?reservatie=${reservatie.id}">Verwijderen</a><br>
                        <a href="omzetten?reservatie=${reservatie.id}" onclick="bericht();">Uitlenen</a><br></td></tr>
                    </c:forEach>
            </tbody>
                        </table>
    </article>
</section>    
                    <script language="JavaScript" type="text/javascript">
<!--
$(document).ready(function() {
    $('#reservatiesTable').DataTable();
} );
-->
</script> 
</body>
</html>
<% } else {
    response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
}
%>