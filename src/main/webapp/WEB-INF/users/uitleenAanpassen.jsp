<%-- 
    Document   : uitleenAanpassen
    Created on : 3-jul-2017, 3:42:32
    Author     : zenodotus
--%>

<%@page import="databank.TblUitleen"%>
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

<section id="hoofdinhoud">
    <article id="formulier" align="center">
				<h2 align="center">Aanpassen</h2>
				<form method="post" action="uitleenAanpassen" name="Aanpassen" onSubmit="return leeg();">
					<table border="0" align="center" width="25%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                                                            <s:actionerror escape="false" />
                  </div></td></tr>
                                                <tr><td><input type="hidden" name="uitleen" value="${uitlening.id}"/>
                                                        
                                                        Gebruikersnaam:</td><td><b><span>${uitlening.naam.gebruikersnaam}</span></b> </td></tr>
                                                <tr><td>Product</td><td><b>${uitlening.spel.naam}</b></td></tr>
                                                <tr><td>Uitleendatum<span id="vereist">*</span> </td><td><input type="text" value="${uitlening.uitleendatum}" class="invullen" name="uitleendatum" id="txtUitleendatum" onKeypress="correct(this)" /></td></tr>
                                                <tr><td>Terugbrengdatum<span id="vereist">*</span> </td><td><input type="text" value="${uitlening.terugbrengdatum}" class="invullen" name="terugbrengdatum" id="txtTerugbrengdatum" onKeypress="correct(this);"/></td></tr>
                                                <tr><td>Aantal <span id="vereist">*</span> </td><td><input type="text" value="${uitlening.aantal}" class="invullen" name="aantal" id="txtAantal" onKeypress="correct(this)" /></td></tr>
                                                
                                                <tr><td>boete</td><td><input t ype="text" value="${uitlening.boete}" class="invullen" name="boete" id="txtBoete" /></td></tr>
                                                <tr><td>huurprijs</td><td><input type="text" class="invullen" value="${uitlening.huurprijs}" name="huurprijs" id="txtHuurprijs" /></td></tr>
                                                <tr><td>Opmerking </td><td><textarea id="txtOpmerking" name="opmerking" class="invullen">${uitlening.opmerking}</textarea>
                                                </td>
                                                <tr><td>Teruggebracht</td><td><input type="text" value="${uitlening.teruggebracht}" class="invullen" id="teruggebracht" name="txtTeruggebracht" /></td></tr>
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td colspan="2" align="center"><input type="submit" value="Aanpassen" id="txtSubmit" /><input type="reset" value="Wissen" /></td></tr>
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