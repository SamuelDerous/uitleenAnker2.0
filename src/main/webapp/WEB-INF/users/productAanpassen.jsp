<%-- 
    Document   : gebAanpassen
    Created on : 20-mei-2017, 17:36:35
    Author     : zenodotus
--%>
<%@page import="databank.TblInventarisatie"%>
<% if((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="databank.TblBeschrijving"%>
<%@page import="databank.TblProduct"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:set name="theme" value="'simple'" scope="page" />
<section id="hoofdinhoud">
    <article id="formulier" align="center">
				<h2 align="center">Aanpassen</h2>
				<s:form method="post" action="aanpassenProduct" name="Aanpassen">
					<table border="0" align="center" width="25%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                                                            <s:actionerror escape="false" />
                  </div></td></tr>
                                                <tr><td><input type="hidden" name="productId" value="${product.id}" />
                                                        <input type="hidden" name="naam" value="${product.naam}" />
                                                        <input type="hidden" name="actie" value="aanpassen" />
                                                        Product:</td><td><b><span>${product.naam}</span></b> </td></tr>
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td>Aankoopprijs</td><td><input type="text" value="${product.aankoopprijs}" class="invullen" name="aankoopprijs" id="txtAankoopprijs"/></td></tr>
                                                <tr><td>Breukprijs:</td><td><input type="text" value="${product.breukprijs}" class="invullen" name="breukprijs" id="txtBreukprijs" onKeypress="correct(this)" /></td></tr>
                                                <tr><td>Aankoopdatum</td><td><input type="text" value="${product.aankoopdatum}" class="invullen" name="aankoopdatum" id="txtAankoopdatum" onKeypress="correct(this);"/></td></tr>
                                                <tr><td>Aantal: <span id="vereist">*</span> </td><td><input type="text" value="${product.aantal}" class="invullen" name="aantal" id="txtAantal" onKeypress="correct(this)" /></td></tr>
                                                
                                                <tr><td>Opmerkingen</td><td><textarea id="txtOpmerkingen" name="opmerking" class="invullen" rows="3" >${product.opmerking}</textarea></td></tr>
                                                <tr><td>Website:</td><td><input type="text" class="invullen" value="${product.website}" name="website" id="txtWebsite" /></td></tr>
                                                <tr><td>Plaatsbepaling<span id="vereist">*</span></td><td><input type="text" class="invullen" value="${product.plaats}" name="plaats" id="txtPlaats" /></td></tr>
                                                <tr><td>Gecontroleerd</td><td><input type="checkbox" name="controle" value="on" <c:if test="${uitlening eq null || uitlening.controle eq null || uitlening.controle eq 1}">checked</c:if> disabled></td></tr>
                                                <tr><td>Volledig</td><td><input type="checkbox" name="volledig" value="on" <c:if test="${product.volledig eq 1}">checked</c:if>></td></tr>
                                                <tr><td>Uitleentermijn: </td><td><input type="text" class="invullen" value="${product.uitleentermijn}" name="uitleentermijn" id="txtUitleentermijn" /></td></tr>
                                                <tr><td>Soort materiaal: </td><td><select name="beschrijving" class="invullen">
                                                            
                                                        <jsp:useBean id="beschrijvingen" class="databank.dao.BeschrijvingDao" />
                                                        <c:forEach var="soort" items="${beschrijvingen.beschrijvingen}">
                                                            <option value="${soort.soort}" <c:if test="${soort.soort eq product.beschrijving.soort}">selected </c:if>>${soort.soort}</option> 
                                                        </c:forEach>
                                                </select></td>
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td colspan="2" align="center"><input type="submit" value="Aanpassen" id="txtSubmit" /><input type="reset" value="Wissen" /></td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>	
        </table>
                                                        
                                                
</s:form>
<jsp:useBean id="invents" class="databank.dao.InventarisDao" />
                                <c:set var="aantal"  value="${invents.getInventarisProductAantal(product)}" />
<c:if test="${aantal gt 0}">
      
                                
                                                                            <h2 align="center">Inventarisatie</h2>
                                                <table border="1" width="100%">
                                                    <thead><td><b>Naam Product</b></td><td><b>Aantal</b></td><td>&nbsp;</td></thead>
                                                    <c:forEach var="invent" items="${invents.getInventarisProduct(product)}">
                                                        <tr><td>${invent.product.naam}</td><td>${invent.aantal}</td><td><a href="inventVerwijderenProduct?invent=${invent.id}">Verwijderen</a></td></tr>
                                                </c:forEach>
                                                        <tr><td><b>TOTAAL:</b> </td><td><b>${aantal}</b></td><td>&nbsp;</td></tr>
                                                        </table>

</c:if>
</article>
</section>
</div>
</body>
</html>
<%} else {
    response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
}%>