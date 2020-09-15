<%-- 
    Document   : gebToevoegen
    Created on : 20-mei-2017, 12:14:16
    Author     : zenodotus
--%>
<%@page import="databank.TblProduct"%>
<% if((session.getAttribute("gebruikersnaam") != null && !session.getAttribute("gebruikersnaam").equals("")) && session.getAttribute("soort").equals("gebruiker")) { %>
<%@page import="databank.TblPersoon"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../headers/header.jsp" />
<jsp:include page="../headers/menu.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<section id="hoofdinhoud">
    <article id="gebruikers">
        <div id="foutmelding"><s:actionerror /></div>
        <table id="gebruikersTable" border="1" width="100%">
            <thead>
                <tr>
                    <td>Gebruikersnaam</td>
                    <td>Voornaam</td>
                    <td>Naam</td>
                    <td>Adres</td>
                    <td>telefoon</td>
                    <!--<td>E-mail</td>-->
                    <td>Bewerkingen</td>
                </tr>
            </thead>
            <tbody>
            <jsp:useBean id="personen" class="databank.dao.PersoonDao" scope="request" />
            
            <c:forEach var="persoon" items="${personen.alleGebruikers}">
                 <c:set var="gebruiker" value="${persoon.gebruikersnaam}" scope="session" />
            <tr><td>${persoon.gebruikersnaam}</td>
                <td>${persoon.voornaam}</td>
                <td>${persoon.naam} </td>
                <td>${persoon.adres}</td>
                <td>${persoon.telefoon}</td> 
                <!--<td>${persoon.EMail}</td>-->
                <td>
                         <a href="verwijderenGebruiker?gebruikersnaam=${persoon.gebruikersnaam}">Verwijderen</a><br>
                         
                        <a href="aanpassenGebruiker?gebruikersnaam=${persoon.gebruikersnaam}">Aanpassen</a><br>
                       
                         <script>
                    $(function () {
                            var dialog;

                            // a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
                            $("#dialog:ui-dialog").dialog("destroy");



                            $("#uitleen${persoon.gebruikersnaam}").hide();

                            dialog = $("#uitleen${persoon.gebruikersnaam}").dialog({
                                autoOpen: false,
                                height: 400,
                                width: 350,
                                modal: true,
                                closeOnEscape: true,
                                buttons: {
                                    "Uitlenen": function () {
                                        
                                        $("#doen${persoon.gebruikersnaam}").submit();
                                    }

                                },
                                close: function () {
                                    dialog.dialog("close");

                                }
                            });




                            $("#uitlenen${persoon.gebruikersnaam}").on("click", function (event) {
                                event.preventDefault();
                                dialog.dialog("open");
                            });
                        }
                        );
                </script>
                    <a href="Uitlenen.do?gebruiker=${persoon.gebruikersnaam}" id="uitlenen${persoon.gebruikersnaam}">Uitlenen</a>
                    <div id="uitleen${persoon.gebruikersnaam}" title="Uitlening" align="center">
                          <div id="foutmelding">
                            <%if(request.getAttribute("uitlening") != null && request.getAttribute("uitlening").equals("uitgeleend")) {%>
                                Het maximaal aantal uitleningen is bereikt
                                <%request.setAttribute("automatischOpenen", true);
                            }%>
                        </div>  
                        <form id="doen${persoon.gebruikersnaam}" action="uitlenenGebruiker" method="post">
                            
                           <input type="hidden" value="${persoon.gebruikersnaam}" name="gebruikersnaam" />
                           <input type="hidden" value="/users/gebruikers.jsp" name="website" />
                           <jsp:useBean id="producten" class="databank.dao.ProductDao" scope="request" /> 
                                <select name="productId">
                                    <c:forEach var="product" items="${producten.alleProducten}">
                                         <option value="${product.id}">${product.naam}</option>
                                    </c:forEach>
                                </select>
                                
                                
                            <input type="text" placeholder="aantal" size="4" name="aantal" />
                        </form>

                    </div>

                        <br>
                        <script>
                         $(function () {
                            var dialog;

                            // a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
                            $("#dialog:ui-dialog").dialog("destroy");



                            $("#reserveer${persoon.gebruikersnaam}").hide();

                            dialog = $("#reserveer${persoon.gebruikersnaam}").dialog({
                                autoOpen: false,
                                height: 400,
                                width: 350,
                                modal: true,
                                closeOnEscape: true,
                                buttons: {
                                    "Reserveren": function () {
                                        
                                        $("#reserve${persoon.gebruikersnaam}").submit();
                                    }

                                },
                                close: function () {
                                    dialog.dialog("close");

                                }
                            });




                            $("#reserveren${persoon.gebruikersnaam}").on("click", function (event) {
                                event.preventDefault();
                                dialog.dialog("open");
                            });
                        }
                        );
                </script>
                        
                        <a id="reserveren${persoon.gebruikersnaam}" href="Reserveren.do?gebruiker=${persoon.gebruikersnaam}">Reserveren</a>
                    <div id="reserveer${persoon.gebruikersnaam}" title="Reserveren" align="center">
                            <div id="foutmelding">
                            <%if(request.getAttribute("uitlening") != null && request.getAttribute("uitlening").equals("uitgeleend")) {%>
                                Het maximaal aantal uitleningen is bereikt
                                <%request.setAttribute("automatischOpenen", true);
                            }%>
                        </div>
                        <form id="reserve${persoon.gebruikersnaam}" action="reservatieGebruiker" method="post">
                            
                           <input type="hidden" value="${persoon.gebruikersnaam}" name="gebruikersnaam" />
                            
                                
                                
                                <select name="productId">
                                    <c:forEach var="product" items="${producten.alleProducten}">
                                           <option value="${product.id}">${product.naam}</option>
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
    $('#gebruikersTable').DataTable();
} );
-->
</script> 
</body>
</html>
<%} else {
    response.sendRedirect("/UitleenAnker/faces/inloggen.jsp");
}%>