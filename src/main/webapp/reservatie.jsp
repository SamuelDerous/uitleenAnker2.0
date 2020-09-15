<%-- 
    Document   : reservatie
    Created on : 24-jun-2017, 23:39:28
    Author     : zenodotus
--%>

<%@page import="databank.TblUitleen"%>
<%@page import="databank.TblReservatie"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.List"%>
<%@page import="databank.TblProduct"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="headers/header.jsp" />
<jsp:include page="headers/menu.jsp" />
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 
<script src="${pageContext.request.contextPath}/js/formulier.js"></script>
<section id="hoofdinhoud">
                
                <div class="dialoog" title="reservatie">
                    <% if(session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) {%>
                    <form id="doen" action="reserverenVanafAccount" method="post">
                        <table id="formTabel" border="0" align="center">
                            <tr><td><input type="hidden" name="gebruikersnaam" value="${sessionScope.gebruikersnaam}">
                                    <input type="text" placeholder="aantal" size="4" name="aantal" value="${aantal}" />
                                    <input type="hidden" name="productId" value="${product.id}" /></td></tr>
                        </table>
                    </form>
                    <%} else {%>
                    <p>U dient aangemeld te zijn om te kunnen reserveren</p>
                    <%}%>
                </div>
                <article id="formulier" align="center">
                            <table border="0" width="100%">
                                <tr><td width="20%">Naam:</td><td colspan="2" width="80%">${product.naam}</td></tr>
                                <tr><td>Aantal:</td><td colspan="2">${product.aantal}</td></tr>
                                <tr><td>Uitgavevorm:</td><td colspan="2">${product.beschrijving.soort}</td></tr>
                                <tr><td>Aankoopdatum:</td><td colspan="2">${product.aankoopdatum}</td></tr>
                                <tr><td>Breukprijs:</td><td colspan="2">${product.breukprijs}</td></tr>
                                <tr><td>Beschrijving:</td><td colspan="2">${product.opmerking}</td></tr>
                                <tr><td>Website:</td><td colspan="2">${product.website}</td></tr>
                                <tr><td colspan=3"><hr></td></tr>
                                <tr><td colspan="3">&nbsp;</td></tr>
                                <tr><td>Reservaties</td><td align="right">${fn:length(reservaties)}</td><td><button class="create" value="Reserveer">Reserveer</button>
                                <s:if test="!uitleningen.isEmpty()"> 
                                    <s:iterator var="uitlening" value="uitleningen">    
                                    
                                <tr><td class="uitgeleend">Uitgeleend tot</td><td colspan="2" align="right" class="uitgeleend">${uitlening.terugbrengdatum}</td></tr>
                                    </s:iterator>
                                <tr><td>Totaal</td><td colspan="2" align="right">${fn:length(uitleningen)}</td></tr>
                                    </s:if>
                            </table>
			</article>
                                        
                 
      </section>
    </div>
  </body>
</html>
