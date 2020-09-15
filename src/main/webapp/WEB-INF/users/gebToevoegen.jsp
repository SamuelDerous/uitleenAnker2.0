<%-- 
    Document   : registreren
    Created on : 10-mei-2017, 11:59:46
    Author     : zenodotus
--%>
<% if((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="java.util.List"%>
<%@page import="databank.TblSoort"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<s:set name="theme" value="'simple'" scope="page" />
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />


<script type="text/javascript" src="../js/validation.js"></script>
<% 
                SessionFactory factory = HibernateFactory.getSessionFactory();
                Session sessie = factory.openSession();
                String gebruiker = request.getParameter("gebruiker");
                
                Query soort = sessie.createQuery("from TblSoort");
                List<TblSoort> soorten = soort.list();
                sessie.close();
                %>
<section id="hoofdinhoud">
			<article id="formulier" align="center">
				<h2 align="center">Gebruiker Toevoegen</h2>
				<s:form method="post" action="toevoegen" name="toevoegen">
					<table border="0" align="center" width="25%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                                                            <s:actionerror escape="false" />
                                                    </div></td></tr>
                                                <tr><td>Gebruikersnaam:<span id="vereist">*</span></td><td><s:textfield cssClass="invullen" name="gebruikersnaam" id="txtGebruikersnaam"/></td></tr>
                                                <tr><td>Wachtwoord:<span id="vereist">*</span> </td><td><s:password cssClass="invullen" name="wachtwoord" id="txtWachtwoord" /></td></tr>
                                                <tr><td>Bevestig wachtwoord:<span id="vereist">*</span> </td><td><s:password cssClass="invullen" name="bevestig" id="txtBevestig"/></td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td>Voornaam:<span id="vereist">*</span> </td><td><s:textfield cssClass="invullen" name="voornaam" id="txtVoornaam"/></td></tr>
                                                <tr><td>Naam:<span id="vereist">*</span> </td><td><s:textfield cssClass="invullen" name="naam" id="txtNaam" /></td></tr>
						<tr><td>Adres: </td><td><s:textarea id="txtAdres" name="adres" cssClass="invullen" rows="3" ></s:textarea></td></tr>
						<tr><td>Telefoon: </td><td><s:textfield cssClass="invullen" name="telefoon" id="txtTelefoon" /></td></tr>
                                                <tr><td>E-mail:<span id="vereist">*</span> </td><td><s:textfield cssClass="invullen" name="email" id="txtEmail"/></td></tr>
                                                <tr><td>Soort gebruiker:</td><td><select name="soort" class="invullen">
                                                            <jsp:useBean id="soorten" class="databank.dao.SoortDao" />
                                                            <c:forEach var="soortItem" items="${soorten.soorten}">
                                                                <option value="${soortItem.soort}" <c:if test="${soortItem.soort.equals('ontlener')}"> selected</c:if>>${soortItem.soort}</option>
                                                            </c:forEach>
                                                                                                                               </select></td>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
						<tr><td colspan="2" align="center"><s:submit value="Toevoegen" id="txtSubmit" /><s:reset value="Wissen" /></td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>						
					</table>				
				</s:form>			
			</article>      
      </section>
    </div>
  </body>
</html>
<%} else {
    response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
}%>