<%-- 
    Document   : gebToevoegen
    Created on : 20-mei-2017, 12:14:16
    Author     : zenodotus
--%>
<%@page import="databank.TblPersoon"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if ((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="databank.TblProduct"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Query"%>
<%@page import="databank.adapter.HibernateFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />



<section id="hoofdinhoud">
    <article id="gebruikers">
        <div id="foutmelding">
                            <s:actionerror escape="false" />
                        </div>
        <table id="productenTable" border="1" width="100%">
            <thead>
                <tr>
                    <td>Productnaam</td>
                    <td>Aantal</td>
                    <td>Soort</td>
                    <td>Plaats</td>
                    <td>Bewerkingen</td>
                </tr>
            </thead>
            <tbody>
            <jsp:useBean id="producten" class="databank.dao.ProductDao" />
            <c:forEach var="product" items="${producten.alleProducten}">


                <tr><td><c:if test="${product.website ne null && product.website ne ''}" var="testWebsite"><a href="${product.website}" target="_blank"></c:if>${product.naam}<c:if test="${testWebsite}"></a></c:if></td>
                <td>${product.aantal}</td>
                <td>${product.beschrijving.soort}</td>
                <td>${product.plaats}</td>
                <td class="test"><a href="verwijderenProduct?productId=${product.id}">Verwijderen</a><br>
                    
                    <a href="aanpassenProductForm?productId=${product.id}">Aanpassen</a><br>
                    <script>
                    $(function () {
                            var dialog;

                            // a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
                            $("#dialog:ui-dialog").dialog("destroy");



                            $("#uitleen${product.id}").hide();

                            dialog = $("#uitleen${product.id}").dialog({
                                autoOpen: false,
                                height: 400,
                                width: 350,
                                modal: true,
                                closeOnEscape: true,
                                buttons: {
                                    "Uitlenen": function () {
                                        
                                        $("#doen${product.id}").submit();
                                    }

                                },
                                close: function () {
                                    dialog.dialog("close");

                                }
                            });




                            $("#uitlenen${product.id}").on("click", function (event) {
                                event.preventDefault();
                                dialog.dialog("open");
                            });
                        }
                        );
                </script>
                    <a href="#" id="uitlenen${product.id}">Uitlenen</a>
                    <div id="uitleen${product.id}" title="Uitlening" align="center">
                        
                        <form id="doen${product.id}" action="uitlenenProduct" method="post">
                           
                           <input type="hidden" value="${product.id}" name="productId" />
                           <input type="hidden" value="/users/producten.jsp" name="website" /> 
                           <select name="gebruikersnaam"> 
                                <jsp:useBean id="gebruikers" class="databank.dao.PersoonDao" />
                                <c:forEach var="gebruiker" items="${gebruikers.alleGebruikers}">
                                
                                
                                    
                                    <option value="${gebruiker.gebruikersnaam}">${gebruiker.gebruikersnaam}</option>
                                            
                                </c:forEach>           
                                </select>
                            <input type="text" placeholder="aantal" size="4" name="aantal" />
                        </form>

                    </div>

                </td>               
            </tr>           
            </c:forEach>
            </tbody>
        </table>
    </article>
</section> 
            <script language="JavaScript" type="text/javascript">
<!--
$(document).ready(function() {
    $('#productenTable').DataTable();
} );
-->
</script> 
</body>
</html>
<% } else {
        response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
    }
%>