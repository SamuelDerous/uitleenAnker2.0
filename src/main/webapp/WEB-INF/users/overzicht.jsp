<%-- 
    Document   : overzicht
    Created on : Dec 9, 2017, 5:35:40 AM
    Author     : zenodotus
--%>

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
<%@taglib uri="/struts-tags" prefix="s" %>
<section id="hoofdinhoud">
    <article id="formulier" align="center">
				<h2 align="center">Toon Overzicht</h2>
				<s:form method="post" action="periodiekOverzicht" name="overzicht">
					<table border="0" align="center" width="100%">
						<tr><td colspan="2"><div id="foutmelding" name="foutmelding">
                                                            <s:actionerror escape="false" />
            
                  </div></td></tr>
                                                
                                                        
                                                <tr><td>Product ID</td>
                                                    <td><input type="text" class="invullen" autocomplete="off" name="productId" id="txtProductId" list="producten"/>
                                                        <datalist id="producten">
                                                            <option value="0">Alle</option>
                                                            <jsp:useBean id="productie" class="databank.dao.ProductDao" />
                                                            <c:forEach var="product" items="${productie.alleProducten}">
                                                            <option value="${product.id}">${product.naam}</option>
                                                            </c:forEach>
                                                        </datalist></td></tr>
                                                <tr><td>Periode: </td><td><select name="jaar">
                                                            <option value="2015">2015</option>
                                                            <option value="2016">2016</option>
                                                            <option value="2017">2017</option>
                                                            <option value="2018">2018</option>
                                                            <option value="2019">2019</option>
                                                            <option value="2020">2020</option>
                                                            <option value="2021">2021</option>
                                                        </select></td></tr>
                                                <tr><td>&nbsp;</td><td><input type="radio" name="sort" value="yearly">Jaarlijks
                                                    <input type="radio" name="sort" value="academicly">per academiejaar</td></tr>       
                                                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                <tr><td colspan="2" align="center"><input type="submit" value="Overzicht" id="txtSubmit" /><input type="reset" value="Wissen" /></td></tr>
						<tr><td>&nbsp;</td><td>&nbsp;</td></tr>	
        </table>
</s:form>
    </article>
</section>
</div>
</body>
</html><% } else {
    response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
} 
%>
