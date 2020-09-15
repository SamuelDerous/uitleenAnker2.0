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
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<s:set name="theme" value="'simple'" scope="page" />
<section id="hoofdinhoud">
    <article id="formulier" align="center">
				<h2 align="center">Toevoegen</h2>
				<s:form method="post" action="inventToevoegen" name="Toevoegen" onSubmit="return leeg();">
					<table border="0" align="center" width="25%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                                                            <s:actionerror escape="false" />
           
                  </div></td></tr>
                                                <jsp:useBean id="producten" class="databank.dao.ProductDao" />
                                                <tr><td>Productnaam:<span id="vereist">*</span></td><td><Select class="invullen" name="product" id="slctProductNaam" onKeypress="correct(this);">
                                                            <c:forEach var="productie" items="${producten.alleProducten}">
                                                                    <option value="${productie.id}">${productie.naam} 
                                                                    </c:forEach>
                                                        </select>
                                                    </td></tr>
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td>Aankoopdatum<span id="vereist">*</span> </td><td><s:textfield value="" cssClass="invullen" name="aankoopdatum" id="txtAankoopdatum" onKeypress="correct(this);"/></td></tr>
                                                <tr><td>Aantal: <span id="vereist">*</span> </td><td><s:textfield value="" cssClass="invullen" name="aantal" id="txtAantal" onKeypress="correct(this)" /></td></tr>
                                                
                                                <tr><td>Opmerkingen</td><td><s:textarea id="txtOpmerkingen" value="" name="opmerkingen" cssClass="invullen" rows="3" ></s:textarea></td></tr>
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