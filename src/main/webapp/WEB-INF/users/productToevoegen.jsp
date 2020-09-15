<%-- 
    Document   : gebAanpassen
    Created on : 20-mei-2017, 17:36:35
    Author     : zenodotus
--%>
<% if((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="databank.TblBeschrijving"%>
<%@page import="databank.TblProduct"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<section id="hoofdinhoud">
    <article id="formulier" align="center">
        <h2 align="center">Toevoegen</h2>
				<s:form method="post" action="productToevoegen" name="Toevoegen" onSubmit="return leeg();">
					<table border="0" align="center" width="25%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                                                            <s:actionerror escape="false" />       
            
                  </div></td></tr>
                                                
                                                <tr><td><input type="hidden" value="toevoegen" name="actie" />
                                                        Productnaam:<span id="vereist">*</span></td><td><input type="text" class="invullen" name="naam" id="txtProductNaam" onKeypress="correct(this);"/></td></tr>
                                                
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td>Aankoopprijs</td><td><input type="text" class="invullen" name="aankoopprijs" id="txtAankoopprijs"/></td></tr>
                                                <tr><td>Breukprijs:</td><td><input type="text" class="invullen" name="breukprijs" id="txtBreukprijs" onKeypress="correct(this)" /></td></tr>
                                                <tr><td>Aankoopdatum</td><td><input type="text" class="invullen" name="aankoopdatum" id="txtAankoopdatum" onKeypress="correct(this);"/></td></tr>
                                                <tr><td>Aantal: <span id="vereist">*</span> </td><td><input type="text" class="invullen" name="aantal" id="txtAantal" onKeypress="correct(this)" /></td></tr>
                                                
                                                <tr><td>Opmerkingen</td><td><textarea id="txtOpmerkingen" name="opmerking" class="invullen" rows="3" ></textarea></td></tr>
                                                <tr><td>Website:</td><td><input type="text" class="invullen" name="website" id="txtWebsite" /></td></tr>
                                                <tr><td>Plaatsbepaling<span id="vereist">*</span></td><td><input type="text" class="invullen" name="plaats" id="txtPlaats" /></td></tr>
                                                <tr><td>Volledig</td><td><input type="checkbox" name="volledig" checked></td></tr>
                                                <tr><td>Uitleentermijn: </td><td><input type="text" class="invullen" name="uitleentermijn" id="txtUitleentermijn" value="2" /></td></tr>
                                                <tr><td>Soort materiaal: </td><td><select name="beschrijving" class="invullen">
                                                            
                                                        <jsp:useBean id="beschrijvingen" class="databank.dao.BeschrijvingDao" />
                                                        <c:forEach var="soort" items="${beschrijvingen.beschrijvingen}">
                                                            <option value="${soort.soort}">${soort.soort}</option> 
                                                        </c:forEach>
                                                </select></td>
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td colspan="2" align="center"><input type="submit" value="Toevoegen" id="txtSubmit" /><input type="reset" value="Wissen" /></td></tr>
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