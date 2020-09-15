<%-- 
    Document   : uitleningen
    Created on : 30-jun-2017, 18:15:43
    Author     : zenodotus
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="databank.TblReservatie"%>
<%@page import="databank.TblUitleen"%>
<% if((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && (session.getAttribute("soort").equals("ontlener") || session.getAttribute("soort").equals("medewerker"))) { %>
<%@page import="databank.TblInventarisatie"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="databank.TblPersoon"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<section id="hoofdinhoud">
    <article id="gebruikers">
        <table id="accountsTable" border="1" width="100%">
            <c:set var="actie" value="${param.action}"/>
                   
                <c:if test="${actie eq 'uitlening'}">
                    <jsp:useBean id="uitleningen" class="databank.dao.UitleenDao"/>
                    <c:forEach var="uitlening" items="${uitleningen.getActieveUitleningenGebruiker(gebruikersnaam)}">
                    <tr><td>${uitlening.naam.gebruikersnaam}</td>
                        <td>${uitlening.spel.naam}</td>
                    <td>${uitlening.aantal}</td>
                    <td>${uitlening.uitleendatum}</td>
                    <td>${uitlening.terugbrengdatum}</td>
                    <td><a href="verlengenGebruiker?uitleen=${uitlening.id}&action=uitlening">Verlengen</a></td></tr>
                    </c:forEach>
                    </c:if>
                    <c:if test="${actie eq 'reservering'}">
                        <jsp:useBean id="reserveringen" class="databank.dao.ReservatieDao"/>
                        <c:forEach var="reservering" items="${reserveringen.getReservatiesGebruiker(gebruikersnaam)}">
                    <tr><td>${reservering.gebruiker.gebruikersnaam}</td>
                        <td>${reservering.product.naam}</td>
                    <td>${reservering.aantal}</td>
                    <td>
                        <a href="reservatieVerwijderen?reservatie=${reservering.id}">Verwijderen</a></td></tr>
                        </c:forEach>
                    </c:if>
        </table>
    </article>
</section>    
</body>
</html>
<% } else {
    response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
}
%>