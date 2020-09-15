<%-- 
    Document   : gebAanpassen
    Created on : 20-mei-2017, 17:36:35
    Author     : zenodotus
--%>
<% if((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && (session.getAttribute("soort").equals("ontlener") || session.getAttribute("soort").equals("medewerker"))) { %>
<%@page import="databank.TblSoort"%>
<%@page import="databank.TblPersoon"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<section id="hoofdinhoud">
    <article id="formulier" align="center">
				<h2 align="center">Aanpassen</h2>
				<form method="post" action="resetwwAccount" name="Aanpassen" onSubmit="return leeg();">
					<table border="0" align="center" width="25%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
        
                  </div></td></tr>
                                                <tr><td><input type="hidden" name="gebruikersnaam" value="${param.gebruikersnaam}"/> Wachtwoord:<span id="vereist">*</span> </td><td><input type="password" class="invullen" name="wachtwoord" id="txtWachtwoord" onKeypress="correct(this)" /></td></tr>
                                                <tr><td>Bevestig wachtwoord:<span id="vereist">*</span> </td><td><input type="password" class="invullen" name="bevestig" id="txtBevestig" onKeyUp="gelijkeWachtwoorden()" /></td></tr>
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