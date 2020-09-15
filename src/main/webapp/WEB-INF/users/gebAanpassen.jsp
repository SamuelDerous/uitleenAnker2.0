<%-- 
    Document   : gebAanpassen
    Created on : 20-mei-2017, 17:36:35
    Author     : zenodotus
test
--%>
<% if((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="databank.TblSoort"%>
<%@page import="databank.TblPersoon"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<section id="hoofdinhoud">
    <article id="formulier" align="center">
				<h2 align="center">Aanpassen</h2>
				<form method="post" action="gebAanpassen" name="Aanpassen">
					<table border="0" align="center" width="25%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                                                            <s:actionerror escape="false" />
                                     
                                  </div></td></tr>
                                                
                                                <tr><td><input type="hidden" name="persoon.gebruikersnaam" value="${gebruiker.gebruikersnaam}" />Gebruikersnaam:</td><td><b><span name="gebruikersnaam">${gebruiker.gebruikersnaam}</span></b> </td></tr>
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td>Voornaam:<span id="vereist">*</span> </td><td><input type="text" value="${gebruiker.voornaam}" class="invullen" name="persoon.Voornaam" id="txtVoornaam" /></td></tr>
                                                <tr><td>Naam:<span id="vereist">*</span> </td><td><input type="text" value="${gebruiker.naam}" class="invullen" name="persoon.naam" id="txtNaam" onKeypress="correct(this)" /></td></tr>
                                                <tr><td>Adres: </td><td><textarea id="txtAdres" name="persoon.adres" class="invullen" rows="3" >${gebruiker.adres}</textarea></td></tr>
                                                <tr><td>Telefoon: </td><td><input type="text" class="invullen" value="${gebruiker.telefoon}" name="persoon.Telefoon" id="txtTelefoon" /></td></tr>
                                                <tr><td>E-mail:<span id="vereist">*</span> </td><td><input type="email" value="${gebruiker.EMail}" class="invullen" name="persoon.EMail" id="txtEmail" onKeypress="correct(this)"/></td></tr>
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td>Soort gebruiker:</td><td><select name="persoon.soort.soort" class="invullen">
                                                <jsp:useBean id="soorten" class="databank.dao.SoortDao" />
                                                            <c:forEach var="soortItem" items="${soorten.soorten}">
                                                                <option value="${soortItem.soort}"${soortItem.soort eq gebruiker.soort.soort ? 'selected' : ' '}>${soortItem.soort}</option>
                                                            </c:forEach></select></td></tr>
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                
                                                <tr><td colspan="2" align="center"><input type="submit" value="Aanpassen" id="txtSubmit" /><input type="reset" value="Wissen" /><input type="button" name="btnWachtwoord" onclick=location.href="resetWachtwoord?gebruikersnaam=${gebruiker.gebruikersnaam}"   value="Reset ww" /></td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>	
        </table>
</form>
    </article>
</section>
</div>
</body>
</html>
<%} else {
    response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
}%>